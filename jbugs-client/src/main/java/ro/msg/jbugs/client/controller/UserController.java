package ro.msg.jbugs.client.controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.service.notification.SendEmailBusinessService;
import ro.msg.edu.jbugs.business.service.user.IUserBusinessService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserController {
    @EJB
    private IUserBusinessService userBusinessService;
    @EJB
    private SendEmailBusinessService emailService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(userBusinessService.getAllUsers()))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    public Response getUserByUsername(@PathParam("username") String username) {
        Response response;
        try {
            UserDTO userDTO = userBusinessService.getUserByUsername(username);
            response = Response.status(Response.Status.OK)
                    .entity(new Gson().toJson(userDTO))
                    .build();
        } catch (BusinessException e) {
            response = Response.status(Response.Status.UNAUTHORIZED)
                    .entity(e.getExceptionCode())
                    .build();
        }

        return response;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(UserDTO userDTO) {
        Response response;
        try {
            response = Response
                        .status(Response.Status.OK)
                        .entity(userBusinessService.updateUser(userDTO))
                        .build();
        } catch (BusinessException e) {
            response = Response.
                        status(Response.Status.PRECONDITION_FAILED)
                        .entity(new Gson().toJson(e.getExceptionCode()))
                        .build();
        }
        return response;
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserDTO userDTO) {
        Response response;
        UserDTO addedUser = null;
        try {
            addedUser = userBusinessService.createUser(userDTO);
            response = Response.status(Response.Status.CREATED)
                    .entity(new Gson().toJson(addedUser))
                    .build();
        } catch (BusinessException e) {
            response = Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Gson().toJson(e.getExceptionCode()))
                    .build();
        }
        return response;
    }
}
