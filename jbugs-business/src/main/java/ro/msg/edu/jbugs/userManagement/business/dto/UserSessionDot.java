package ro.msg.edu.jbugs.userManagement.business.dto;


import java.util.List;

public class UserSessionDot {
    private String userName;
    private String password;
    private List<PermissionDTO> permissionDTOS;

    public UserSessionDot() {
    }

    public UserSessionDot(String userName, String password, List<PermissionDTO> permissionDTOS) {
        this.userName = userName;
        this.password = password;
        this.permissionDTOS = permissionDTOS;
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

    public List<PermissionDTO> getPermissionDTOS() {
        return permissionDTOS;
    }

    public void setPermissionDTOS(List<PermissionDTO> permissionDTOS) {
        this.permissionDTOS = permissionDTOS;
    }
}
