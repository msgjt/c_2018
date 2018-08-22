package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.service.IBugBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/attachments")
public class AttachmentController {
    @EJB
    private IBugBusinessService bugBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttachments(){
        List<AttachmentDTO> attachmentDTOS = bugBusinessService.getAllAttachments();
        List<String> strings = attachmentDTOS.stream().map(x -> new String(x.getBlob())).collect(Collectors.toList());
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(strings))
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
