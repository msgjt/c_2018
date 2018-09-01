package ro.msg.edu.jbugs.business.dto.user;

import ro.msg.edu.jbugs.persistence.entity.PermissionEnum;

public class PermissionDTO {

    private long id;
    private String description;
    private PermissionEnum type;

    public PermissionDTO() {
    }

    public PermissionDTO(long id, String description, PermissionEnum type) {
        this.id = id;
        this.description = description;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PermissionEnum getType() {
        return type;
    }

    public void setType(PermissionEnum type) {
        this.type = type;
    }
}
