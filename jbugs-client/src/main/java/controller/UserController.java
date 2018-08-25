package controller;

import authentification.Secured;
import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.service.notification.SendEmailService;
import ro.msg.edu.jbugs.userManagement.business.service.user.IUserBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Secured(PermissionEnum.USER_MANAGEMENT)
public class UserController {
    @EJB
    private IUserBusinessService userBusinessService;
    @EJB
    private SendEmailService emailService;

    @Path("/{userName}")
    public Response deleteUser(@PathParam("userName") String userName) {
        Response response;
        try {
            userBusinessService.deleteUser(userName);
            response = Response
                    .status(Response.Status.OK)
                    .build();
        } catch (BusinessException e) {
            response = Response
                    .status(Response.Status.EXPECTATION_FAILED)
                    .entity(e.getExceptionCode())
                    .build();
        }
        return response;
    }

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
        return Response
                .status(Response.Status.OK)
                .entity(new Gson().toJson(userBusinessService.updateUser(userDTO)))
                .build();
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserDTO userDTO) {
        UserDTO addedUser = null;
        try {
            addedUser = userBusinessService.createUser(userDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(addedUser))
                .build();
    }
}
