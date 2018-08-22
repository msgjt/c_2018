package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;

public class PermissionDTOHelper {
    public static PermissionDTO fromEntity(Permission permission) {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(permission.getIdPermission());
        permissionDTO.setType(permission.getType());
        permissionDTO.setDescription(permission.getDescription());
        return permissionDTO;
    }

    public static Permission toEntity(PermissionDTO permissionDTO) {
        Permission permission = new Permission();
        permission.setType(permissionDTO.getType());
        permission.setIdPermission(permissionDTO.getId());
        permission.setDescription(permissionDTO.getDescription());
        return permission;
    }
}
