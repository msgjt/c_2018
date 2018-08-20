package resources;

import authentification.Secured;
import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.control.UserManagement;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {
    @EJB
    private UserManagement userManagement;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response getUsers() {
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(userManagement.getAllUsers()))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userName}")
    public Response getUserByUsername(@PathParam("userName") String userName) {
        return Response.status(Response.Status.OK).entity(userManagement.getUserByUsername(userName)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersPost() {
        String[] blabla = {"Gica", "Eu", "Gica", "Eu", "Gica", "Eu",};
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(blabla))
                .build();
    }
}
