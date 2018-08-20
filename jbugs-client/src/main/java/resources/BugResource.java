package resources;

import com.google.gson.Gson;

import ro.msg.edu.jbugs.userManagement.business.control.BugManagement;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDTO;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

@Path("/bugs")
public class BugResource {

    @EJB
    private BugManagement bugManagement;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBugs(){
        List<BugDTO> bugDTOS = bugManagement.getAllBugs();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugDTOS))
                .build();
    }

}
