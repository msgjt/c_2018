import authentification.AuthenticationFilter;
import authentification.CORSFilter;
import resources.AuthenticateUser;
import resources.PermissionResource;
import resources.RoleResource;
import resources.UserResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/rest")
public class ManagementApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();
        classes.add(UserResource.class);
        classes.add(AuthenticationFilter.class);
        classes.add(AuthenticateUser.class);
        classes.add(PermissionResource.class);
        classes.add(RoleResource.class);
        classes.add(CORSFilter.class);
        return classes;
    }
}
