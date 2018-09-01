package ro.msg.jbugs.client.controller;

import ro.msg.edu.jbugs.business.service.user.ILoginBusinessService;
import ro.msg.jbugs.client.authentification.Secured;
import ro.msg.edu.jbugs.business.dto.user.UserSessionDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;

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
    private ILoginBusinessService userLogoutBusinessService;

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
