package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;


public interface IUserPersistenceService extends Serializable {

    /**
     * Method used for persisting a user in DB
     * @param user
     * @return optional of the persisted user
     */
    Optional<User> createUser(@NotNull User user);


    /**
     * Method used for updating a user
     * @param user
     * @return optional of the updated user
     */
    Optional<User> updateUser(@NotNull User user);


    /**
     * Method for getting an user after an id
     * @param id
     * @return optional of the user if exists or emptry optional instead
     */
    Optional<User> getUserById(long id);

    /**
     * Method for getting all users from DB
     * @return set of users
     */
    Set<User> getAllUsers();

    /**
     * Method used to changed an user's password
     * @param username
     * @param password
     */
    void changePassword(@NotNull String username, @NotNull String password);
    /**
     * Method used for getting an user after his username
     * @param username
     * @return optional of the founded user or optional of empty if it doesn't exist
     */
    Optional<User> getUserByUsername(@NotNull String username);

    /**
     * Method used for getting an user after his email
     * @param email
     * @return optional of the founded user or optional of empty if it doesn't exist
     */
    Optional<User> getUserByEmail(@NotNull String email);

    /**
     * Method used for getting a list of usernames that begins with the username sent as param
     * @param username
     * @return set of founded usernames
     */
    Set<String> getUsernamesLike(@NotNull String username);

    /**
     * Method used for getting the number of unfinished tasks by an user
     * @param user
     * @return number of unfinished tasks for an user
     */
    int countUnfinishedTasks(@NotNull User user);

}
