package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.service.notification.NotificationBusinessService;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/notification")
public class NotificationController {
    @EJB
    private NotificationBusinessService notificationBusinessService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{username}")
    public Response getNotificationsForUser(@PathParam("username") String username) {
        return Response.status(Response.Status.OK)
                .entity(new Gson().toJson(notificationBusinessService.getNotificationsForUser(username)))
                .build();
    }
}
