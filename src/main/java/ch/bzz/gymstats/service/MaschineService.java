package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Maschine;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

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
            @QueryParam("uuid") String maschineUUID
    ) {
        Maschine maschine = DataHandler.getInstance().readMaschineByUUID(maschineUUID);
        return Response
                .status(200)
                .entity(maschine)
                .build();
    }

    @PUT
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createMaschine(
            @FormParam("name") String name,
            @FormParam("muskel") String muskel
    ) {
        Maschine maschine = new Maschine();
        maschine.setMaschineUUID(UUID.randomUUID().toString());
        setAttributes(
                maschine,
                name,
                muskel
        );
        DataHandler.getInstance().insertMaschine(maschine);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @POST
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateMaschine(
            @FormParam("maschineUUID") String maschineUUID,
            @FormParam("name") String name,
            @FormParam("muskel") String muskel
    ) {
        int httpStatus = 200;
        Maschine maschine = DataHandler.getInstance().readMaschineByUUID(maschineUUID);
        if (maschine != null) {
            setAttributes(
                    maschine,
                    name,
                    muskel
            );

            DataHandler.getInstance().updateMaschine();
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
    public Response deleteMaschine(
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


    private void setAttributes(
            Maschine maschine,
            String name,
            String muskel

    ) {
        maschine.setName(name);
        maschine.setMuskel(muskel);
    }

}
