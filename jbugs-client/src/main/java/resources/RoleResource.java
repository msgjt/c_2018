package resources;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.control.PermissionManagement;
import ro.msg.edu.jbugs.userManagement.business.control.UserManagement;
import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.utils.IdGenerator;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/roles")
public class RoleResource {
    @EJB
    private PermissionManagement permissionManagement;

    @EJB
    private UserManagement userManagement;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRoles() {
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(userManagement.getAllRoles()))
                .build();
    }


    @Path("/{idRole}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPermissionsForRole(@PathParam("idRole") long id) {
        RoleDTO roleDTO = permissionManagement.getRoleById(id);
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

    @Path("/remove/{idRole}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removePermissionForRole(@PathParam("idRole") long id, IdGenerator idGenerator) {
        RoleDTO roleDTO = permissionManagement.getRoleById(id);
        PermissionDTO permission = permissionManagement.getPermissionById(idGenerator.getId());
        permissionManagement.removePermissionForRole(roleDTO, permission);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(permissionManagement.getRoleById(id)))
                .build();
    }

    @Path("/add/{idRole}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPermissionForRole(@PathParam("idRole") long id, IdGenerator idGenerator) {
        RoleDTO roleDTO = permissionManagement.getRoleById(id);
        PermissionDTO permission = permissionManagement.getPermissionById(idGenerator.getId());
        permissionManagement.addPermissionForRole(roleDTO, permission);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(permissionManagement.getRoleById(id)))
                .build();
    }
}
