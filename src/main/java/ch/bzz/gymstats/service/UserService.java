package ch.bzz.gymstats.service;

import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.User;
import ch.bzz.gymstats.util.JWToken;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("user")
public class UserService {

    @RolesAllowed({"admin"})
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUser() {
        List<User> userList = DataHandler.getInstance().readAllUser();

        return Response
                .status(200)
                .entity(userList)
                .build();
    }

    @RolesAllowed({"admin"})
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readUser(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("userUUID") String userUUID
    ) {
        User user = null;
        int httpStatus = 200;
        try {
            user = DataHandler.getInstance().readUserByUUID(userUUID);
            if (user == null) {
                httpStatus = 404;
            }
        } catch (Exception exception) {
            httpStatus = 400;
        }
        return Response
                .status(httpStatus)
                .entity(user)
                .build();
    }

    @RolesAllowed({"admin", "user"})
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUser(
            @Valid @BeanParam User user
    ) {
        DataHandler.getInstance().insertUser(user);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @RolesAllowed({"admin", "user"})
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUser(
            @Valid @BeanParam User user
    ) {
        int httpStatus = 200;
        User oldUser = DataHandler.getInstance().readUserByUUID(user.getUserUUID());
        if (user != null) {
            oldUser.setPassword(user.getPassword());
            oldUser.setUserName(user.getUserName());
            oldUser.setUserRole(user.getUserRole());
            DataHandler.getInstance().updateUser();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @RolesAllowed({"admin"})
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteUser(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("userUUID") String userUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteUser(userUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    @PermitAll
    @Path("login")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response loginUser(
            @FormParam("username") String username,
            @FormParam("password") String password
    ) {
        int httpStatus = 200;
        User user = DataHandler.getInstance().readUserLogin(
                username,
                password
        );

        String token;
        Map<String, Object> claimMap = new HashMap<>();
        int randomWord = 0;
        if (user.getUserRole().equals("guest")) {
            httpStatus = 404;
        } else {
            randomWord = (int) (Math.random() * 5);
            claimMap.put("role", user.getUserRole());
            claimMap.put("word", user.getWords().get(randomWord));
        }
        token = JWToken.buildToken(user.getUserRole(), 5, claimMap);


        NewCookie roleCookie = new NewCookie(
                "userRole",
                user.getUserRole(),
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        NewCookie wordCookie = new NewCookie(
                "secret",
                randomWord + 1 + "",
                "/",
                "",
                "Login-Cookie",
                600,
                false
        );

        return Response
                .status(httpStatus)
                .entity(randomWord + 1)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .cookie(roleCookie)
                .cookie(wordCookie)
                .build();
    }

    @PermitAll
    @Path("2fa")
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response checkWord(
            @HeaderParam("Authorization") String authorization,
            @FormParam("secret") String secret
    ) {
        int httpStatus = 200;
        String token = authorization.substring(7);
        Map<String, String> claims = JWToken.readClaims(token);
        String word = claims.get("word");
        if (word == null || !word.equals(secret)) {
            httpStatus = 401;
        }

        return Response
                .status(httpStatus)
                .entity(null)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .build();
    }

    @RolesAllowed({"admin", "user"})
    @GET
    @Path("logoff")
    @Produces(MediaType.TEXT_PLAIN)
    public Response logoff() {

        NewCookie tokenCookie = new NewCookie(
                "token",
                "",
                "/",
                "",
                "Auth-Token",
                1,
                false);

        Response response = Response
                .status(205)
                .header("Access-Control-Allow-Origin", "*")
                .entity("")
                .cookie(tokenCookie)
                .build();
        return response;
    }


}
