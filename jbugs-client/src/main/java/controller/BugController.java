package controller;

import com.google.gson.Gson;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.service.IBugBusinessService;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.utils.BugAttachmentMapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response addBug(BugAttachmentMapper bugAttachmentMapper){
        BugDTO addedBug = bugBusinessService.addBug(bugAttachmentMapper.getBug(),bugAttachmentMapper.getAttachmentDTO());
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(addedBug))
                .build();
    }

    @Path("/add")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBugg(){
        BugAttachmentMapper bugAttachmentMapper = new BugAttachmentMapper();
        bugAttachmentMapper.setAttachmentDTO(bugBusinessService.getAllAttachments().get(0));
        bugAttachmentMapper.setBug(bugBusinessService.getAllBugs().get(0));
        BugDTO addedBug = bugBusinessService.addBug(bugAttachmentMapper.getBug(),bugAttachmentMapper.getAttachmentDTO());
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(addedBug))
                .build();
    }

//    @Path("/addd")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response addGetBug(){
//        BugDTO addedBug = bugBusinessService.addBug(bugBusinessService.findBugById(2),bugBusinessService.getAllAttachments().get(0));
//        return Response.status(Response.Status.OK)
//                .entity(new Gson().toJson(addedBug))
//                .build();
//    }

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
