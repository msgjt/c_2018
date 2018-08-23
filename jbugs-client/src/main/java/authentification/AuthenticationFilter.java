package authentification;

import com.auth0.jwt.JWT;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.business.service.utils.JwtService;
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
    public void filter(ContainerRequestContext requestContext) {
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new NotAuthorizedException("Authorization header must be provided");
        }
        String token = authorizationHeader.substring("Bearer".length()).trim();
        authorizeUserToken(token, requestContext);
        authorizeUserRequest(token, requestContext);
    }

    private void authorizeUserRequest(String token, ContainerRequestContext requestContext) {
        Method resourceMethod = resourceInfo.getResourceMethod();
        List<PermissionEnum> methodRoles = extractRoles(resourceMethod);
        try {
            if (!methodRoles.isEmpty()) {
                checkPermissions(methodRoles, token);
            }
        } catch (BusinessException e) {
            requestContext.abortWith(
                    Response.status(Response.Status.FORBIDDEN)
                            .entity(e.getExceptionCode())
                            .build());
        }
    }

    private void authorizeUserToken(String token, ContainerRequestContext requestContext) {
        try {
            validateToken(token);
        } catch (BusinessException e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                            .entity(e.getExceptionCode())
                            .build());
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

    private void validateToken(String token) throws BusinessException {
        if (JWT.decode(token).getClaim("iat").asDate().compareTo(Date.from(Instant.now())) < 0) {
            throw new BusinessException(ExceptionCode.TOKEN_EXPIRED);
        }
    }

    private void checkPermissions(List<PermissionEnum> allowedRoles, String token) throws BusinessException {
        if (!service.getUserSessionDot(token).getPermissions().containsAll(allowedRoles))
            throw new BusinessException(ExceptionCode.PERMISSION_VALIDATION_EXCEPTION);
    }
}
