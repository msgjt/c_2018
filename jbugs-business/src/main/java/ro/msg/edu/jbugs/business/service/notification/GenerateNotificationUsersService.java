package ro.msg.edu.jbugs.business.service.notification;


import ro.msg.edu.jbugs.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.business.dto.notification.EmailDto;
import ro.msg.edu.jbugs.business.dto.user.PermissionDTO;
import ro.msg.edu.jbugs.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.business.service.user.IUserBusinessService;
import ro.msg.edu.jbugs.business.service.utils.Encryptor;
import ro.msg.edu.jbugs.persistence.entity.NotificationEnum;
import ro.msg.edu.jbugs.persistence.entity.PermissionEnum;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;
import java.util.stream.Collectors;

@Stateless
public class GenerateNotificationUsersService {
    @EJB
    private SendEmailBusinessService emailBusinessService;
    @EJB
    private IUserBusinessService userBusinessService;

    public Set<String> generateUsers(BugDTO oldBugDTO, BugDTO newBugDTO) throws BusinessException {
        if (oldBugDTO != null) {
            return new HashSet<>(Arrays.asList(oldBugDTO.getCreatedByUser().getUsername(),
                    oldBugDTO.getAssignedTo().getUsername(), newBugDTO.getAssignedTo().getUsername()));
        } else {
            return new HashSet<>(Arrays.asList(newBugDTO.getCreatedByUser().getUsername(),
                    newBugDTO.getAssignedTo().getUsername()));
        }
    }

    public Set<String> generateUsers(NotificationEnum type, UserDTO oldUserDTO, UserDTO newUserDTO) throws BusinessException {
        switch (type) {
            case WELCOME_NEW_USER:
                sendWelcomeEmail(newUserDTO);
                return new HashSet<>(Collections.singletonList(newUserDTO.getUsername()));
            case USER_UPDATED:
                return new HashSet<>(Collections.singletonList(newUserDTO.getUsername()));
            case USER_DEACTIVATED:
                return userBusinessService.getAllUsers()
                        .stream()
                        .filter(this::userHasUserManagement)
                        .map(UserDTO::getUsername)
                        .collect(Collectors.toSet());

        }
        throw new BusinessException(ExceptionCode.USER__GENRATITON_FAIL);
    }

    private void sendWelcomeEmail(UserDTO newUserDTO) throws BusinessException {
        EmailDto emailDto = new EmailDto();
        emailDto.setTo("florin.baciu20@gmail.com");
        emailDto.setSubject("Welcome");
        emailDto.setMessage("Welcome " + newUserDTO.getFirstName() + ", you have a JBugger account now" + "\nPlease Log In with your credentials and change the password from MyProfile" + ",\nusername: " + newUserDTO.getUsername() + ",\npassword: " + Encryptor.decrypt(newUserDTO.getPassword()));
        emailBusinessService.sendEmail(emailDto);
    }

    private boolean userHasUserManagement(UserDTO userDTO){
        Set<PermissionDTO> permissions = new HashSet<>();
        userDTO.getRoles().forEach(u -> permissions.addAll(u.getPermissions()));
        return permissions.stream().anyMatch(permissionDTO -> permissionDTO.getType() == PermissionEnum.USER_MANAGEMENT);
    }
}
