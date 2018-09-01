package ro.msg.edu.jbugs.business.service.user;

import ro.msg.edu.jbugs.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.persistence.exceptions.PersistenceException;

import java.util.Set;

public interface IRoleBusinessService {
    RoleDTO getRoleById(long id) throws PersistenceException;

    Set<RoleDTO> getAllRoles();

    RoleDTO createRole(RoleDTO roleDTO);

    RoleDTO updateRole(RoleDTO roleDTO) throws PersistenceException, BusinessException;
}
