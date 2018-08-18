package services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;
import ro.msg.edu.jbugs.userManagement.business.dto.UserSessionDot;

import javax.ejb.Stateless;
import java.time.Instant;
import java.util.Date;

@Stateless
public class JwtService {
    public String generateToken(final UserSessionDot user) {
        try {

            Algorithm algorithm = Algorithm.HMAC256("secret");

            return JWT.create()
                    .withClaim("username", user.getUserName())
                    .withClaim("role", user.getPermissionDTOS().toString())
                    .withIssuedAt(Date.from(Instant.now().plusSeconds(3600)))
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            return null;
        }
    }

    public String getUserSessionDot(final String token) {
        String[] split_string = token.split("\\.");
        Base64 base64Url = new Base64(true);
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(new String(base64Url.decode(split_string[1]))).getAsJsonObject();
        return obj.get("username").getAsString();
    }
}
