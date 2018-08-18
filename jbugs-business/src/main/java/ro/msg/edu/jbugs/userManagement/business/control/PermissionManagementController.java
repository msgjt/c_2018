package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDTOHelper;
import ro.msg.edu.jbugs.userManagement.persistence.dao.PermissionPersistenceManagement;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Optional;

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
}
