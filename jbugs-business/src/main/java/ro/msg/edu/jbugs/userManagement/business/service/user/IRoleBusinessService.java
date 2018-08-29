package ro.msg.edu.jbugs.userManagement.business.service.user;

import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.persistence.exceptions.PersistenceException;

import java.util.List;
import java.util.Set;

public interface IRoleBusinessService {
    RoleDTO getRoleById(long id) throws PersistenceException;

    Set<RoleDTO> getAllRoles();

    RoleDTO createRole(RoleDTO roleDTO);

    RoleDTO updateRole(RoleDTO roleDTO) throws PersistenceException, BusinessException;
}
