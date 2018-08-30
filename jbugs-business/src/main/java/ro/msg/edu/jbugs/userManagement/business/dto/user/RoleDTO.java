package ro.msg.edu.jbugs.userManagement.business.dto.user;

import ro.msg.edu.jbugs.userManagement.persistence.entity.RoleType;

import java.util.Set;

public class RoleDTO {

    private long id;
    private RoleType type;
    private Set<PermissionDTO> permissions;

    public RoleDTO() {
    }

    public RoleDTO(long id, RoleType type, Set<PermissionDTO> permissions) {
        this.id = id;
        this.type = type;
        this.permissions = permissions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public Set<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
