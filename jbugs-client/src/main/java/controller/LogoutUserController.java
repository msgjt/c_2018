package controller;

import authentification.Secured;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserSessionDTO;
import ro.msg.edu.jbugs.userManagement.business.service.UserLogoutBusinessService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/logout")
@Secured
public class LogoutUserController {

    @EJB
    private UserLogoutBusinessService userLogoutBusinessService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userLogout(UserSessionDTO userSessionDTO) {
        if (userLogoutBusinessService.logoutUser(userSessionDTO)) {
            return Response.status(Response.Status.OK)
                    .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

}
