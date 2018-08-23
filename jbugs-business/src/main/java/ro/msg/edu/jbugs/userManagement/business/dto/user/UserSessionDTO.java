package ro.msg.edu.jbugs.userManagement.business.dto.user;

import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;

import java.util.Set;

public class UserSessionDTO {
    private String userName;
    private Set<PermissionEnum> permissions;

    public UserSessionDTO() {
    }

    public UserSessionDTO(String userName, Set<PermissionEnum> permissions) {
        this.userName = userName;
        this.permissions = permissions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<PermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEnum> permissions) {
        this.permissions = permissions;
    }
}
