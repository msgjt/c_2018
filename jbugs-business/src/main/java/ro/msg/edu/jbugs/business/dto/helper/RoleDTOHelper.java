package ro.msg.edu.jbugs.business.dto.helper;

import ro.msg.edu.jbugs.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.persistence.entity.Role;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.stream.Collectors;

@Stateless
public class RoleDTOHelper {
    @EJB
    private PermissionDTOHelper permissionDTOHelper;

    public RoleDTO fromEntity(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getIdRole());
        roleDTO.setType(role.getType());
        roleDTO.setPermissions(role.getPermissions().stream().map(permissionDTOHelper::fromEntity).collect(Collectors.toSet()));
        return roleDTO;
    }

    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();
        role.setIdRole(roleDTO.getId());
        role.setType(roleDTO.getType());
        role.setPermissions(roleDTO.getPermissions().stream().map(permissionDTOHelper::toEntity).collect(Collectors.toSet()));
        return role;
    }

}
