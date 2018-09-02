package ro.msg.edu.jbugs.business.service.notification;

import ro.msg.edu.jbugs.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.persistence.entity.NotificationEnum;
import ro.msg.edu.jbugs.persistence.entity.StatusEnum;

import javax.ejb.Stateless;

@Stateless
public class NotificationTypeService {
    public NotificationEnum getNotificationType(BugDTO oldBugDTO, BugDTO newBugDTO) {
        if (oldBugDTO == null)
            return NotificationEnum.BUG_CREATED;
        if (oldBugDTO.getStatus() != StatusEnum.CLOSED && newBugDTO.getStatus() == StatusEnum.CLOSED)
            return NotificationEnum.BUG_CLOSED;
        if (oldBugDTO.getStatus() != newBugDTO.getStatus())
            return NotificationEnum.BUG_STATUS_UPDATED;
        return NotificationEnum.BUG_UPDATED;


    }

    public NotificationEnum getNotificationType(UserDTO oldUserDTO, UserDTO newUserDTO) {
        if (oldUserDTO == null)
            return NotificationEnum.WELCOME_NEW_USER;
        if (oldUserDTO.getIsActive() && !newUserDTO.getIsActive())
            return NotificationEnum.USER_DEACTIVATED;
        return NotificationEnum.USER_UPDATED;
    }
}
