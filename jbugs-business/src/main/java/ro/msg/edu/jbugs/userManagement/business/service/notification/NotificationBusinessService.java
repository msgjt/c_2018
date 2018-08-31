package ro.msg.edu.jbugs.userManagement.business.service.notification;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.NotificationDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.NotificationDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationType;
import ro.msg.edu.jbugs.userManagement.persistence.service.NotificationPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class NotificationBusinessService {
    @EJB
    private NotificationPersistenceService notificationPersistenceService;
    @EJB
    private GenerateNotificationService generateNotificationService;
    @EJB
    private NotificationDTOHelper notificationDTOHelper;

    public void generateNotification(NotificationType type, BugDTO oldBugDTO, BugDTO newBugDTO) throws BusinessException {
        notificationPersistenceService.createNotification(notificationDTOHelper.toEntity(generateNotificationService.generateNotification(type, oldBugDTO, newBugDTO)));
    }

    public void generateNotification(NotificationType type, UserDTO oldUserDTO, UserDTO newUserDTO) throws BusinessException {
        notificationPersistenceService.createNotification(notificationDTOHelper.toEntity(generateNotificationService.generateNotification(type, oldUserDTO, newUserDTO)));
    }

    public List<NotificationDTO> getNotificationsForUser(String username) {
        return notificationPersistenceService.getNotifications()
                .stream()
                .map(notificationDTOHelper::fromEntity)
                .filter(u -> u.getUsernames().contains(username))
                .sorted(Comparator.comparing(NotificationDTO::getDate))
                .collect(Collectors.toList());
    }
}
