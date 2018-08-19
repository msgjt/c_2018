package ro.msg.edu.jbugs.userManagement.business.dto.user;

import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;

import java.util.List;

public class UserSessionDto {
    private String userName;
    private List<PermissionEnum> permissions;

    public UserSessionDto() {
    }

    public UserSessionDto(String userName, List<PermissionEnum> permissions) {
        this.userName = userName;
        this.permissions = permissions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<PermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionEnum> permissions) {
        this.permissions = permissions;
    }
}
