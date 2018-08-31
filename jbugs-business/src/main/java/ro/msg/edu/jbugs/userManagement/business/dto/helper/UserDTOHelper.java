package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.user.*;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.service.user.IUserBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.PermissionEnum;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class UserDTOHelper {
    @EJB
    private RoleDTOHelper roleDTOHelper;

    @EJB
    private IUserBusinessService userBusinessService;

    public UserDTO fromEntity(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getIdUser());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setUsername(user.getUsername());
        userDTO.setIsActive(user.getIsActive());

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
        user.setIsActive(userDTO.getIsActive());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        if (Optional.ofNullable(userDTO.getRoles()).isPresent())
            user.setRoles(userDTO.getRoles().stream().map(roleDTOHelper::toEntity).collect(Collectors.toSet()));
        else {
            user.setRoles(null);
        }
        return user;
    }

    //ToDo: make not static and make fictional;
    public UserSessionDTO toEntity(UserLoginDTO userLoginDTO) throws BusinessException {
        UserDTO userDTO = userBusinessService.getUserByUsername(userLoginDTO.getUsername());
        Set<PermissionEnum> permissions = new HashSet<>();
        userDTO.getRoles()
                .forEach(r -> permissions.addAll(r.getPermissions()
                        .stream()
                        .map(PermissionDTO::getType)
                        .collect(Collectors.toList())
                ));
        return new UserSessionDTO(userLoginDTO.getUsername(), permissions);
    }
}

