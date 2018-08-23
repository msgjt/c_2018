package controller;

import com.google.gson.Gson;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.service.bug.IBugBusinessService;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.Date;
import java.util.List;

@Path("/bugs")
public class BugController {

    @EJB
    private IBugBusinessService bugBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBugs(){
        List<BugDTO> bugDTOS = bugBusinessService.getAllBugs();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugDTOS))
                .build();
    }
    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBugAndAttachment(AttachmentDTO attachmentDTO){
        BugDTO bugDTO = attachmentDTO.getBugDTO();
        System.out.println("aaaaaaaaaaaaaaa" + bugDTO.getTargetDate());
        bugBusinessService.addBug(bugDTO,attachmentDTO);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(attachmentDTO))
                .build();
    }


    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBug(BugDTO bugDTO){
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugBusinessService.updateBug(bugDTO)))
                .build();
    }

    @Path("/{idBug}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPermissionsForRole(@PathParam("idBug") long id) {
        BugDTO bugDTO = bugBusinessService.findBugById(id);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugDTO))
                .build();
    }

}
