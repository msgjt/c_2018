package ro.msg.edu.jbugs.userManagement.business.dto.notification;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;

public class NotificationDetail {
    private UserDTO user;
    private BugDTO bug;

    public NotificationDetail() {
    }

    public NotificationDetail(UserDTO user, BugDTO bug) {
        this.user = user;
        this.bug = bug;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public BugDTO getBug() {
        return bug;
    }

    public void setBug(BugDTO bug) {
        this.bug = bug;
    }
}
