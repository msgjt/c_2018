package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.UserDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.EmailDto;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.service.notification.SendEmailService;
import ro.msg.edu.jbugs.userManagement.business.service.user.IUserBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.service.IUserPersistenceService;
import ro.msg.edu.jbugs.userManagement.persistence.service.UserPersistenceService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashSet;

@Path("/users")
public class UserController {
    @EJB
    private IUserBusinessService userBusinessService;
    @EJB
    private SendEmailService emailService;

    @DELETE
    @Path("/{userName}")
    public Response deleteUser(@PathParam("userName")String userName){
        Response response;
        try {
            userBusinessService.deleteUser(userName);
            response = Response
                    .status(Response.Status.OK)
                    .build();
        } catch (BusinessException e){
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
    @Path("/{userName}")
    public Response getUserByUsername(@PathParam("userName") String userName) {
        Response response;
        try {
            UserDTO userDTO = userBusinessService.getUserByUsername(userName);
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
            userToUpdate = userBusinessService.getUserByUsername(userName);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        UserDTO userUpdated = userBusinessService.updateUser(userToUpdate, userDTO);

        return Response.status(Response.Status.OK).entity(new Gson().toJson(userUpdated)).build();
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
