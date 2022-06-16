package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Wiederholung;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
            @Valid @BeanParam Wiederholung wiederholung,
            @NotEmpty
            @FormParam("datum") String datum
    ) throws ParseException {
        Date date =new SimpleDateFormat("dd/MM/yyyy").parse(datum);
        wiederholung.setDatum(date);
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
            @Valid @BeanParam Wiederholung wiederholung,
            @NotEmpty
            @FormParam("datum") String datum
    ) throws ParseException {
        int httpStatus = 200;
        Wiederholung oldWiederholung = DataHandler.getInstance().readWiederholungByUUID(wiederholung.getWiederholungUUID());
        if (wiederholung != null) {
            Date date =new SimpleDateFormat("dd/MM/yyyy").parse(datum);
            oldWiederholung.setDatum(date);
            oldWiederholung.setGewicht(wiederholung.getGewicht());
            oldWiederholung.setAnzahlWiederholungen(wiederholung.getAnzahlWiederholungen());
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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

}
