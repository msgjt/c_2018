package ro.msg.edu.jbugs.business.service.notification;

import ro.msg.edu.jbugs.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.business.dto.notification.NotificationDTO;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.persistence.entity.NotificationEnum;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class GenerateNotificationService {
    @EJB
    private GenerateNotificationMessageService messageGenerator;
    @EJB
    private GenerateNotificationUsersService usersGenerator;
    @EJB
    private NotificationTypeService notificationTypeService;

    public NotificationDTO generateNotification(BugDTO oldBugDTO, BugDTO newBugDTO) throws BusinessException {
        NotificationEnum type = notificationTypeService.getNotificationType(oldBugDTO, newBugDTO);
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setType(type);
        notificationDTO.setMessage(messageGenerator.generateMessage(type, oldBugDTO, newBugDTO));
        notificationDTO.setDate(new Date());
        notificationDTO.setUsernames(usersGenerator.generateUsers(oldBugDTO, newBugDTO));
        return notificationDTO;
    }

    public NotificationDTO generateNotification(UserDTO oldUserDTO, UserDTO newUserDTO) throws BusinessException {
        NotificationEnum type = notificationTypeService.getNotificationType(oldUserDTO, newUserDTO);
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage(messageGenerator.generateMessage(type, oldUserDTO, newUserDTO));
        notificationDTO.setType(type);
        notificationDTO.setDate(new Date());
        notificationDTO.setUsernames(usersGenerator.generateUsers(type, oldUserDTO, newUserDTO));
        return notificationDTO;
    }

}
