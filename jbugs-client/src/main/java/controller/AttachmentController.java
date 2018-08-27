package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.service.bug.IBugBusinessService;

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttachments(){
        List<AttachmentDTO> attachmentDTOS = bugBusinessService.getAllAttachments();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(attachmentDTOS))
                .build();
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAttachment(AttachmentDTO attachmentDTO){
        System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaa: " + attachmentDTO.getExtension());
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugBusinessService.addAttachment(attachmentDTO)))
                .build();
    }

    @Path("/file")
    @POST
    public Response getFile(byte[] file){
        System.out.println("Aaaaaaaaaaaaaaaaaaaaaaaaaa: " + file.toString());
//        byte[] array = file.getPath().getBytes();
//        AttachmentDTO attachmentDTO = new AttachmentDTO();
//        attachmentDTO.setBlob(array);
//        attachmentDTO.setBugDTO(bugBusinessService.findBugById(2));
//        bugBusinessService.addAttachment(attachmentDTO);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(("Test")))
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
