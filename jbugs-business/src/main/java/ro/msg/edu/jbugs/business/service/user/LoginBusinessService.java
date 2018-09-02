package ro.msg.edu.jbugs.business.service.user;

import ro.msg.edu.jbugs.business.dto.user.UserSessionDTO;
import ro.msg.edu.jbugs.business.service.utils.Encryptor;
import ro.msg.edu.jbugs.business.dto.helper.UserDTOHelper;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.business.service.utils.JwtService;
import ro.msg.edu.jbugs.persistence.entity.User;
import ro.msg.edu.jbugs.persistence.service.user.IUserPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Optional;

@Stateless
public class LoginBusinessService implements ILoginBusinessService {
    @EJB
    private IUserBusinessService userBusinessService;
    @EJB
    private JwtService jwtService;
    @EJB
    private UserDTOHelper userDTOHelper;
    @EJB
    private IUserPersistenceService userPersistenceManager;
    @EJB
    private SessionSingleton sessionSingleton;


    @Override
    public String login(UserLoginDTO loginDTO) throws BusinessException {
        UserDTO userDTO = userBusinessService.getUserByUsername(loginDTO.getUsername());
        if(!userDTO.getIsActive()){
            throw new BusinessException(ExceptionCode.USER_INACTIVATED);
        }
        addUserLoginRequest(loginDTO.getUsername());
        validatePassword(loginDTO, userDTO);
        sessionSingleton.userOverflow.remove(loginDTO.getUsername());
        validateUserActive(loginDTO.getUsername());
        return jwtService.generateToken(userDTOHelper.toEntity(loginDTO));
    }

    @Override
    public void validatePassword(UserLoginDTO loginDTO, UserDTO userDTO) throws BusinessException {
        if (!Encryptor.encrypt(loginDTO.getPassword()).equals(userDTO.getPassword())) {
            throw new BusinessException(ExceptionCode.INVALID_USER_LOGIN);
        }
    }

    @Override
    public void addUserLoginRequest(String userName) throws BusinessException {
        validateUserActive(userName);
        if (sessionSingleton.userOverflow.get(userName) == null) {
            sessionSingleton.userOverflow.put(userName, 0);
        } else {
            sessionSingleton.userOverflow.put(userName, sessionSingleton.userOverflow.get(userName) + 1);
        }
    }

    @Override
    public void removeUserFromOverflow(String userName) {
        sessionSingleton.userOverflow.remove(userName);
    }

    private void validateUserActive(String userName) throws BusinessException {
        if(sessionSingleton.userOverflow.get(userName) != null && sessionSingleton.userOverflow.get(userName) > 5) {
            userBusinessService.deactivateUser(userName);
            throw new BusinessException(ExceptionCode.INVALID_USER_LOGIN);
        }
    }

    @Override
    public boolean logout(UserSessionDTO userSessionDTO) throws BusinessException {
        Optional<User> userOptional = userPersistenceManager.getUserByUsername(userSessionDTO.getUsername());
        if (!userOptional.isPresent()) {
            throw new BusinessException(ExceptionCode.USERNAME_NOT_VALID);
        }
        return true;
    }
}
