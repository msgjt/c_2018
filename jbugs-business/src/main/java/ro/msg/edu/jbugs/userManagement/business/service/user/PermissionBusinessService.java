package ro.msg.edu.jbugs.userManagement.business.service.user;

import ro.msg.edu.jbugs.userManagement.business.dto.helper.PermissionDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.RoleDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.user.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.user.RoleDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.service.IPermissionPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Stateless
public class PermissionBusinessService implements IPermissionBusinessService {
    @EJB
    private IPermissionPersistenceService permissionPersistenceService;
    @EJB
    private PermissionDTOHelper permissionDTOHelper;

    @Override
    public PermissionDTO getPermissionById(long id) {
        Optional<Permission> permissionOptional = permissionPersistenceService.getPermissionForId(id);
        return permissionDTOHelper.fromEntity(permissionOptional.get());
    }

    @Override
    public List<PermissionDTO> getAllPermissions() {
        List<Permission> permissions = permissionPersistenceService.getAllPermissions();
        return permissions.stream().map(permissionDTOHelper::fromEntity).collect(Collectors.toList());
    }

}
