package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.helper.UserDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.business.utils.Encryptor;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.service.IUserPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Optional;

@Stateless
public class UserLoginBusinessService {
    @EJB
    private IUserBusinessService userBusinessService;
    @EJB
    private JwtService jwtService;

    private HashMap<String, Integer> userOverflow = new HashMap<>();

    public String login(UserLoginDTO loginDTO) throws BusinessException {
        UserDTO userDTO = userBusinessService.getUserByUsername(loginDTO.getUserName());
        addUserLoginRequest(loginDTO.getUserName());
        validatePassword(loginDTO, userDTO);
        userOverflow.remove(loginDTO.getUserName());
        validateUserActive(loginDTO.getUserName());
        return jwtService.generateToken(UserDTOHelper.toEntity(loginDTO));
    }

    public void validatePassword(UserLoginDTO loginDTO, UserDTO userDTO) throws BusinessException {
        if (!Encryptor.encrypt(loginDTO.getPassword()).equals(userDTO.getPassword())) {
            throw new BusinessException(ExceptionCode.INVALID_USER_LOGIN);
        }
    }

    public void addUserLoginRequest(String userName) throws BusinessException {
        validateUserActive(userName);
        if (userOverflow.get(userName) == null) {
            userOverflow.put(userName, 0);
        } else {
            userOverflow.put(userName, userOverflow.get(userName) + 1);
        }
    }

    public void removeUserFromOverflow(String userName) {
        userOverflow.remove(userName);
    }

    //ToDo: persist user deactivation
    private void validateUserActive(String userName) throws BusinessException {
        if(userOverflow.get(userName) != null && userOverflow.get(userName) > 5) {
            throw new BusinessException(ExceptionCode.INVALID_USER_LOGIN);
        }
    }
}
