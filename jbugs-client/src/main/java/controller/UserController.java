package controller;

import authentification.Secured;
import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.EmailDto;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.service.notification.SendEmailService;
import ro.msg.edu.jbugs.userManagement.business.service.user.IUserBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserController {
    @EJB
    private IUserBusinessService IUserBusinessService;

    @EJB
    private SendEmailService emailService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(IUserBusinessService.getAllUsers()))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{userName}")
    public Response getUserByUsername(@PathParam("userName") String userName) {
        Response response;
        try {
            UserDTO userDTO = IUserBusinessService.getUserByUsername(userName);
            response = Response.status(Response.Status.UNAUTHORIZED)
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
    @Path("/{userName}")
    public Response updateUser(@PathParam("userName") String userName, UserDTO userDTO) {
        UserDTO userToUpdate = null;
        try {
            userToUpdate = IUserBusinessService.getUserByUsername(userName);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        UserDTO userUpdated = IUserBusinessService.updateUser(userToUpdate, userDTO);

        return Response.status(Response.Status.OK).entity(new Gson().toJson(userUpdated)).build();
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserDTO userDTO) {
        UserDTO addedUser = null;
        try {
            addedUser = IUserBusinessService.createUser(userDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(addedUser))
                .build();
    }

    //ToDO:delete this
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/email")
    public Response sendEmail() {
        Response response;
        try {
            emailService.sendEmail(new EmailDto("test", "ioan.grozea@gmail.com", "mere"));
            response = Response.status(Response.Status.OK)
                    .build();
        } catch (BusinessException e) {
            response = Response.status(Response.Status.OK)
                    .entity(e.getExceptionCode())
                    .build();
        }
        return response;
    }
}
