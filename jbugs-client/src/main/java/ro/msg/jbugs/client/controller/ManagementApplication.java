package ro.msg.jbugs.client.controller;

import ro.msg.jbugs.client.authentification.AuthenticationFilter;
import ro.msg.jbugs.client.authentification.CORSFilter;


import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class ManagementApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(UserController.class);
        classes.add(AuthenticationFilter.class);
        classes.add(AuthenticateUserController.class);
        classes.add(PermissionController.class);
        classes.add(RoleController.class);
        classes.add(BugController.class);
        classes.add(CORSFilter.class);
        classes.add(LogoutUserController.class);
        classes.add(AttachmentController.class);
        classes.add(CaptchaController.class);
        classes.add(NotificationController.class);
        return classes;
    }
}
