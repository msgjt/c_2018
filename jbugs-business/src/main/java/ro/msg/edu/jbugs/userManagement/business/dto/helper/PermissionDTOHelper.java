package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PermissionDTOHelper {
    public static PermissionDTO fromEntity(Permission permission){
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(permission.getIdPermission());
        permissionDTO.setType(permission.getType());
        permissionDTO.setDescription(permission.getDescription());
        permissionDTO.setRoles(permission.getRoles());
        permissionDTO.setRolesList(permission.getRoles().stream().map(x->x.getType()).collect(Collectors.toList()));
        return permissionDTO;
    }

    public static Permission toEntity(PermissionDTO permissionDTO){
        Permission permission = new Permission();
        permission.setType(permissionDTO.getType());
        permission.setIdPermission(permissionDTO.getId());
        permission.setDescription(permissionDTO.getDescription());
        permission.setRoles(permissionDTO.getRoles());
        return permission;
    }
}
