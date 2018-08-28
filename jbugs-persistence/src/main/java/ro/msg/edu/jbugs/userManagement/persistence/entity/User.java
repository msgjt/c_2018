package ro.msg.edu.jbugs.userManagement.persistence.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@NamedQueries(
        {
                @NamedQuery(name = User.GET_ALL_USERS, query = "SELECT u FROM User u"),
                @NamedQuery(name = User.GET_USER_BY_USERNAME, query = "SELECT u FROM User u WHERE u.username=:username"),
                @NamedQuery(name = User.GET_USER_BY_EMAIL, query = "SELECT u from User u where u.email = :email "),
        }
)
public class User {
    @Transient
    private final static int MAX_STRING_LENGTH = 40;
    public static final String GET_ALL_USERS = "get_All_Users";
    public static final String GET_USER_BY_USERNAME = "get_User_By_Username";
    public static final String GET_USER_BY_EMAIL = "get_User_By_Email";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(length = MAX_STRING_LENGTH, nullable = false)
    private String firstName;

    @Column(length = MAX_STRING_LENGTH, nullable = false)
    private String lastName;

    @Column(length = MAX_STRING_LENGTH, nullable = false)
    private String phoneNumber;

    @Column(length = MAX_STRING_LENGTH, nullable = false, unique = true)
    private String email;

    @Column(length = MAX_STRING_LENGTH, nullable = false, unique = true)
    private String username;

    @Column(length = MAX_STRING_LENGTH)
    private String password;

    @Column(length = MAX_STRING_LENGTH, nullable = false)
    private boolean isActive;

    @ManyToMany(cascade = CascadeType.DETACH)
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserNotification> userNotifications;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<UserNotification> getUserNotifications() {
        return userNotifications;
    }

    public void setUserNotifications(List<UserNotification> userNotifications) {
        this.userNotifications = userNotifications;
    }
}
