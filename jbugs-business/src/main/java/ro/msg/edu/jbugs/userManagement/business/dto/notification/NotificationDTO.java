package ro.msg.edu.jbugs.userManagement.business.dto.notification;

import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationType;

import java.util.Date;

public class NotificationDTO {
    private NotificationType type;
    private String message;
    private Date date;
    private NotificationDetail newNotificationDetail;
    private NotificationDetail oldNotificationDetail;
    private String username;

    public NotificationDTO() {
    }

    public NotificationDTO(NotificationType type, String message, Date date, NotificationDetail newNotificationDetail, NotificationDetail oldNotificationDetail, String username) {
        this.type = type;
        this.message = message;
        this.date = date;
        this.newNotificationDetail = newNotificationDetail;
        this.oldNotificationDetail = oldNotificationDetail;
        this.username = username;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public NotificationDetail getNewNotificationDetail() {
        return newNotificationDetail;
    }

    public void setNewNotificationDetail(NotificationDetail newNotificationDetail) {
        this.newNotificationDetail = newNotificationDetail;
    }

    public NotificationDetail getOldNotificationDetail() {
        return oldNotificationDetail;
    }

    public void setOldNotificationDetail(NotificationDetail oldNotificationDetail) {
        this.oldNotificationDetail = oldNotificationDetail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
