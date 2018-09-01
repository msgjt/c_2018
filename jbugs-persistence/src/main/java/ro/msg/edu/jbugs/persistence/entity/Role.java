package ro.msg.edu.jbugs.persistence.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@NamedQueries({
        @NamedQuery(name = Role.GET_ALL_ROLES, query = "SELECT r FROM Role r")
})
public class Role {
    public static final String GET_ALL_ROLES = "get_all_roles";
    @Transient
    private static final int MAX_STRING_LENGTH = 20;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Column(length = MAX_STRING_LENGTH)
    @Enumerated(EnumType.STRING)
    private RoleEnum type;

    @ManyToMany(cascade = CascadeType.DETACH)
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
