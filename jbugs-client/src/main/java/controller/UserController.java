package controller;

import authentification.Secured;
import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.RoleDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.service.IUserBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
public class UserController {
    @EJB
    private IUserBusinessService IUserBusinessService;


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
        return Response.status(Response.Status.OK).entity(IUserBusinessService.getUserByUsername(userName)).build();
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Secured(PermissionEnum.PERMISSION_MANAGEMENT)
    @Path("/{userName}")
    public Response updateUser(@PathParam("userName") String userName, UserDTO userDTO) {
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

}
