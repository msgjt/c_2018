package ro.msg.edu.jbugs.userManagement.business.dto;

public class RoleDTO {

    private long id;
    private String type;

    public RoleDTO() {
    }

    public RoleDTO(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
