package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;


public interface IPermissionPersistenceService extends Serializable {


    /**
     * Method used for getting a permission with a specified id
     * @param id
     * @return optional of a permission with the id send as parameter
     */
    Optional<Permission> getPermissionForId(@NotNull long id);

    /**
     * Method used for getting the permissions from DB
     * @return list of all permissions
     */
    List<Permission> getAllPermissions();


}
