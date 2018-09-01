package ro.msg.edu.jbugs.business.service.user;

import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.business.dto.user.UserSessionDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;

public interface ILoginBusinessService {

    String login(UserLoginDTO loginDTO) throws BusinessException;

    void validatePassword(UserLoginDTO loginDTO, UserDTO userDTO) throws BusinessException;

    void addUserLoginRequest(String userName) throws BusinessException;

    void removeUserFromOverflow(String userName);

    boolean logout(UserSessionDTO userSessionDTO) throws BusinessException;
}
