package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Wiederholung;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * wiederholung service for reading wiederholungen
 */
@Path("wiederholung")
public class WiederholungService {

    /**
     * reads a list of all wiederholungen
     *
     * @return wiederholungen as JSON
     */
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listWiederholungen() {
        List<Wiederholung> wiederholungList = DataHandler.getInstance().readAllWiederholungen();
        return Response
                .status(200)
                .entity(wiederholungList)
                .build();
    }

    /**
     * reads a wiederholung identified by the uuid
     *
     * @param wiederholungUUID
     * @return wiederholung
     */
    @GET
    @Path("read")
    @Produces(MediaType.APPLICATION_JSON)
    public Response readBook(
            @QueryParam("uuid") String wiederholungUUID
    ) {
        Wiederholung wiederholung = DataHandler.getInstance().readWiederholungByUUID(wiederholungUUID);
        return Response
                .status(200)
                .entity(wiederholung)
                .build();
    }

    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createWiederholung(
            @FormParam("anzahlWiederholungen") Integer anzahlWiederholungen,
            @FormParam("gewicht") Integer gewicht,
            @FormParam("datum") String datum
    ) throws ParseException {
        Wiederholung wiederholung = new Wiederholung();
        wiederholung.setWiederholungUUID(UUID.randomUUID().toString());
        Date date =new SimpleDateFormat("dd/MM/yyyy").parse(datum);
        setAttributes(
                wiederholung,
                anzahlWiederholungen,
                gewicht,
                date
        );
        DataHandler.getInstance().insertWiederholung(wiederholung);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateWiederholung(
            @QueryParam("wiederholungUUID") String wiederholungUUID,
            @FormParam("anzahlWiederholungen") Integer anzahlWiederholungen,
            @FormParam("gewicht") Integer gewicht,
            @FormParam("datum") String datum
    ) throws ParseException {
        int httpStatus = 200;
        Wiederholung wiederholung = DataHandler.getInstance().readWiederholungByUUID(wiederholungUUID);
        if (wiederholung != null) {
            Date date =new SimpleDateFormat("dd/MM/yyyy").parse(datum);
            setAttributes(
                    wiederholung,
                    anzahlWiederholungen,
                    gewicht,
                    date
            );

            DataHandler.getInstance().updateWiederholung();
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
    public Response deleteWiederholung(
            @QueryParam("wiederholungUUID") String wiederholungUUID
    ) {
        int httpStatus = 200;
        if (!DataHandler.getInstance().deleteWiederholung(wiederholungUUID)) {
            httpStatus = 410;
        }
        return Response
                .status(httpStatus)
                .entity("")
                .build();
    }

    private void setAttributes(
            Wiederholung wiederholung,
            Integer anzahlWiederholungen,
            Integer gewicht,
            Date datum

    ) {
        wiederholung.setAnzahlWiederholungen(anzahlWiederholungen);
        wiederholung.setGewicht(gewicht);
        wiederholung.setDatum(datum);
    }

}
