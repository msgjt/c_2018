package ro.msg.edu.jbugs.userManagement.business.service.notification;

import ro.msg.edu.jbugs.userManagement.business.dto.helper.NotificationDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.NotificationDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.NotificationDetail;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;
import ro.msg.edu.jbugs.userManagement.persistence.service.NotificationPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class NotificationBusinessService {
    @EJB
    private NotificationPersistenceService notificationPersistenceService;
    @EJB
    private GenerateNotificationBusinessService generateNotificationBusinessService;
    @EJB
    private NotificationDTOHelper notificationDTOHelper;

    public void generateNotification(NotificationType type, NotificationDetail oldDetail, NotificationDetail newDetail, UserDTO userDTO) throws BusinessException {
        NotificationDTO notificationDTO = generateNotificationBusinessService.generateNotification(type, oldDetail, newDetail, userDTO);
        notificationPersistenceService.createNotification(notificationDTOHelper.toEntity(notificationDTO));
    }

    public List<NotificationDTO> getNotificationsForUser(String username) {
        return notificationPersistenceService.getNotifications()
                .stream()
                .filter(notification -> notification.getUser().getUsername().equals(username))
                .map(notification -> {
                    try {
                        return notificationDTOHelper.fromEntity(notification);
                    } catch (BusinessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());
    }
}
