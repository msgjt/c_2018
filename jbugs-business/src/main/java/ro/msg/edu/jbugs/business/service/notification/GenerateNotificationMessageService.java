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
            case BUG_CREATED:
                return newBugNotification(newBugDTO);
            case BUG_UPDATED:
                return bugUpdatedNotification(oldBugDTO, newBugDTO);
            case BUG_STATUS_UPDATED:
                return bugStatusChangedNotification(oldBugDTO, newBugDTO);
            case BUG_CLOSED:
                return bugClosedNotification(oldBugDTO, newBugDTO);
        }
        throw new BusinessException(ExceptionCode.MESSAGE_GENRATITON_FAIL);
    }

    public String generateMessage(NotificationEnum type, UserDTO oldUserDTO, UserDTO newUserDTO) throws BusinessException {
        switch (type) {
            case WELCOME_NEW_USER:
                return welcomeNewUserNotification(newUserDTO);
            case USER_UPDATED:
                return updateUserNotification(oldUserDTO, newUserDTO);
            case USER_DEACTIVATED:
                return userDeactivated(newUserDTO);
        }
        throw new BusinessException(ExceptionCode.MESSAGE_GENRATITON_FAIL);
    }

    public String welcomeNewUserNotification(UserDTO newUserDTO) {
        return "bun venit " + newUserDTO.getUsername() + newUserDTO.getFirstName() + newUserDTO.getLastName();
    }

    private String newBugNotification(BugDTO bugDTO) {
        return "A new bug was created: " + bugDTO.toString() + ", status: " + bugDTO.getStatus();
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

    private String userDeactivated(UserDTO userDTO) {
        return "user: " + userDTO.getUsername() + " has been deactivated";
    }

    private String bugUpdatedNotification(BugDTO oldBugDTO, BugDTO newBugDTO) {
        return "a bug was updated: Title " + oldBugDTO.getTitle() + " is now " + newBugDTO.getTitle() +
                ",\n Version " + oldBugDTO.getVersion() + " is now " + newBugDTO.getVersion() +
                ",\n Fixed version " + oldBugDTO.getFixedVersion() + " is now " + newBugDTO.getFixedVersion() +
                ",\n Severity " + oldBugDTO.getSeverity() + " is now " + newBugDTO.getSeverity() +
                ",\n Status " + oldBugDTO.getStatus() + " is now " + newBugDTO.getStatus() +
                ",\n Assigned to " + oldBugDTO.getAssignedTo().getUsername() + " is now " + newBugDTO.getAssignedTo().getUsername() +
                ".";
    }

    private String bugStatusChangedNotification(BugDTO oldBugDTO, BugDTO newBugDTO) {
        return "a bug status was updated: Title " + oldBugDTO.getTitle() + " is now " + newBugDTO.getTitle() +
                ",\n Status " + oldBugDTO.getStatus() + " is now " + newBugDTO.getStatus() +
                ".";
    }

    private String bugClosedNotification(BugDTO oldBugDTO, BugDTO newBugDTO) {
        return "a bug was closed: Title " + oldBugDTO.getTitle() + " is now " + newBugDTO.getTitle() +
                ",\n Status " + oldBugDTO.getStatus() + " is now " + newBugDTO.getStatus() +
                ".";
    }
}
