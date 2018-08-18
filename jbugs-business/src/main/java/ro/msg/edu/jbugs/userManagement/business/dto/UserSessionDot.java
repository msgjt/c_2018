package ro.msg.edu.jbugs.userManagement.business.dto;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;

import java.util.List;

public class UserSessionDot {
    private String userName;
    private String password;

    public UserSessionDot() {
    }

    public UserSessionDot(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserSessionDot{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
