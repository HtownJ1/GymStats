package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Uebung;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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
            @Valid @BeanParam Uebung uebung,
            @NotEmpty
            @FormParam("maschine") String maschine
    ) {
        uebung.setMaschineUUID(maschine);
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
            @Valid @BeanParam Uebung uebung,
            @NotEmpty
            @FormParam("maschine") String maschine
    ) {
        int httpStatus = 200;
        Uebung oldUebung = DataHandler.getInstance().readUebungByUUID(uebung.getUebungUUID());
        if (uebung != null) {
            oldUebung.setMaschineUUID(maschine);
            oldUebung.setUebungName(uebung.getUebungName());
            oldUebung.setWiederholungListe(uebung.getWiederholungListe());

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
            @NotEmpty
            @Pattern(regexp = "[0-9a-fA-F]{8}-([0-9a-fA-F]{4}-){3}[0-9a-fA-F]{12}")
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

}
