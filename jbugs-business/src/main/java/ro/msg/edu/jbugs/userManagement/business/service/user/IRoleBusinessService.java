package ro.msg.edu.jbugs.userManagement.business.service.user;

import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;

import java.util.List;
import java.util.Set;

public interface IRoleBusinessService {
    RoleDTO getRoleById(long id);

    Set<RoleDTO> getAllRoles();

    RoleDTO createRole(RoleDTO roleDTO);
}
