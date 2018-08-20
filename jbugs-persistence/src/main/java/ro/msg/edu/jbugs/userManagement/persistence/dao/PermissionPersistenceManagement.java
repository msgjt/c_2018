package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface PermissionPersistenceManagement extends Serializable {

    Optional<Permission> addPermission(@NotNull Permission permission);

    Optional<Permission> updatePermission(@NotNull Permission permission);

    Optional<Permission> removePermissionById(long id);

    Optional<Permission> removePermissionForRole(@NotNull Role role, @NotNull Permission permission);

    Optional<Role> removeAllPermissionsForRole(@NotNull Role role);

    Optional<Permission> getPermissionForId(@NotNull long id);

    List<Permission> getPermissionsForRole(@NotNull Role role);

    List<Permission> getAllPermissions();

    Optional<Permission> createPermissionForRole(@NotNull Role role, @NotNull Permission permission);

    Optional<Role> getRoleForId(long id);


}
