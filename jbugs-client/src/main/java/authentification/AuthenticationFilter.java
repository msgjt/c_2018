package authentification;

import com.auth0.jwt.JWT;
import ro.msg.edu.jbugs.userManagement.business.service.JwtService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;

import javax.annotation.Priority;
import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Context
    private ResourceInfo resourceInfo;

    @EJB
    private JwtService service;


    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        String token = authorizationHeader.substring("Bearer".length()).trim();
        try {
            validateToken(token);

        } catch (Exception e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<PermissionEnum> methodRoles = extractRoles(resourceMethod);
        try {
            if (!methodRoles.isEmpty()) {
                checkPermissions(methodRoles, token);
            }

        } catch (Exception e) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN).build());
        }
    }

    private List<PermissionEnum> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<>();
            } else {
                PermissionEnum[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private void validateToken(String token) throws Exception {
        if (JWT.decode(token).getClaim("iat").asDate().compareTo(Date.from(Instant.now())) < 0) {
            throw new Exception("your token has expired");
        }
    }

    private void checkPermissions(List<PermissionEnum> allowedRoles, String token) throws Exception {
        if (!service.getUserSessionDot(token).getPermissions().containsAll(allowedRoles))
            throw new Exception("you have no permission");
    }
}