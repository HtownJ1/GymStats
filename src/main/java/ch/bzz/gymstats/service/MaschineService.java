package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Maschine;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * maschine service for reading maschinen
 */
@Path("maschine")
public class MaschineService {


    /**
     * returns a list of all maschinen
     *
     * @return maschinen as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMaschinen() {
        List<Maschine> maschineList = DataHandler.getInstance().readAllMaschinen();
        return Response
                .status(200)
                .entity(maschineList)
                .build();
    }

    /**
     * reads a maschine identified by the uuid
     *
     * @param maschineUUID
     * @return maschine
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readMaschine(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("uuid") String maschineUUID
    ) {
        Maschine maschine = DataHandler.getInstance().readMaschineByUUID(maschineUUID);
        return Response
                .status(200)
                .entity(maschine)
                .build();
    }

    /**
     * creates a maschine
     *
     * @param maschine
     * @return -
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createMaschine(
            @Valid @BeanParam Maschine maschine
    ) {
        DataHandler.getInstance().insertMaschine(maschine);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a maschine identified by the uuid
     *
     * @param maschine
     * @return -
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMaschine(
            @Valid @BeanParam Maschine maschine
    ) {
        int httpStatus = 200;
        Maschine oldMaschine = DataHandler.getInstance().readMaschineByUUID(maschine.getMaschineUUID());
        if (maschine != null) {
            oldMaschine.setMuskel(maschine.getMuskel());
            oldMaschine.setName(maschine.getName());
            DataHandler.getInstance().updateMaschine();
        } else {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    /**
     * deletes a maschine identified by the uuid
     *
     * @param maschineUUID
     * @return -
     */
    @DELETE
    @Path("delete")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteMaschine(
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
            @QueryParam("maschineUUID") String maschineUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteMaschine(maschineUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

}
