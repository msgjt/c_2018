package ro.msg.edu.jbugs.persistence.exceptions;

/**
 * Provides exception codes and description.
 */
public enum ExceptionCode {
    ROLE_NOT_FOUND_EXCEPTION(1001,"Role not found Exception"),
    EMAIL_NOT_FOUND_EXCEPTION(1002, "Email not found Exception"),
    USERNAME_NOT_FOUND_EXCEPTION(1003, "Username not found Exception"),
    USER_NOT_FOUND_EXCEPTION(1004, "User not found exception");

    private int id;
    String message;

    ExceptionCode(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }




}
