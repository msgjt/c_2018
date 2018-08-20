package resources;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.control.UserLoginControl;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.UserDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import services.JwtService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

@Path("/authenticate")
public class AuthenticateUser {
    @EJB
    private JwtService jwtService;
    @EJB
    private UserLoginControl userLoginControl;

    class Token {
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response userLogin(UserLoginDTO userLoginDTO) {
        if (userLoginControl.validateUser(userLoginDTO)) {
            String token = jwtService.generateToken(UserDTOHelper.toEntity(userLoginDTO));
            Token token1 = new Token();
            token1.setKey("Bearer " + token);
            return Response.status(Response.Status.OK)
                    .entity(new Gson().toJson(token1))
                    .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
