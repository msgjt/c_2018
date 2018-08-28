package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.service.user.IRoleBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.exceptions.PersistenceException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/roles")
public class RoleController {
    @EJB
    private IRoleBusinessService roleBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRoles() {
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(roleBusinessService.getAllRoles()))
                .build();
    }


    @Path("/{idRole}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPermissionsForRole(@PathParam("idRole") long id) {
        RoleDTO roleDTO = null;
        try {
            roleDTO = roleBusinessService.getRoleById(id);
        } catch (PersistenceException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(roleDTO))
                .build();
    }

    @Path("add/{idRole}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMethodAddPermissionForRole(@PathParam("idRole") long id) {
        return this.getPermissionsForRole(id);
    }

    @Path("remove/{idRole}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMethodRemovePermissionForRole(@PathParam("idRole") long id) {
        return this.getPermissionsForRole(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRole(RoleDTO roleDTO) {
        Response updateResponse;
        try {
            RoleDTO roleUpdated = roleBusinessService.updateRole(roleDTO);
            updateResponse = Response
                    .status(Response.Status.OK)
                    .entity(new Gson().toJson(roleUpdated))
                    .build();
            return updateResponse;
        } catch (BusinessException | PersistenceException e) {
            updateResponse = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .build();
            return updateResponse;
        }
    }
}
