package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Maschine;
import ch.bzz.gymstats.model.Uebung;
import ch.bzz.gymstats.model.Wiederholung;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createUebung(
            @FormParam("wiederholungListe") List<String> wiederholungListe,
            @FormParam("maschine") String maschine,
            @FormParam("uebungName") String uebungName
    ) {
        Uebung uebung = new Uebung();
        uebung.setUebungUUID(UUID.randomUUID().toString());
        setAttributes(
                uebung,
                wiederholungListe,
                maschine,
                uebungName
        );
        DataHandler.getInstance().insertUebung(uebung);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateUebung(
            @QueryParam("uebungUUID") String uebungUUID,
            @FormParam("wiederholungListe") List<String> wiederholungListe,
            @FormParam("maschine") String maschine,
            @FormParam("uebungName") String uebungName
    ) {
        int httpStatus = 200;
        Uebung uebung = DataHandler.getInstance().readUebungByUUID(uebungUUID);
        if (uebung != null) {
            setAttributes(
                    uebung,
                    wiederholungListe,
                    maschine,
                    uebungName
            );

            DataHandler.getInstance().updateUebung();
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
    public Response deleteUebung(
            @QueryParam("uebungUUID") String uebungUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteUebung(uebungUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    private void setAttributes(
            Uebung uebung,
            List<String> wiederholungListe,
            String maschine,
            String uebungName

    ) {
        uebung.setWiederholungListe(wiederholungListe);
        uebung.setMaschineUUID(maschine);
        uebung.setUebungName(uebungName);
    }

}
