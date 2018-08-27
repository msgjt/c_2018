package ro.msg.edu.jbugs.userManagement.business.service.user;

import ro.msg.edu.jbugs.userManagement.business.dto.helper.RoleDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.service.IRolePersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
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
    public RoleDTO getRoleById(long id) {
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
    public RoleDTO updateRole(RoleDTO roleDTO) {
        Role role = roleDTOHelper.toEntity(roleDTO);
        return roleDTOHelper.fromEntity(rolePersistenceService.updateRole(role).get());

    }

    @Override
    public Set<RoleDTO> getAllRoles() {
        Set<Role> roles = rolePersistenceService.getAllRoles();
        return roles.stream().map(roleDTOHelper::fromEntity).collect(Collectors.toSet());
    }
}
