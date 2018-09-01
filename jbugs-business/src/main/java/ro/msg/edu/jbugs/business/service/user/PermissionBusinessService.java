package ro.msg.edu.jbugs.business.service.user;

import ro.msg.edu.jbugs.business.dto.helper.PermissionDTOHelper;
import ro.msg.edu.jbugs.business.dto.user.PermissionDTO;
import ro.msg.edu.jbugs.persistence.entity.Permission;
import ro.msg.edu.jbugs.persistence.service.user.IPermissionPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
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
