package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.service.bug.IBugBusinessService;
import ro.msg.edu.jbugs.userManagement.business.service.utils.ByteToFilesConverter;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("/attachments")
public class AttachmentController {
    @EJB
    private IBugBusinessService bugBusinessService;

    byte[] fileBytes = new byte[500000];

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttachments(){
        List<AttachmentDTO> attachmentDTOS = bugBusinessService.getAllAttachments();
        ByteToFilesConverter byteToFilesConverter = new ByteToFilesConverter();

        AttachmentDTO attachmentDTO = attachmentDTOS.get(attachmentDTOS.size()-1);
        System.out.println(attachmentDTO.getBlob().toString().getBytes().length);
//        Fi = attachmentDTO.getBlob();
//            byteToFilesConverter.getDocfromBytes(array);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(attachmentDTOS))
                .build();
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAttachment(AttachmentDTO attachmentDTO){
        attachmentDTO.setBlob(this.fileBytes);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugBusinessService.addAttachment(attachmentDTO)))
                .build();
    }

    @Path("/file")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response getFile(byte[] file){
        this.fileBytes = file;
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson("received"))
                .build();
    }


    @Path("/{idBug}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttachments(@PathParam("idBug") long idBug){

        List<AttachmentDTO> attachmentDTOS = bugBusinessService.getAllAttachments().stream().filter(x ->x.getBugDTO().getIdBug().equals(idBug)).collect(Collectors.toList());
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(attachmentDTOS))
                .build();
    }

    @Path("/delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAttachment(AttachmentDTO attachmentDTO){
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugBusinessService.deleteAttachment(attachmentDTO)))
                .build();
    }
}
