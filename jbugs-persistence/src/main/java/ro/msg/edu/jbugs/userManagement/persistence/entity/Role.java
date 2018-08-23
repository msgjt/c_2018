package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@NamedQueries({
        @NamedQuery(name = Role.GET_ALL_ROLES, query = "SELECT r FROM Role r")
})
public class Role {
    @Transient
    private final static int MAX_STRING_LENGTH = 20;
    public static final String GET_ALL_ROLES = "get_all_roles";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Column(name = "type", length = MAX_STRING_LENGTH)
    @Enumerated(EnumType.STRING)
    private RoleEnum type;

    @ManyToMany
    private Set<Permission> permissions;

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public RoleEnum getType() {
        return type;
    }

    public void setType(RoleEnum type) {
        this.type = type;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

}
