package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public interface IUserPersistenceService extends Serializable {

    Optional<User> createUser(@NotNull User user);

    Optional<User> updateUser(@NotNull User user);

    List<User> getAllUsers();

    Optional<User> getUserByUsername(@NotNull String username);

    Optional<Role> createRole(@NotNull Role role);

    Optional<Role> removeRole(@NotNull Role role);

    Optional<Role> updateRole(@NotNull Role role);

    Optional<Role> getRoleForId(long id);

    List<Role> getAllRoles();

    Optional<User> getUserByEmail(@NotNull String email);

    List<String> getUsernamesLike(@NotNull String username);

    Optional<User> createRoleForUser(@NotNull Role role, @NotNull User user);
}
