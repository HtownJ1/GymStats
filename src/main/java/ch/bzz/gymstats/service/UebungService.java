package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Uebung;
import ch.bzz.gymstats.model.Wiederholung;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * uebung service
 */
@Path("uebung")
public class UebungService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUebung(){
        List<Uebung> uebungList = DataHandler.getInstance().readAllUebungen();
        return Response
                .status(200)
                .entity(uebungList)
                .build();
    }

    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readUebung(
            @QueryParam("uuid") String wiederholungUUID
    ){
        Wiederholung wiederholung =DataHandler.getInstance().readWiederholungByUUID(wiederholungUUID);
        return Response
                .status(200)
                .entity(wiederholung)
                .build();
    }

}
