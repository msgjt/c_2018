package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notifications")
@NamedQueries({
        @NamedQuery(name = Notification.GET_ALL_NOTIFICATIONS, query = "SELECT n FROM Notification n"),
})
public class Notification {
    public static final String GET_ALL_NOTIFICATIONS = "get_all_notifications";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;

    @Column(nullable = false)
    private NotificationType type;
    @Column(length = 1000)
    private String newNotificationDetailJson;
    @Column(length = 1000)
    private String oldNotificationDetailJson;
    private Date date;
    @OneToOne(cascade = CascadeType.DETACH)
    private User user;

    public Notification() {
    }

    public Notification(NotificationType type, String newNotificationDetailJson, String oldNotificationDetailJson, Date date, User user) {
        this.type = type;
        this.newNotificationDetailJson = newNotificationDetailJson;
        this.oldNotificationDetailJson = oldNotificationDetailJson;
        this.date = date;
        this.user = user;
    }

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getNewNotificationDetailJson() {
        return newNotificationDetailJson;
    }

    public void setNewNotificationDetailJson(String newNotificationDetailJson) {
        this.newNotificationDetailJson = newNotificationDetailJson;
    }

    public String getOldNotificationDetailJson() {
        return oldNotificationDetailJson;
    }

    public void setOldNotificationDetailJson(String oldNotificationDetailJson) {
        this.oldNotificationDetailJson = oldNotificationDetailJson;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
