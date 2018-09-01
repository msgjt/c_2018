package ro.msg.edu.jbugs.business.service.user;

import ro.msg.edu.jbugs.business.dto.user.UserChangePasswordDTO;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;

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
