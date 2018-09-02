package ro.msg.edu.jbugs.business.dto.helper;

import ro.msg.edu.jbugs.business.dto.notification.NotificationDTO;
import ro.msg.edu.jbugs.business.service.bug.IBugBusinessService;
import ro.msg.edu.jbugs.business.service.user.IUserBusinessService;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.persistence.entity.Notification;
import ro.msg.edu.jbugs.persistence.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
public class NotificationDTOHelper {
    @EJB
    private UserDTOHelper userDTOHelper;
    @EJB
    private IUserBusinessService userBusinessService;
    @EJB
    private IBugBusinessService bugBusinessService;
    @EJB
    private BugDTOHelper bugDTOHelper;

    public NotificationDTO fromEntity(Notification notification) {
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setDate(notification.getDate());
        notificationDTO.setType(notification.getType());
        if (notification.getUsers() != null)
            notificationDTO.setUsernames(notification.getUsers().stream().map(User::getUsername).collect(Collectors.toSet()));
        if (notification.getBug() != null)
            notificationDTO.setBugId(notification.getBug().getIdBug());
        notificationDTO.setMessage(notification.getMessage());
        return notificationDTO;
    }

    public Notification toEntity(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        notification.setDate(notificationDTO.getDate());
        notification.setType(notificationDTO.getType());
        if (notificationDTO.getBugId() != null)
           notification.setBug(bugDTOHelper.toEntity(bugBusinessService.findBugById(notificationDTO.getBugId())));
        if (notificationDTO.getUsernames() != null)
            notification.setUsers(notificationDTO.getUsernames()
                    .stream()
                    .map(this::getUserByUsername)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toSet()));
        notification.setMessage(notificationDTO.getMessage());
        return notification;
    }

    private User getUserByUsername(String username) {
        try {
            return userDTOHelper.toEntity(userBusinessService.getUserByUsername(username));
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
