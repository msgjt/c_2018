package ro.msg.edu.jbugs.persistence.service.notification;

import ro.msg.edu.jbugs.persistence.entity.Notification;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Stateless
public class NotificationPersistenceService implements INotificationPersistenceService {
    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    @Override
    public Optional<Notification> updateNotificaction(@NotNull Notification notification) {
        return Optional.of(em.merge(notification));
    }

    @Override
    public void createNotification(@NotNull Notification notification) {
        em.persist(notification);
        em.flush();
    }

    @Override
    public void deleteNotification(@NotNull Notification notification) {
        em.remove(notification);
    }

    @Override
    public Set<Notification> getNotifications() {
        return new HashSet<>();
    }
}
