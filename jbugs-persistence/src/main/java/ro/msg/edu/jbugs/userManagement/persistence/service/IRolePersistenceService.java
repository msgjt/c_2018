package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.exceptions.PersistenceException;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IRolePersistenceService extends Serializable {

    Optional<Role> createRole(@NotNull Role role);

    void removeRole(@NotNull Role role);

    Optional<Role> updateRole(@NotNull Role role) throws PersistenceException;

    Optional<Role> getRoleById(long id) throws PersistenceException;

    Optional<Role> getRoleByType(@NotNull String type);

    Set<Role> getAllRoles();

}
