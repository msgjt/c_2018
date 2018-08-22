package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
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

    void activateUser(String username);

    UserDTO updateUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    RoleDTO createRole(RoleDTO roleDTO) throws BusinessException;

    RoleDTO getRoleById(long id);

    List<RoleDTO> getAllRoles();

    UserDTO getUserByUsername(String username) throws BusinessException;
}
