package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.service.IBugBusinessService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/attachments")
public class AttachmentController {
    @EJB
    private IBugBusinessService bugBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttachments(){
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugBusinessService.getAllAttachments()))
                .build();
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBug(AttachmentDTO attachmentDTO){
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugBusinessService.addAttachment(attachmentDTO)))
                .build();
    }
}
