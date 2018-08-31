package ro.msg.edu.jbugs.userManagement.business.exceptions;

/**
 * Provides exception codes and description.
 */
public enum ExceptionCode {
    USER_VALIDATION_EXCEPTION(1000, "Validation Exception"),
    EMAIL_EXISTS_ALREADY(1001, "Email already exists Exception"),
    PASSWORD_NOT_VALID(1002, "Password not valid."),
    ROLE_VALIDATION_EXCEPTION(1004, "Validation Exception"),
    USERNAME_NOT_VALID(1003, "Username not valid"),
    USER_INACTIVATED(1006, "User has been inactivated by admin"),
    INVALID_USER_LOGIN(1007, "Invalid user"),
    EMAIL_FAIL(1008, "Email cod not be sent"),
    TOKEN_EXPIRED(1009, "Token is no longer valid"),
    PERMISSION_VALIDATION_EXCEPTION(1004, "Validation Exception"),
    BUG_VALIDATION_EXCEPTION(1010, "Validation Exception"),
    ATTACHMENT_VALIDATION_EXCEPTION(1011, "Validation Exception"),
    UNFINISHED_TASKS(2000, "Unfinished tasks for user Exception"),
    DESCRIPTION_VALIDATION_EXCEPTION(2001,"Description length is too large"),
    MESSAGE_GENRATITON_FAIL(1015, "Can not generate notification message"),
    COMMENT_VALIDATION_EXCEPTION(2002,"Comment text too long");
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
