package controller;

import com.google.gson.Gson;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.*;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.service.bug.IBugBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.lang.annotation.Repeatable;
import java.util.*;

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
    public Response addBugAndAttachment(BugDTO bugDTO){
        try {
            bugBusinessService.addBug(bugDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugDTO))
                .build();
    }


    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBug(BugDTO bugDTO){
        try {
            bugBusinessService.updateBug(bugDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugDTO))
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

    @Path("/history/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addHistory(HistoryDTO historyDTO){
        HistoryDTO addedHistory = bugBusinessService.addHistory(historyDTO);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(addedHistory))
                .build();
    }

    @Path("/history")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHistory(){
        List<HistoryDTO> historyDTOS = bugBusinessService.getAllHistory();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(historyDTOS))
                .build();
    }

    @Path("/statistics/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistics(){
        Map<String,Long> allBugsMap = bugBusinessService.getStatistics();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(allBugsMap))
                .build();
    }

    @Path("/statistics/users")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatisticsForUser(){
        Map<String,Long> statisticsMap = bugBusinessService.getFixedBugsForUser();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(statisticsMap))
                .build();
    }

    @Path("/statistics/created")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatisticsForCreatedAndRejectedBugs(){
        Map<String,Long> statisticsMap = bugBusinessService.getStatisticsForNewAndRejectedBugs();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(statisticsMap))
                .build();
    }



}
