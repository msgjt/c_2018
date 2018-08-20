package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDot;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserSessionDto;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;


public class UserDTOHelper {

    public static UserDTO fromEntity(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        return user;
    }

    //ToDo: make not static and make fictional;
    public static UserSessionDto toEntity (UserLoginDot userLoginDot){
        List<PermissionEnum> permissions = new ArrayList<>();
        permissions.add(PermissionEnum.Test);
        return new UserSessionDto("doreld", permissions);
    }
}
