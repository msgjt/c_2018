package ro.msg.edu.jbugs.business.service.notification;

import ro.msg.edu.jbugs.business.dto.notification.NotificationDTO;
import ro.msg.edu.jbugs.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.business.dto.helper.NotificationDTOHelper;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.persistence.entity.NotificationEnum;
import ro.msg.edu.jbugs.persistence.service.notification.INotificationPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class NotificationBusinessService {
    @EJB
    private INotificationPersistenceService INotificationPersistenceService;
    @EJB
    private GenerateNotificationService generateNotificationService;
    @EJB
    private NotificationDTOHelper notificationDTOHelper;

    public void generateNotification(NotificationEnum type, BugDTO oldBugDTO, BugDTO newBugDTO) throws BusinessException {
        INotificationPersistenceService.createNotification(notificationDTOHelper.toEntity(generateNotificationService.generateNotification(type, oldBugDTO, newBugDTO)));
        deleteOutdatedNotifications();
    }

    public void generateNotification(NotificationEnum type, UserDTO oldUserDTO, UserDTO newUserDTO, String creator) throws BusinessException {
        INotificationPersistenceService.createNotification(notificationDTOHelper.toEntity(generateNotificationService.generateNotification(type, oldUserDTO, newUserDTO, creator)));
        deleteOutdatedNotifications();
    }

    public List<NotificationDTO> getNotificationsForUser(String username) {
        return INotificationPersistenceService.getNotifications()
                .stream()
                .map(notificationDTOHelper::fromEntity)
                .filter(n -> n.getUsernames().contains(username))
                .sorted(Comparator.comparing(NotificationDTO::getDate))
                .collect(Collectors.toList());
    }

    private void deleteOutdatedNotifications() {
        INotificationPersistenceService.getNotifications()
                .stream()
                .filter(u -> u.getDate().compareTo(Date.from(Instant.now().minusSeconds(2592000L))) < 0)
                .forEach(INotificationPersistenceService::deleteNotification);
    }
}
