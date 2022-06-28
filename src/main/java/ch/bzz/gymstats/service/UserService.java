package ch.bzz.gymstats.service;

import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.User;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("user")
public class UserService {

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

}
