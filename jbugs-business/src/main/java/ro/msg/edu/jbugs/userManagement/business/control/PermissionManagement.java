package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.validation.constraints.NotNull;
import java.util.List;


public interface PermissionManagement {
    /**
     * This method adds a permission to a role
     * @param role
     * @param permission
     * @return the permission that has been added
     */
    PermissionDTO addPermissionForRole(@NotNull RoleDTO role, @NotNull PermissionDTO permission);

    /**
     * This method removes a permission to a role
     * @param role
     * @param permission
     * @return the permission that has been removed
     */
    PermissionDTO removePermissionForRole(@NotNull RoleDTO role, @NotNull PermissionDTO permission);

    /**
     * Method for finding a permission in db
     * @param id
     * @return the permission that has been found
     */
    PermissionDTO getPermissionById(long id);

    /**
     * Method for getting all the permission from db
     * @return a list containing all the permissions
     */
    List<PermissionDTO> getAllPermissions();

    /**
     * Method for getting all the permission for a specific role
     * @return a list containing all the permissions
     */
    List<PermissionDTO> getAllPermissionsForRole(@NotNull RoleDTO roleDTO);

    /**
     * Method used for finding a role from db
     * @param id
     * @return the roleDTO with the id that is sent
     */
    RoleDTO getRoleById(long id);

}
