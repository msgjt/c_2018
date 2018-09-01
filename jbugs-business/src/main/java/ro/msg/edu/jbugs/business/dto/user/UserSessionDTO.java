package ro.msg.edu.jbugs.business.dto.user;

import ro.msg.edu.jbugs.persistence.entity.PermissionEnum;

import java.util.Set;

public class UserSessionDTO {
    private Set<PermissionEnum> permissions;
    private String username;

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
