package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.UserDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.userManagement.business.service.JwtService;
import ro.msg.edu.jbugs.userManagement.business.service.UserLoginBusinessService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Path("/authenticate")
public class AuthenticateUserController {
    @EJB
    private JwtService jwtService;
    @EJB
    private UserLoginBusinessService userLoginControl;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response userLogin(UserLoginDTO userLoginDot) {
        if (userLoginControl.validateUser(userLoginDot)) {
            String token = jwtService.generateToken(UserDTOHelper.toEntity(userLoginDot));
            return Response
                    .status(Response.Status.OK)
                    .entity(new Gson().toJson(token))
                    .build();
        }
        return Response
                .status(Response.Status.UNAUTHORIZED)
                .build();
    }
}
