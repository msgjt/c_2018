package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface IUserPersistenceService extends Serializable {

    Optional<User> createUser(@NotNull User user);

    Optional<User> updateUser(@NotNull User user);

    void removeUser(@NotNull User user);

    Set<User> getAllUsers();

    Optional<User> getUserByUsername(@NotNull String username);

    Optional<User> getUserByEmail(@NotNull String email);

    Set<String> getUsernamesLike(@NotNull String username);

}
