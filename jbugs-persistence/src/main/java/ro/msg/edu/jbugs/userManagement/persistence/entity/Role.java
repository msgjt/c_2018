package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles")
@NamedQueries(
        {
                @NamedQuery(name = Role.GET_ALL_ROLES, query = "SELECT r FROM Role r")
        }
)
public class Role {

    @Transient
    private final static int MAX_STRING_LENGTH = 20;
    public static final String GET_ALL_ROLES = "get_all_roles";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    @Column(name = "type", length = MAX_STRING_LENGTH)
    private String type;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Permission> permissions;


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
}
