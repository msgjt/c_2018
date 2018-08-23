package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.service.user.IPermissionBusinessService;
import ro.msg.edu.jbugs.userManagement.business.service.user.IUserBusinessService;
import ro.msg.edu.jbugs.userManagement.business.dto.user.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.business.service.user.RoleBusinessService;
import ro.msg.edu.jbugs.userManagement.business.utils.IdGenerator;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.function.Function;

@Path("/roles")
public class RoleController {
    @EJB
    private IPermissionBusinessService IPermissionBusinessService;

    @EJB
    private RoleBusinessService roleBusinessService;

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
        RoleDTO roleDTO = roleBusinessService.getRoleById(id);
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
    /*
    @Path("/remove/{idRole}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removePermissionForRole(@PathParam("idRole") long id, IdGenerator idGenerator) {
        RoleDTO roleDTO = IPermissionBusinessService.getRoleById(id);
        PermissionDTO permission = IPermissionBusinessService.getPermissionById(idGenerator.getId());
        IPermissionBusinessService.removePermissionForRole(roleDTO, permission);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(IPermissionBusinessService.getRoleById(id)))
                .build();
    }

    @Path("/add/{idRole}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPermissionForRole(@PathParam("idRole") long id, IdGenerator idGenerator) {
        RoleDTO roleDTO = IPermissionBusinessService.getRoleById(id);
        PermissionDTO permission = IPermissionBusinessService.getPermissionById(idGenerator.getId());
        IPermissionBusinessService.addPermissionForRole(roleDTO, permission);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(IPermissionBusinessService.getRoleById(id)))
                .build();
    }
    */
}
