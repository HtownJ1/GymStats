package ch.bzz.gymstats.service;


import ch.bzz.gymstats.data.DataHandler;
import ch.bzz.gymstats.model.Maschine;

import javax.activation.DataContentHandler;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("maschine")
public class MaschineService {

    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMaschinen(){
        List<Maschine> maschineList = DataHandler.getInstance().readAllMaschinen();
        return Response
                .status(200)
                .entity(maschineList)
                .build();
    }
}
