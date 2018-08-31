package ro.msg.edu.jbugs.userManagement.business.service.user;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserChangePasswordDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;

import java.util.List;

public interface IUserBusinessService {

    UserDTO createUser(UserDTO userDTO) throws BusinessException;

    void deactivateUser(String username);

    void activateUser(String username);

    UserDTO updateUser(UserDTO userDTO) throws BusinessException;

    List<UserDTO> getAllUsers();

    UserDTO getUserByUsername(String username) throws BusinessException;

    void changePassword(UserChangePasswordDTO userChangePasswordDTO) throws BusinessException;
}
