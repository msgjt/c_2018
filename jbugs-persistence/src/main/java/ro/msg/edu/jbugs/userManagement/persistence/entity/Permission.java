package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "permissions")
public class Permission {

    @Transient
    private final static int MAX_STRING_LENGTH = 20;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermission;

    @Column(name = "type", nullable = false, length = MAX_STRING_LENGTH, unique = true)
    private String type;

    @Column(name = "description")
    private String description;

    public Long getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Long idPermission) {
        this.idPermission = idPermission;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
