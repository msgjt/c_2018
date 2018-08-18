package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.PermissionDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.RoleDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.validation.constraints.NotNull;


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
    PermissionDTO deletePermissionForRole(@NotNull RoleDTO role, @NotNull PermissionDTO permission);

    /**
     * Method for finding a permission in db
     * @param id
     * @return the permission that has been found
     */
    PermissionDTO getPermissionById(long id);

}
