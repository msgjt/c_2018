package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.service.bug.IBugBusinessService;

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

    static byte[] fileBytes = new byte[1000000];

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAttachments() {
        List<AttachmentDTO> attachmentDTOS = bugBusinessService.getAllAttachments();
//        ByteToFilesConverter byteToFilesConverter = new ByteToFilesConverter();
//        AttachmentDTO attachmentDTO = attachmentDTOS.get(attachmentDTOS.size() - 1);
//        if (attachmentDTO.getExtension().equals(ExtensionEnum.JPG)) {
//            byteToFilesConverter.getImagefromBytes(attachmentDTO.getBlob());
//        }
//        if (attachmentDTO.getExtension().equals(ExtensionEnum.DOC)) {
//            byteToFilesConverter.getDocfromBytes(attachmentDTO.getBlob());
//        }

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
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugBusinessService.addAttachment(attachmentDTO)))
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
        System.out.println("aaaaaaaaaaaaaaa + " + attachmentDTOS.get(0).getName());
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(attachmentDTOS))
                .build();
    }

    @Path("/delete")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteAttachment(AttachmentDTO attachmentDTO) {
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugBusinessService.deleteAttachment(attachmentDTO)))
                .build();
    }
}
