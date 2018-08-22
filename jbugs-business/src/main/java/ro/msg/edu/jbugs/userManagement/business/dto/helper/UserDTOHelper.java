package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserSessionDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class UserDTOHelper {

    public static UserDTO fromEntity(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getIdUser());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRoles(user.getRoles());
        userDTO.setPassword(user.getPassword());
        userDTO.setRolesList(user.getRoles().stream().map(x -> x.getType()).collect(Collectors.toList()));
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO){
        User user = new User();
        user.setIdUser(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRoles(userDTO.getRoles());
        return user;
    }

    //ToDo: make not static and make fictional;
    public static UserSessionDTO toEntity (UserLoginDTO userLoginDTO){
        List<PermissionEnum> permissions = new ArrayList<>();
        permissions.add(PermissionEnum.PERMISSION_MANAGEMENT);
        return new UserSessionDTO("doreld", permissions);
    }
}

