package ro.msg.jbugs.client.controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.business.dto.user.PermissionDTO;
import ro.msg.edu.jbugs.business.service.user.IPermissionBusinessService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/permissions")
public class PermissionController {

    @EJB
    private IPermissionBusinessService permissionBusinessService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPermissions() {
        List<PermissionDTO> permissionDTOList = permissionBusinessService.getAllPermissions();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(permissionDTOList))
                .build();
    }

    @Path("/{idPermission}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPermissionById(@PathParam("idPermission") long id) {
        PermissionDTO permissionDTO = permissionBusinessService.getPermissionById(id);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(permissionDTO))
                .build();
    }
}
