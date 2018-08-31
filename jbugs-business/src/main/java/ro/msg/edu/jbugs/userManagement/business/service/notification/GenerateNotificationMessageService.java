package ro.msg.edu.jbugs.userManagement.business.service.notification;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationType;

import javax.ejb.Stateless;

@Stateless
public class GenerateNotificationMessageService {

    public String generateMessage(NotificationType type, BugDTO oldBugDTO, BugDTO newBugDTO) throws BusinessException {
        //ToDo: implement for bug
        switch (type){
            case BUG_UPDATED:
                return newBugNotification(newBugDTO);
        }
        return null;
    }

    public String generateMessage(NotificationType type, UserDTO oldUserDTO, UserDTO newUserDTO) throws BusinessException {
        switch (type) {
            case WELCOME_NEW_USER:
                return welcomeNewUserNotification(newUserDTO);
        }
        throw new BusinessException(ExceptionCode.MESSAGE_GENRATITON_FAIL);
    }

    public String welcomeNewUserNotification(UserDTO newUserDTO) {
        return "bun venit" + newUserDTO.getUsername() + newUserDTO.getFirstName() + newUserDTO.getLastName();
    }

    private String newBugNotification(BugDTO bugDTO){
        return "A new bug was created: " + bugDTO.toString() + ", status: " + bugDTO.getStatus();
    }

}
