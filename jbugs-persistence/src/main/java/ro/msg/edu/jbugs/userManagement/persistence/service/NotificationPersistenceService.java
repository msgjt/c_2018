package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Notification;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Stateless
public class NotificationPersistenceService {
    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    public Optional<Notification> updateNotificaction(@NotNull Notification notification) {
        return Optional.of(em.merge(notification));
    }

    public void createNotification(@NotNull Notification notification) {
        em.persist(notification);
        em.flush();
    }

    public void deleteNotification(@NotNull Notification notification) {
        em.remove(notification);
    }

    public Set<Notification> getNotifications() {
        return new HashSet<>(em.createNamedQuery(Notification.GET_ALL_NOTIFICATIONS, Notification.class).getResultList());
    }
}
