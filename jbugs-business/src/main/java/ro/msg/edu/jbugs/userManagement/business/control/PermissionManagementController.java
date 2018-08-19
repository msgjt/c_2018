package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDTOHelper;
import ro.msg.edu.jbugs.userManagement.persistence.dao.PermissionPersistenceManagement;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class PermissionManagementController implements PermissionManagement{

    @EJB
    private PermissionPersistenceManagement permissionManagement;

    @Override
    public PermissionDTO addPermissionForRole(RoleDTO role, PermissionDTO permission) {

        Optional<Permission> permissionOptional = permissionManagement.createPermissionForRole(RoleDTOHelper.toEntity(role),PermissionDTOHelper.toEntity(permission));
        return PermissionDTOHelper.fromEntity(permissionOptional.get());
    }

    @Override
    public PermissionDTO removePermissionForRole(RoleDTO role, PermissionDTO permission) {
        Optional<Permission> permissionOptional = permissionManagement.removePermissionForRole(RoleDTOHelper.toEntity(role),PermissionDTOHelper.toEntity(permission));
        return PermissionDTOHelper.fromEntity(permissionOptional.get());
    }

    @Override
    public PermissionDTO getPermissionById(long id) {
        Optional<Permission> permissionOptional = permissionManagement.getPermissionForId(id);
        return PermissionDTOHelper.fromEntity(permissionOptional.get());
    }

    @Override
    public List<PermissionDTO> getAllPermissions() {
        List<Permission> permissions = permissionManagement.getAllPermissions();
        return permissions.stream().map(x -> PermissionDTOHelper.fromEntity(x)).collect(Collectors.toList());
    }

    @Override
    public List<PermissionDTO> getAllPermissionsForRole(RoleDTO roleDTO) {
        Role role = RoleDTOHelper.toEntity(roleDTO);
        List<Permission> permissions = permissionManagement.getPermissionsForRole(role);
        return permissions.stream().map(x -> PermissionDTOHelper.fromEntity(x)).collect(Collectors.toList());
    }

    @Override
    public RoleDTO getRoleById(long id) {
        Role role = permissionManagement.getRoleForId(id).get();
        return RoleDTOHelper.fromEntity(role);
    }

}
