package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "users_notifications")
public class UserNotification {

    @Id
    @ManyToOne
    @JoinColumn(name = "idNotification")
    private Notification notification;

    @Id
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @Column(name = "date")
    private Date date;

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
