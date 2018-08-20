package resources;

import ro.msg.edu.jbugs.userManagement.business.control.UserLoginControl;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.UserDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDot;
import services.JwtService;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Path("/authenticate")
public class AuthenticateUser {
    @EJB
    private JwtService jwtService;
    @EJB
    private UserLoginControl userLoginControl;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response userLogin(UserLoginDot userLoginDot) {
        if (userLoginControl.validateUser(userLoginDot)) {
            String token = jwtService.generateToken(UserDTOHelper.toEntity(userLoginDot));
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
