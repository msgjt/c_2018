package ro.msg.edu.jbugs.userManagement.business.service.notification;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.NotificationDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class GenerateNotificationService {
    @EJB
    private GenerateNotificationMessageService messageGenerator;
    @EJB
    private GenerateNotificationUsersService generateNotificationUsersService;


    public NotificationDTO generateNotification(NotificationType type, BugDTO oldBugDTO, BugDTO newBugDTO) throws BusinessException {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setType(type);
        notificationDTO.setMessage(messageGenerator.generateMessage(type, oldBugDTO, newBugDTO));
        notificationDTO.setDate(new Date());
        notificationDTO.setUsernames(generateNotificationUsersService.generateUsers(type, oldBugDTO, newBugDTO));
        return notificationDTO;
    }

    public NotificationDTO generateNotification(NotificationType type, UserDTO oldUserDTO, UserDTO newUserDTO) throws BusinessException {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage(messageGenerator.generateMessage(type, oldUserDTO, newUserDTO));
        notificationDTO.setType(type);
        notificationDTO.setDate(new Date());
        notificationDTO.setUsernames(generateNotificationUsersService.generateUsers(type, oldUserDTO, newUserDTO));
        return notificationDTO;
    }

}
