package ro.msg.edu.jbugs.userManagement.business.dto.user;

import java.util.List;
import java.util.Set;

public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private boolean isActive;
    private String phoneNumber;
    private Set<RoleDTO> roles;


    public UserDTO() {
    }

    public UserDTO(long id, String firstName, String lastName, String username, String password, String email, boolean isActive, String phoneNumber, Set<RoleDTO> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.isActive = isActive;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<RoleDTO> getRoles() {

        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }
}
