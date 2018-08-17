package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "notifications")
public class Notification {
    @Transient
    private final static int MAX_STRING_LENGTH = 40;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;

    @Column(name = "type", length = MAX_STRING_LENGTH, nullable = false)
    private String type;

    @Column(name = "message", length = MAX_STRING_LENGTH, nullable = false)
    private String message;

    @OneToMany(mappedBy = "notification")
    private List<UserNotification> userNotifications;

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UserNotification> getUserNotifications() {
        return userNotifications;
    }

    public void setUserNotifications(List<UserNotification> userNotifications) {
        this.userNotifications = userNotifications;
    }
}
