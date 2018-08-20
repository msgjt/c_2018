package controller;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.UserDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.userManagement.business.service.JwtService;
import ro.msg.edu.jbugs.userManagement.business.service.UserLoginBusinessService;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/authenticate")
public class AuthenticateUserController {
    @EJB
    private JwtService jwtService;
    @EJB
    private UserLoginBusinessService userLoginBusinessService;
    /**
     * This class is used for send token to angular client
     */
    private class Token {
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
        if (userLoginBusinessService.validateUser(userLoginDTO)) {
            String token = jwtService.generateToken(UserDTOHelper.toEntity(userLoginDTO));
            Token authetificationToken = new Token();
            authetificationToken.setKey("Bearer " + token);
            return Response.status(Response.Status.OK)
                    .entity(new Gson().toJson(authetificationToken))
                    .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }
}
