package ro.msg.edu.jbugs.userManagement.business.dto.user;


public class UserLoginDot {
    private String userName;
    private String password;

    public UserLoginDot() {
    }

    public UserLoginDot(String userName, String password) {
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
}
