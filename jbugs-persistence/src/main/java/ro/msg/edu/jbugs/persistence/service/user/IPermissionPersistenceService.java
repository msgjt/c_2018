package ro.msg.edu.jbugs.persistence.service.user;

import ro.msg.edu.jbugs.persistence.entity.Permission;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;


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
