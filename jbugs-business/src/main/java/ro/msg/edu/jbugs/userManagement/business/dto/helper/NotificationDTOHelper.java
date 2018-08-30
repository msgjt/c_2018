package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import com.google.gson.Gson;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.NotificationDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.notification.NotificationDetail;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.service.notification.GenerateNotificationMessageBusinessService;
import ro.msg.edu.jbugs.userManagement.business.service.user.IUserBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Notification;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class NotificationDTOHelper {
    @EJB
    private GenerateNotificationMessageBusinessService messageGenerator;
    @EJB
    private UserDTOHelper userDTOHelper;
    @EJB
    private IUserBusinessService userBusinessService;

    public NotificationDTO fromEntity(Notification notification) throws BusinessException {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setDate(notification.getDate());
        notificationDTO.setType(notification.getType());
        notificationDTO.setMessage(messageGenerator.generateMessage(null, new Gson().fromJson(notification.getNewNotificationDetailJson(),
                NotificationDetail.class), notification.getType()));
        notificationDTO.setOldNotificationDetail(new Gson().fromJson(notification.getNewNotificationDetailJson(), NotificationDetail.class));
        notificationDTO.setUsername(notification.getUser().getUsername());
        return notificationDTO;
    }

    public Notification toEntity(NotificationDTO notificationDTO) throws BusinessException {
        Notification notification = new Notification();
        String newNotificationDetailJson = new Gson().toJson(notificationDTO.getNewNotificationDetail());
        notification.setNewNotificationDetailJson(newNotificationDetailJson);
        notification.setType(notificationDTO.getType());
        notification.setDate(notificationDTO.getDate());
        notification.setUser(userDTOHelper.toEntity(userBusinessService.getUserByUsername(notificationDTO.getUsername())));
        return notification;
    }
}
