package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface PermissionPersistenceManagement extends Serializable {

    Optional<Permission> addPermission(Permission permission);
    Optional<Permission> updatePermission(Permission permission);
    Optional<Permission> removePermissionById(long id);
    Optional<Permission> removePermissionForRole(Role role, Permission permission);
    Optional<Role> removeAllPermissionsForRole(Role role);
    Optional<Permission> getPermissionForId(long id);
    List<Permission> getPermissionsForRole(Role role);
    List<Permission> getAllPermissions();
    Optional<Permission> createPermissionForRole(Role role, Permission permission);


}
