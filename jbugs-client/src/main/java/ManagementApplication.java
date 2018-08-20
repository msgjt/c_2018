import authentification.AuthenticationFilter;
import controller.AuthenticateUserController;
import controller.PermissionController;
import controller.RoleController;
import controller.UserController;
import resources.BugResource;

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
        classes.add(BugResource.class);
        return classes;
    }
}
