package ro.msg.edu.jbugs.userManagement.business.service.notification;

import ro.msg.edu.jbugs.userManagement.business.dto.notification.NotificationDetail;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.entity.NotificationType;

import javax.ejb.Stateless;

@Stateless
public class GenerateNotificationMessageBusinessService {
    public String generateMessage(NotificationDetail oldDetail, NotificationDetail newDetail, NotificationType type) throws BusinessException {
        switch (type) {
            case WELCOME_NEW_USER:
                return welcomeNewUserNotification(newDetail);
        }
        throw new BusinessException(ExceptionCode.MESSAGE_GENRATITON_FAIL);
    }

    public String welcomeNewUserNotification(NotificationDetail detail) {
        return "bun venit" + detail.getUser().getUsername() + detail.getUser().getFirstName() + detail.getUser().getLastName();
    }
}
