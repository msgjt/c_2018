package ro.msg.edu.jbugs.persistence.exceptions;

public class PersistenceException extends Exception {

    private ExceptionCode exceptionCode;

    public PersistenceException() {
    }
    public PersistenceException(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
    public PersistenceException(String message, ExceptionCode exceptionCode ) {
        super(message);
        this.exceptionCode = exceptionCode;
    }

    public PersistenceException(String message, Throwable cause, ExceptionCode exceptionCode) {
        super(message, cause);
        this.exceptionCode = exceptionCode;
    }

    public PersistenceException(Throwable cause, ExceptionCode exceptionCode) {
        super(cause);
        this.exceptionCode = exceptionCode;
    }

    public PersistenceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ExceptionCode exceptionCode) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionCode = exceptionCode;
    }

    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(ExceptionCode exceptionCode) {
        this.exceptionCode = exceptionCode;
    }
}
