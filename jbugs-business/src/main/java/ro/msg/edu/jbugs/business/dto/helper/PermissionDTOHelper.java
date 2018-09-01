package ro.msg.edu.jbugs.business.dto.helper;

import ro.msg.edu.jbugs.business.dto.user.PermissionDTO;
import ro.msg.edu.jbugs.persistence.entity.Permission;

import javax.ejb.Stateless;

@Stateless
public class PermissionDTOHelper {
    public PermissionDTO fromEntity(Permission permission) {
        PermissionDTO permissionDTO = new PermissionDTO();
        permissionDTO.setId(permission.getIdPermission());
        permissionDTO.setType(permission.getType());
        permissionDTO.setDescription(permission.getDescription());
        return permissionDTO;
    }

    public Permission toEntity(PermissionDTO permissionDTO) {
        Permission permission = new Permission();
        permission.setType(permissionDTO.getType());
        permission.setIdPermission(permissionDTO.getId());
        permission.setDescription(permissionDTO.getDescription());
        return permission;
    }
}
