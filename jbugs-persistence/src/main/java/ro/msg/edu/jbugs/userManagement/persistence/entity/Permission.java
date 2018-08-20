package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Enumerated(EnumType.STRING)
    private PermissionEnum type;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "roles_permissions",
            joinColumns = { @JoinColumn(name = "id_permission") },
            inverseJoinColumns = { @JoinColumn(name = "id_role") })
    private List<Role> roles = new ArrayList<>();

    public Long getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(Long idPermission) {
        this.idPermission = idPermission;
    }

    public PermissionEnum getType() {
        return type;
    }

    public void setType(PermissionEnum type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
