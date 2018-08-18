package ro.msg.edu.jbugs.userManagement.business.dto;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

public class RoleDTOHelper {

    public static RoleDTO fromEntity(Role role){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getIdRole());
        roleDTO.setType(role.getType());
        roleDTO.setPermissions(role.getPermissions());
        return roleDTO;
    }

    public static Role toEntity(RoleDTO roleDTO){
        Role role = new Role();
        role.setIdRole(roleDTO.getId());
        role.setType(roleDTO.getType());
        role.setPermissions(roleDTO.getPermissions());
        return role;
    }


}
