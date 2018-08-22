package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import java.util.List;

public interface IUserBusinessService {

    /**
     * Method is used for persisting an user from an userDTO.
     * It generates the username and does the validations.
     *
     * @param userDTO user information
     * @return the newly created entity as a userDTO
     */
    UserDTO createUser(UserDTO userDTO) throws BusinessException;

    /**
     * Deactivates a user, restricting the access of said user to the app.
     *
     * @param username
     */
    void deactivateUser(String username);

    /**
     * Activates a deactivated user.
     *
     * @param username
     */
    void activateUser(String username);


    UserDTO updateUser(UserDTO userDTO);

    /**
     * @return a list of DTOs containing information about users.
     */
    List<UserDTO> getAllUsers();


    /**
     * Tries to log in a user.
     *
     * @param username
     * @param password
     * @return UserDTO of said user
     * @throws BusinessException in case the user is not found or the password is wrong.
     */
    UserDTO login(String username, String password) throws BusinessException;

    /**
     * Checks if the user is existing in the db and, if so, logs him out
     *
     * @param username
     * @return true if the use exists
     */
    public boolean logout(String username) throws BusinessException;

    /**
     * Method is used for persisting an role from an roleDTO.
     * It generates the type of role and does the validations.
     *
     * @param roleDTO user information
     * @return the newly created entity as a roleDTO
     */
    RoleDTO createRole(RoleDTO roleDTO) throws BusinessException;


    /**
     * Method used for finding a role from db
     *
     * @param id
     * @return the roleDTO with the id that is sent
     */
    RoleDTO getRoleById(long id);

    /**
     * @return a list of DTOs containing information about roles.
     */
    List<RoleDTO> getAllRoles();

    /**
     * Method used to retrieve from database an user based on his unique username
     *
     * @param username
     * @return the userDTO if it exists
     */
    UserDTO getUserByUsername(String username);


}
