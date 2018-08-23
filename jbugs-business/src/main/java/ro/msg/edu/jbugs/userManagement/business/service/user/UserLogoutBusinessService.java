package ro.msg.edu.jbugs.userManagement.business.service.user;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserSessionDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.service.IUserPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Optional;

@Stateless
public class UserLogoutBusinessService {
    @EJB
    private IUserPersistenceService userPersistenceManager;

    public boolean logout(UserSessionDTO userSessionDTO) throws BusinessException {
        Optional<User> userOptional = userPersistenceManager.getUserByUsername(userSessionDTO.getUserName());
        if (!userOptional.isPresent()) {
            throw new BusinessException(ExceptionCode.USERNAME_NOT_VALID);
        }
        return true;
    }
}
