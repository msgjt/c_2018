package ro.msg.jbugs.client.controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.business.dto.user.UserChangePasswordDTO;
import ro.msg.edu.jbugs.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.service.user.ILoginBusinessService;
import ro.msg.edu.jbugs.business.service.user.IUserBusinessService;
import ro.msg.edu.jbugs.business.service.user.LoginBusinessService;

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
    private ILoginBusinessService loginBusinessService;

    @EJB
    private IUserBusinessService userBusinessService;
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

    @POST
    @Path("/changePassword")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response userChangePassword(UserChangePasswordDTO userChangePasswordDTO){
        Response response;
        try{
            userBusinessService.changePassword(userChangePasswordDTO);
            response = Response.status(Response.Status.OK).build();
        } catch (BusinessException e){
            response = Response.status(Response.Status.PRECONDITION_FAILED).entity(new Gson().toJson(e.getExceptionCode())).build();
        }
        return response;

    }
}
