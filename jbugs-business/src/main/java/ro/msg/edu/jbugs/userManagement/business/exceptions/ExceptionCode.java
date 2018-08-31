package ro.msg.edu.jbugs.userManagement.business.exceptions;

/**
 * Provides exception codes and description.
 */
public enum ExceptionCode {
    USER_VALIDATION_EXCEPTION(1000, "User validation Exception"),
    EMAIL_EXISTS_ALREADY(1001, "Email already exists Exception"),
    PASSWORD_NOT_VALID(1002, "Password not valid."),
    ROLE_VALIDATION_EXCEPTION(1004, "Role validation Exception"),
    USERNAME_NOT_VALID(1003, "Username not valid"),
    USER_INACTIVATED(1006, "User has been inactivated by admin"),
    INVALID_USER_LOGIN(1007, "Invalid user"),
    EMAIL_FAIL(1008, "Email cod not be sent"),
    TOKEN_EXPIRED(1009, "Token is no longer valid"),
    PERMISSION_VALIDATION_EXCEPTION(1004, "Permission validation Exception"),
    BUG_VALIDATION_EXCEPTION(1010, "Bug validation Exception"),
    ATTACHMENT_VALIDATION_EXCEPTION(1011, "Attachment validation Exception"),
    UNFINISHED_TASKS(2000, "Unfinished tasks for user Exception");
    int id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
