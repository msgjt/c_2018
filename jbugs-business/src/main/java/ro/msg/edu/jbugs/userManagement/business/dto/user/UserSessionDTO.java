package ro.msg.edu.jbugs.userManagement.business.dto.user;

import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;

import java.util.List;

public class UserSessionDTO {
    private String username;
    private List<PermissionEnum> permissions;

    public UserSessionDTO() {
    }

    public UserSessionDTO(String username, List<PermissionEnum> permissions) {
        this.username = username;
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<PermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionEnum> permissions) {
        this.permissions = permissions;
    }
}
