package ro.msg.jbugs.client.controller;

import com.google.gson.Gson;

import ro.msg.edu.jbugs.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.business.dto.bug.BugFiltersDTO;
import ro.msg.edu.jbugs.business.dto.bug.CommentDTO;
import ro.msg.edu.jbugs.business.dto.bug.HistoryDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.service.bug.IBugBusinessService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;

@Path("/bugs")
public class BugController {

    @EJB
    private IBugBusinessService bugBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBugs() {
        List<BugDTO> bugDTOS = bugBusinessService.getAllBugs();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugDTOS))
                .build();
    }

    @Path("/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBugAndAttachment(BugDTO bugDTO) {
        Response response;
        BugDTO addedBug = null;
        try {
            addedBug = bugBusinessService.addBug(bugDTO);
            response = Response.status(Response.Status.CREATED)
                    .entity(new Gson().toJson(addedBug))
                    .build();
        } catch (BusinessException e) {
            response = Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Gson().toJson(e.getExceptionCode()))
                    .build();
        }
        return response;
    }

    @Path("/update")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBug(BugDTO bugDTO) {
        Response response;
        BugDTO addedBug = null;
        try {
            addedBug = bugBusinessService.updateBug(bugDTO);
            response = Response.status(Response.Status.CREATED)
                    .entity(new Gson().toJson(addedBug))
                    .build();
        } catch (BusinessException e) {
            response = Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(new Gson().toJson(e.getExceptionCode()))
                    .build();
        }
        return response;
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
    public Response getAllComments(@PathParam("idBug") long id) {
        List<CommentDTO> comments = bugBusinessService.getCommentsForBug(id);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(comments))
                .build();
    }

    @Path("filter")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response setFilters(List<BugFiltersDTO> filtersDTOs) {
        System.out.println("filtering");

        filtersDTOs.forEach(x -> System.out.println("filter: " + x.getField() + " " + x.getData()));
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(bugBusinessService.filterBugs(filtersDTOs)))
                .build();
    }

    @Path("comments/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment(CommentDTO commentDTO) {
        Response response;
        try {
            CommentDTO commentAdded = bugBusinessService.addComment(commentDTO);
            response = Response.status(Response.Status.OK)
                    .entity(new Gson().toJson(commentAdded))
                    .build();
        } catch (BusinessException e) {
            response = Response.status(Response.Status.PRECONDITION_FAILED)
                    .entity(e.getExceptionCode())
                    .build();
        }

        return response;


    }

    @Path("/history/add")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addHistory(HistoryDTO historyDTO) {
        HistoryDTO addedHistory = bugBusinessService.addHistory(historyDTO);
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(addedHistory))
                .build();
    }

    @Path("/history")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHistory() {
        List<HistoryDTO> historyDTOS = bugBusinessService.getAllHistory();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(historyDTOS))
                .build();
    }

    @Path("/statistics/all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistics() {
        Map<String, Long> allBugsMap = bugBusinessService.getStatistics();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(allBugsMap))
                .build();
    }

    @Path("/statistics/users")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatisticsForUser() {
        Map<String, Long> statisticsMap = bugBusinessService.getFixedBugsForUser();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(statisticsMap))
                .build();
    }

    @Path("/statistics/created")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatisticsForCreatedAndRejectedBugs() {
        Map<String, Long> statisticsMap = bugBusinessService.getStatisticsForNewAndRejectedBugs();
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(statisticsMap))
                .build();
    }


}
