package ro.msg.edu.jbugs.userManagement.business.dto.user;

import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;

import java.util.Set;

public class UserSessionDTO {
    private String userName;
    private Set<PermissionEnum> permissions;
    private String username;
    private List<PermissionEnum> permissions;

    public UserSessionDTO() {
    }

    public UserSessionDTO(String username, Set<PermissionEnum> permissions) {
        this.username = username;
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<PermissionEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEnum> permissions) {
        this.permissions = permissions;
    }
}
