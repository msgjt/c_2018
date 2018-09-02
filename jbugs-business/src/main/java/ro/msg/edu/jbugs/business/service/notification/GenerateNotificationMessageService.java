package ro.msg.edu.jbugs.business.service.notification;

import ro.msg.edu.jbugs.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.persistence.entity.NotificationEnum;

import javax.ejb.Stateless;
import java.util.stream.Collectors;

@Stateless
public class GenerateNotificationMessageService {

    public String generateMessage(NotificationEnum type, BugDTO oldBugDTO, BugDTO newBugDTO) throws BusinessException {
        switch (type) {
            case BUG_UPDATED:
                return newBugNotification(newBugDTO);
            case BUG_CLOSED:
                return closedBugNotification(newBugDTO);
        }
        throw new BusinessException(ExceptionCode.MESSAGE_GENRATITON_FAIL);
    }

    public String generateMessage(NotificationEnum type, UserDTO oldUserDTO, UserDTO newUserDTO) throws BusinessException {
        switch (type) {
            case WELCOME_NEW_USER:
                return welcomeNewUserNotification(newUserDTO);
            case USER_UPDATED:
                return updateUserNotification(oldUserDTO, newUserDTO);
        }
        throw new BusinessException(ExceptionCode.MESSAGE_GENRATITON_FAIL);
    }

    public String welcomeNewUserNotification(UserDTO newUserDTO) {
        return "bun venit " + newUserDTO.getUsername() + newUserDTO.getFirstName() + newUserDTO.getLastName();
    }

    private String newBugNotification(BugDTO bugDTO) {
        return "A new bug was created: " + bugDTO.toString() + ", status: " + bugDTO.getStatus();
    }

    private String closedBugNotification(BugDTO bugDTO){
        return "The bug was closed "+bugDTO.toString()+", status: "+bugDTO.getStatus();
    }

    private String updateUserNotification(UserDTO oldUserDTO, UserDTO newUserDTO) {
        return "a user was updated: FirstName " + oldUserDTO.getFirstName() + " is now " + newUserDTO.getFirstName() +
                ",\n LastName " + oldUserDTO.getLastName() + " is now " + newUserDTO.getLastName() +
                ",\n PhoneNumber " + oldUserDTO.getPhoneNumber() + " is now " + newUserDTO.getPhoneNumber() +
                ",\n Email " + oldUserDTO.getEmail() + " is now " + newUserDTO.getEmail() +
                ",\n Roles " + getRolesForUser(oldUserDTO) + " is now " + getRolesForUser(newUserDTO) +
                ".";
    }

    private String getRolesForUser(UserDTO userDTO) {
        return userDTO.getRoles().stream().map(r -> r.getType() + " ").collect(Collectors.joining(","));
    }
}
