package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Maschine;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
            @QueryParam("uuid") String maschineUUID
    ) {
        Maschine maschine = DataHandler.getInstance().readMaschineByUUID(maschineUUID);
        return Response
                .status(200)
                .entity(maschine)
                .build();
    }
}
