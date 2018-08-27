package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserLoginDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.UserSessionDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class UserDTOHelper {
    @EJB
    private RoleDTOHelper roleDTOHelper;

    public UserDTO fromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getIdUser());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setActive(user.getActive());
        userDTO.setUsername(user.getUsername());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRoles(user.getRoles().stream().map(roleDTOHelper::fromEntity).collect(Collectors.toSet()));
        userDTO.setPassword(user.getPassword());
        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setIdUser(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setActive(userDTO.getActive());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        if(Optional.ofNullable(userDTO.getRoles()).isPresent())
            user.setRoles(userDTO.getRoles().stream().map(roleDTOHelper::toEntity).collect(Collectors.toSet()));
        else{
            user.setRoles(null);
        }
        return user;
    }

    //ToDo: make not static and make fictional;
    public UserSessionDTO toEntity(UserLoginDTO userLoginDTO) {
        Set<PermissionEnum> permissions = new HashSet<>();
        permissions.add(PermissionEnum.PERMISSION_MANAGEMENT);
        return new UserSessionDTO("doreld", permissions);
    }
}

