package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Wiederholung;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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
    public Response readWiederholung(
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

    /**
     * creates a wiederholung
     *
     * @param wiederholung
     * @return -
     */
    @POST
    @Path("create")
    @Produces(MediaType.TEXT_PLAIN)
    public Response createWiederholung(
            @Valid @BeanParam Wiederholung wiederholung
    ) {
        DataHandler.getInstance().insertWiederholung(wiederholung);
        return Response
                .status(200)
                .entity("")
                .build();
    }

    /**
     * updates a wiederholung identified by the uuid
     *
     * @param wiederholung
     * @return -
     */
    @PUT
    @Path("update")
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateWiederholung(
            @Valid @BeanParam Wiederholung wiederholung
    ) {
        int httpStatus = 200;
        Wiederholung oldWiederholung = DataHandler.getInstance().readWiederholungByUUID(wiederholung.getWiederholungUUID());
        if (wiederholung != null) {
            oldWiederholung.setDatum(wiederholung.getDatum());
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

    /**
     * deletes a wiederholung identified by the uuid
     *
     * @param wiederholungUUID
     * @return -
     */
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
