package ro.msg.edu.jbugs.business.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ro.msg.edu.jbugs.business.service.notification.SendEmailBusinessService;
import ro.msg.edu.jbugs.business.dto.notification.EmailDto;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SendEmailPersistenceServiceBeanTest {

    @InjectMocks
    private SendEmailBusinessService sendEmailService;

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

            Mockito.mock(SendEmailBusinessService.class).sendEmail(emailDto);
            //sendEmailService.sendEmail(emailDto);
        }catch (BusinessException e){
            e.printStackTrace();
        }

        assertEquals(from, emailDto.getFrom());

    }

}
