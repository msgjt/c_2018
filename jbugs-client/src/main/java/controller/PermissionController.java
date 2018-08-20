package resources;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.control.PermissionManagement;
import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTO;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/permissions")
public class PermissionResource {

    @EJB
    private PermissionManagement permissionManagement;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPermissions() {
        List<PermissionDTO> permissionDTOList = permissionManagement.getAllPermissions();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(permissionDTOList))
                .build();
    }

    @Path("/{idPermission}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPermissionById(@PathParam("idPermission") long id) {
        PermissionDTO permissionDTO = permissionManagement.getPermissionById(id);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(permissionDTO))
                .build();
    }
}
