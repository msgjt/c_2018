package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    @ManyToMany(cascade = CascadeType.DETACH)
    private Set<User> users;
    @ManyToOne(cascade = CascadeType.DETACH)
    private Bug bug;
    @Column(nullable = false)
    private NotificationType type;
    private Date date;
    private String message;

    public Notification() {

    }

    public Notification(Set<User> users, Bug bug, NotificationType type, Date date, String message) {
        this.users = users;
        this.bug = bug;
        this.type = type;
        this.date = date;
        this.message = message;
    }

    public Long getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Long idNotification) {
        this.idNotification = idNotification;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
