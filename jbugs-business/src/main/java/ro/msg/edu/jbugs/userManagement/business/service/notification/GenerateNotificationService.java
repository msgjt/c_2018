package ro.msg.edu.jbugs.userManagement.business.service.notification;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.NotificationDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

@Stateless
public class GenerateNotificationService {
    @EJB
    private GenerateNotificationMessageService messageGenerator;

    public NotificationDTO generateNotification(NotificationType type, BugDTO oldBugDTO, BugDTO newBugDTO) throws BusinessException {
        //ToDo: implement for bug
        return null;
    }

    public NotificationDTO generateNotification(NotificationType type, UserDTO oldUserDTO, UserDTO newUserDTO) throws BusinessException {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage(messageGenerator.generateMessage(type, oldUserDTO, newUserDTO));
        notificationDTO.setType(type);
        notificationDTO.setDate(new Date());
        notificationDTO.setUsernames(new HashSet<>(Collections.singletonList(newUserDTO.getUsername())));
        return notificationDTO;
    }
}
