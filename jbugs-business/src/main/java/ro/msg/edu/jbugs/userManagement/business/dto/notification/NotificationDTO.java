package ro.msg.edu.jbugs.userManagement.business.dto.notification;

import com.google.gson.annotations.Expose;
import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationEnum;

import java.util.Date;
import java.util.Set;

public class NotificationDTO {
    @Expose
    private NotificationEnum type;
    @Expose
    private String message;
    @Expose
    private Date date;
    @Expose(serialize = false)
    private Set<String> usernames;
    @Expose
    private Long bugId;

    public NotificationDTO() {
    }

    public NotificationDTO(NotificationEnum type, String message, Date date, Set<String> usernames, Long bugId) {
        this.type = type;
        this.message = message;
        this.date = date;
        this.usernames = usernames;
        this.bugId = bugId;
    }

    public NotificationEnum getType() {
        return type;
    }

    public void setType(NotificationEnum type) {
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

    public Set<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(Set<String> usernames) {
        this.usernames = usernames;
    }

    public Long getBugId() {
        return bugId;
    }

    public void setBugId(Long bugId) {
        this.bugId = bugId;
    }
}
