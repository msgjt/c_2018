package ro.msg.edu.jbugs.userManagement.business.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.EmailDto;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.service.notification.SendEmailService;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SendEmailPersistenceServiceBeanTest {

    @InjectMocks
    private SendEmailService sendEmailService;

//    @Mock
//    private EmailDto emailDto;

    @Test
    public void sendEmail_Expected(){
        String from="c.forthewinn@gmail.com";
        String to="manea.bianca9@yahoo.ro";
        String subject="subject";
        String message="message 1";
        EmailDto emailDto=new EmailDto(message,to,subject);


        try {

            Mockito.mock(SendEmailService.class).sendEmail(emailDto);
            //sendEmailService.sendEmail(emailDto);
        }catch (BusinessException e){
            e.printStackTrace();
        }

        assertEquals(from, emailDto.getFrom());

    }

}
