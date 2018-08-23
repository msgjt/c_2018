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
import java.util.stream.Collectors;

@Stateless
public class PermissionBusinessService implements IPermissionBusinessService {
    @EJB
    private IPermissionPersistenceService permissionManagement;
    @EJB
    private PermissionDTOHelper permissionDTOHelper;
    @EJB
    private RoleDTOHelper roleDTOHelper;

    @Override
    public PermissionDTO addPermissionForRole(RoleDTO role, PermissionDTO permission) {

        Optional<Permission> permissionOptional = permissionManagement.createPermissionForRole(roleDTOHelper.toEntity(role), permissionDTOHelper.toEntity(permission));
        return permissionDTOHelper.fromEntity(permissionOptional.get());
    }

    @Override
    public PermissionDTO removePermissionForRole(RoleDTO role, PermissionDTO permission) {
        Optional<Permission> permissionOptional = permissionManagement.removePermissionForRole(roleDTOHelper.toEntity(role), permissionDTOHelper.toEntity(permission));
        return permissionDTOHelper.fromEntity(permissionOptional.get());
    }

    @Override
    public PermissionDTO getPermissionById(long id) {
        Optional<Permission> permissionOptional = permissionManagement.getPermissionForId(id);
        return permissionDTOHelper.fromEntity(permissionOptional.get());
    }

    @Override
    public List<PermissionDTO> getAllPermissions() {
        List<Permission> permissions = permissionManagement.getAllPermissions();
        return permissions.stream().map(permissionDTOHelper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public List<PermissionDTO> getAllPermissionsForRole(RoleDTO roleDTO) {
        Role role = roleDTOHelper.toEntity(roleDTO);
        List<Permission> permissions = permissionManagement.getPermissionsForRole(role);
        return permissions.stream().map(permissionDTOHelper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(long id) {
        Role role = permissionManagement.getRoleForId(id).get();
        return roleDTOHelper.fromEntity(role);
    }

}
