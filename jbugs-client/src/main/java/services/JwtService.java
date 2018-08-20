package services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.gson.Gson;
import org.apache.commons.codec.binary.Base64;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserSessionDTO;

import javax.ejb.Stateless;
import java.time.Instant;
import java.util.Date;

@Stateless
public class JwtService {
    public String generateToken(final UserSessionDTO user) {
        try {

            Algorithm algorithm = Algorithm.HMAC256("secret");

            return JWT.create()
                    .withClaim("username", user.getUserName())
                    .withClaim("permissions", user.getPermissions().toString())
                    .withIssuedAt(Date.from(Instant.now().plusSeconds(3600)))
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            return null;
        }
    }

    //ToDo: remove comments is it is all ok on monday
    public UserSessionDTO getUserSessionDot(final String token) {
        String[] split_string = token.split("\\.");
        Base64 base64Url = new Base64(true);
        return new Gson().fromJson(new String(base64Url.decode(split_string[1])), UserSessionDTO.class);
    }
}
