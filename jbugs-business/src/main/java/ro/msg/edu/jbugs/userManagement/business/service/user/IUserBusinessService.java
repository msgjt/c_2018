package ro.msg.edu.jbugs.userManagement.business.service.user;

import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import java.util.List;
import java.util.function.Function;

public interface IUserBusinessService {

    UserDTO createUser(UserDTO userDTO) throws BusinessException;

    void deactivateUser(String username);

    void activateUser(String username);

    UserDTO updateUser(UserDTO userToUpdate, UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserByUsername(String username) throws BusinessException;

    void deleteUser(String userName) throws BusinessException;
}
