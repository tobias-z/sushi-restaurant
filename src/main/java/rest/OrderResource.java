package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("order")
public class OrderResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response payForOrderAndCreate(String jsonData) {
        return Response.ok().build();
    }

}