package resources;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.control.UserManagement;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {
    @EJB
    private UserManagement userManagement;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(userManagement.getAllUsers()))
                .build();
    }
}
