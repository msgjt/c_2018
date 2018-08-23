package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public interface IPermissionPersistenceService extends Serializable {

    Optional<Permission> addPermission(@NotNull Permission permission);

    Optional<Permission> updatePermission(@NotNull Permission permission);

    void removePermission(@NotNull Permission permission);

    Optional<Permission> getPermissionForId(@NotNull long id);

    List<Permission> getAllPermissions();

}
