package ro.msg.edu.jbugs.persistence.exceptions;

/**
 * Provides exception codes and description.
 */
public enum ExceptionCode {
    ROLE_NOT_FOUND_EXCEPTION(1001,"Role not found Exception");

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
