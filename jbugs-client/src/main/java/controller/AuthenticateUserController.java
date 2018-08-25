package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.service.user.UserLoginBusinessService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/authenticate")
public class AuthenticateUserController {
    @EJB
    private UserLoginBusinessService loginBusinessService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public Response userLogin(UserLoginDTO userLoginDTO) {
        Response loginResponse;
        try {
            String token = loginBusinessService.login(userLoginDTO);
            loginResponse = Response
                    .status(Response.Status.OK)
                    .entity(new Gson().toJson(token))
                    .build();
        } catch (BusinessException e) {
            loginResponse = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(e.getExceptionCode())
                    .build();
        }
        return loginResponse;
    }
}
