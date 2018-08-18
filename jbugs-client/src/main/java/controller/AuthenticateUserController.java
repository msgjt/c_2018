package resources;

import ro.msg.edu.jbugs.userManagement.business.dto.UserSessionDot;
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response userLogin(UserSessionDot userAuth) {
        String token = jwtService.generateToken(userAuth);
        System.out.println(jwtService.getUserSessionDot(token));
        return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();
    }
}
