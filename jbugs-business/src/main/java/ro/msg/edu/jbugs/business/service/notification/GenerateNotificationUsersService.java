package ro.msg.edu.jbugs.business.service.notification;


import ro.msg.edu.jbugs.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.business.dto.notification.EmailDto;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.persistence.entity.NotificationEnum;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class GenerateNotificationUsersService {
    @EJB
    private SendEmailBusinessService emailBusinessService;


    public Set<String> generateUsers(BugDTO oldBugDTO, BugDTO newBugDTO) throws BusinessException {
        if (oldBugDTO != null) {
            return new HashSet<>(Arrays.asList(oldBugDTO.getCreatedByUser().getUsername(),
                    oldBugDTO.getAssignedTo().getUsername(), newBugDTO.getAssignedTo().getUsername()));
        } else {
            new HashSet<>(Arrays.asList(newBugDTO.getCreatedByUser().getUsername(),
                    newBugDTO.getAssignedTo().getUsername()));
        }
        throw new BusinessException(ExceptionCode.USER__GENRATITON_FAIL);
    }

    public Set<String> generateUsers(NotificationEnum type, UserDTO oldUserDTO, UserDTO newUserDTO, String creator) throws BusinessException {
        switch (type) {
            case WELCOME_NEW_USER:
                sendWelcomeEmail(newUserDTO);
                return new HashSet<>(Collections.singletonList(newUserDTO.getUsername()));
            case USER_UPDATED:
                return new HashSet<>(Arrays.asList(newUserDTO.getUsername(), creator));
        }
        throw new BusinessException(ExceptionCode.USER__GENRATITON_FAIL);
    }

    private void sendWelcomeEmail(UserDTO newUserDTO) throws BusinessException {
        EmailDto emailDto = new EmailDto();
        emailDto.setTo(newUserDTO.getEmail());
        emailDto.setSubject("Welcome");
        emailDto.setMessage("welcome " + newUserDTO.getFirstName() + ",\npassword: " + newUserDTO.getPassword());
        emailBusinessService.sendEmail(emailDto);
    }
}
