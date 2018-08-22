package controller;

import authentification.Secured;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserSessionDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
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
        try {
            userLogoutBusinessService.logout(userSessionDTO);
            return Response.status(Response.Status.OK)
                    .build();
        } catch (BusinessException e) {
            return Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(e.getExceptionCode())
                    .build();
        }
    }
}
