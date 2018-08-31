package ro.msg.edu.jbugs.userManagement.business.service.notification;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationType;

import javax.ejb.Stateless;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Stateless
public class GenerateNotificationUsersService {
    public Set<String> generateUsers(NotificationType type, BugDTO oldBugDTO, BugDTO newBugDTO) {
        if (oldBugDTO != null) {
            return new HashSet<>(Arrays.asList(oldBugDTO.getCreatedByUser().getUsername(),
                    oldBugDTO.getAssignedTo().getUsername(), newBugDTO.getAssignedTo().getUsername()));
        } else {
            return new HashSet<>(Arrays.asList(newBugDTO.getCreatedByUser().getUsername(),
                    newBugDTO.getAssignedTo().getUsername()));
        }
    }

    public Set<String> generateUsers(NotificationType type, UserDTO oldUserDTO, UserDTO newUserDTO) throws BusinessException {
        switch (type) {
            case WELCOME_NEW_USER:
                return new HashSet<>(Collections.singletonList(newUserDTO.getUsername()));
        }
        throw new BusinessException();
    }
}
