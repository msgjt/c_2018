package ro.msg.edu.jbugs.userManagement.business.dto.user;

import java.util.List;

public class RoleDTO {

    private long id;
    private String type;
    private List<PermissionDTO> permissions;

    public RoleDTO() {
    }

    public RoleDTO(long id, String type, List<PermissionDTO> permissions) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<PermissionDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionDTO> permissions) {
        this.permissions = permissions;
    }
}
