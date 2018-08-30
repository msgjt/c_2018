package ro.msg.edu.jbugs.userManagement.business.service.notification;

import ro.msg.edu.jbugs.userManagement.business.dto.notification.NotificationDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.NotificationDetail;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationType;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Date;

@Stateless
public class GenerateNotificationBusinessService {
    @EJB
    private GenerateNotificationMessageBusinessService messageGenerator;

    public NotificationDTO generateNotification(NotificationType type, NotificationDetail oldDetail, NotificationDetail newDetail, UserDTO userDTO) throws BusinessException {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setDate(new Date());
        notificationDTO.setType(type);
        notificationDTO.setOldNotificationDetail(oldDetail);
        notificationDTO.setNewNotificationDetail(newDetail);
        notificationDTO.setMessage(messageGenerator.generateMessage(oldDetail, newDetail, type));
        notificationDTO.setUsername(userDTO.getUsername());
        return notificationDTO;
    }
}
