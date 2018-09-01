package ro.msg.edu.jbugs.persistence.service.notification;

import ro.msg.edu.jbugs.persistence.entity.Notification;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

public interface INotificationPersistenceService {
    Optional<Notification> updateNotificaction(@NotNull Notification notification);

    void createNotification(@NotNull Notification notification);

    void deleteNotification(@NotNull Notification notification);

    Set<Notification> getNotifications();
}
