package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private String type;

    @ManyToMany(mappedBy = "roles")
    private List<Permission> permissions = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "id_role")},
            inverseJoinColumns = {@JoinColumn(name = "id_user")})
    private List<User> users = new ArrayList<>();

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
        permission.getRoles().add(this);
    }

    public void removePermission(Permission permission) {
        this.permissions.removeIf(e -> e.getIdPermission().equals(permission.getIdPermission()));
        permission.getRoles().removeIf(e -> e.getIdRole().equals(this.idRole));
    }

    public static String getGetAllRoles() {
        return GET_ALL_ROLES;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
