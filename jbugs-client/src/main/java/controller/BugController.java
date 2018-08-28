package controller;

import authentification.Secured;
import com.google.gson.Gson;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugFiltersDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.CommentDTO;
import ro.msg.edu.jbugs.userManagement.business.service.bug.IBugBusinessService;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.lang.annotation.Repeatable;
import java.util.Date;
import java.util.List;

@Path("/bugs")
public class BugController {

    @EJB
    private IBugBusinessService bugBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //ToDO add permission BUG_MANAGEMENT restriction
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
    public Response addBugAndAttachment(BugDTO bugDTO){
        bugBusinessService.addBug(bugDTO);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugDTO))
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

    @Path("comments/{idBug}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllComments(@PathParam("idBug") long id){
        List<CommentDTO> comments = bugBusinessService.getCommentsForBug(id);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(comments))
                .build();
    }

    @Path("filter")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setFilters(List<BugFiltersDTO> filtersDTOs){
        System.out.println("filtering");

        filtersDTOs.forEach(x -> System.out.println("filter: "+x.getField()+" "+x.getData()));
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugBusinessService.filterBugs(filtersDTOs)))
                .build();
    }

    @Path("comments/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment(CommentDTO commentDTO){
        CommentDTO commentAdded = bugBusinessService.addComment(commentDTO);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(commentAdded))
                .build();
    }
}
