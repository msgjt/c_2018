package ro.msg.edu.jbugs.userManagement.business.service.user;

import ro.msg.edu.jbugs.userManagement.business.dto.helper.RoleDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.userManagement.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.exceptions.PersistenceException;
import ro.msg.edu.jbugs.userManagement.persistence.service.IPermissionPersistenceService;
import ro.msg.edu.jbugs.userManagement.persistence.service.IRolePersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Stateless
public class RoleBusinessService implements IRoleBusinessService {

    @EJB
    private IRolePersistenceService rolePersistenceService;
    @EJB
    private RoleDTOHelper roleDTOHelper;

    @Override
    public RoleDTO getRoleById(long id) throws PersistenceException {
        Optional<Role> roleOptional = rolePersistenceService.getRoleById(id);
        return roleDTOHelper.fromEntity(roleOptional.get());
    }

    @Override
    public RoleDTO createRole(RoleDTO roleDTO) {
        Role role = roleDTOHelper.toEntity(roleDTO);
        rolePersistenceService.createRole(role);
        return roleDTOHelper.fromEntity(role);
    }

    @Override
    public Set<RoleDTO> getAllRoles() {
        Set<Role> roles = rolePersistenceService.getAllRoles();
        return roles.stream().map(roleDTOHelper::fromEntity).collect(Collectors.toSet());
    }

    @Override
    public RoleDTO updateRole(RoleDTO roleDTO) throws PersistenceException {
        Role role = roleDTOHelper.toEntity(roleDTO);
        rolePersistenceService.updateRole(role);
        return roleDTOHelper.fromEntity(role);
    }
}
