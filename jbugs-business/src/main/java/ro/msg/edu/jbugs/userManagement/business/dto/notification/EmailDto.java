package ro.msg.edu.jbugs.userManagement.business.dto.notification;

public class EmailDto {
    private String message;
    private final String from = "c.forthewinn@gmail.com";
    private String to;
    private String subject;

    public EmailDto() {
    }

    public EmailDto(String message, String to, String subject) {
        this.message = message;
        this.to = to;
        this.subject = subject;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
