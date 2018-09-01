package ro.msg.edu.jbugs.business.service.notification;

import ro.msg.edu.jbugs.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.business.dto.notification.NotificationDTO;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.persistence.entity.NotificationEnum;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

@Stateless
public class GenerateNotificationService {
    @EJB
    private GenerateNotificationMessageService messageGenerator;

    public NotificationDTO generateNotification(NotificationEnum type, BugDTO oldBugDTO, BugDTO newBugDTO) throws BusinessException {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setType(type);
        notificationDTO.setMessage(messageGenerator.generateMessage(type, newBugDTO));
        notificationDTO.setDate(new Date());
        if(oldBugDTO != null){
            notificationDTO.setUsernames(new HashSet<>(Arrays.asList(oldBugDTO.getCreatedByUser().getUsername(),
                    oldBugDTO.getAssignedTo().getUsername(), newBugDTO.getAssignedTo().getUsername())));
        }else{
            notificationDTO.setUsernames(new HashSet<>(Arrays.asList(newBugDTO.getCreatedByUser().getUsername(),
                    newBugDTO.getAssignedTo().getUsername())));
        }

        return notificationDTO;
    }

    public NotificationDTO generateNotification(NotificationEnum type, UserDTO newUserDTO) throws BusinessException {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setMessage(messageGenerator.generateMessage(type, newUserDTO));
        notificationDTO.setType(type);
        notificationDTO.setDate(new Date());
        notificationDTO.setUsernames(new HashSet<>(Collections.singletonList(newUserDTO.getUsername())));
        return notificationDTO;
    }

}
