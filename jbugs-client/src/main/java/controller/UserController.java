package controller;

import authentification.Secured;
import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.service.IUserBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserController {
    @EJB
    private IUserBusinessService IUserBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Secured
    public Response getUsers() {
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(IUserBusinessService.getAllUsers()))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userName}")
    public Response getUserByUsername(@PathParam("userName") String userName) {
        return Response.status(Response.Status.OK).entity(IUserBusinessService.getUserByUsername(userName)).build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(PermissionEnum.USER_MANAGEMENT)
    @Path("/{userName}")
    public Response updateUser(@PathParam("userName") String userName, UserDTO userDTO){
        //TODO: Notificare de tip USER_UPDATED
        return Response.status(Response.Status.OK).entity(IUserBusinessService.updateUser(userDTO)).build();

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
