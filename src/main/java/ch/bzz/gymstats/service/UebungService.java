package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Uebung;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * uebung service for reading uebungen
 */
@Path("uebung")
public class UebungService {

    /**
     * reads a list of all uebungen
     *
     * @return uebungen as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUebung() {
        List<Uebung> uebungList = DataHandler.getInstance().readAllUebungen();
        return Response
                .status(200)
                .entity(uebungList)
                .build();
    }

    /**
     * reads a uebung identfied by the uuid
     *
     * @param uebungUUID
     * @return uebung
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readUebung(
            @QueryParam("uuid") String uebungUUID
    ) {
        Uebung uebung = DataHandler.getInstance().readUebungByUUID(uebungUUID);
        return Response
                .status(200)
                .entity(uebung)
                .build();
    }

}
