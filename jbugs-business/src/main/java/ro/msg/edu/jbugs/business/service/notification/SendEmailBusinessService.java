package ro.msg.edu.jbugs.business.service.notification;

import ro.msg.edu.jbugs.business.dto.notification.EmailDto;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.exceptions.ExceptionCode;

import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Stateless
public class SendEmailBusinessService {
    private static final String USERNAME = "c.forthewinn@gmail.com";
    private static final String PASS = "valoare1";

    public void sendEmail(EmailDto emailDto) throws BusinessException {
        Properties props = setEmailProps();
        Session session = getEmailSession(props);
        Message message = setEmailMessage(emailDto, session);
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            throw new BusinessException(ExceptionCode.EMAIL_FAIL);
        }

    }

    private Properties setEmailProps() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        return props;
    }

    private Session getEmailSession(Properties props) {
        return Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASS);
                    }
                });
    }

    private Message setEmailMessage(EmailDto emailDto, Session session) throws BusinessException {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailDto.getFrom()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(emailDto.getTo()));
            message.setSubject(emailDto.getSubject());
            message.setText(emailDto.getMessage());
            return message;
        } catch (MessagingException e) {
            throw new BusinessException(ExceptionCode.EMAIL_FAIL);
        }
    }
}
