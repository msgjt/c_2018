package ro.msg.jbugs.client.controller;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import ro.msg.edu.jbugs.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.service.bug.IBugBusinessService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import org.apache.logging.log4j.Logger;
import java.util.stream.Collectors;

@Path("/attachments")
public class AttachmentController {
    @EJB
    private IBugBusinessService bugBusinessService;
    static private byte[] fileBytes = new byte[10000000];
    private Logger logger = LogManager.getLogger(AttachmentController.class);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttachments() {
        List<AttachmentDTO> attachmentDTOS = bugBusinessService.getAllAttachments();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(attachmentDTOS))
                .build();
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAttachment(AttachmentDTO attachmentDTO) {
        attachmentDTO.setBlob(this.fileBytes);
        try {
            bugBusinessService.addAttachment(attachmentDTO);
        } catch (BusinessException e) {
           logger.info(e.getMessage());
        }
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(attachmentDTO))
                .build();
    }

    @Path("/file")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response getFile(byte[] file) {
        this.fileBytes = file;
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson("received"))
                .build();
    }


    @Path("/{idBug}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttachments(@PathParam("idBug") long idBug) {
        List<AttachmentDTO> attachmentDTOS = bugBusinessService.getAllAttachments().stream().filter(x -> x.getBugDTO().getIdBug().equals(idBug)).collect(Collectors.toList());
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(attachmentDTOS))
                .build();
    }

    @Path("/delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAttachment(AttachmentDTO attachmentDTO) {
        try {
             bugBusinessService.deleteAttachment(attachmentDTO);
        } catch (BusinessException e) {
            logger.info(e.getMessage());
        }
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(attachmentDTO))
                .build();
    }
}
