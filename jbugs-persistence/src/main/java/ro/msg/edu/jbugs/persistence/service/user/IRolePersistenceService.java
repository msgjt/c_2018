package ro.msg.edu.jbugs.persistence.service.user;

import ro.msg.edu.jbugs.persistence.entity.Role;
import ro.msg.edu.jbugs.persistence.exceptions.PersistenceException;

import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.Set;

public interface IRolePersistenceService{

    /**
     * Method used for creating a role
     * @param role
     * @return optional of the added role
     */
    Optional<Role> createRole(@NotNull Role role);

    /**
     * Method used for updating a role
     * @param role
     * @return optional of the updated role
     */
    Optional<Role> updateRole(@NotNull Role role) throws PersistenceException;

    /**
     * Method used for getting a role with a specified id
     * @param id
     * @return optional of a role with the id send as parameter
     */
    Optional<Role> getRoleById(long id) throws PersistenceException;

    /**
     * Method for getting all roles from DB
     * @return set of all roles
     */
    Set<Role> getAllRoles();

}
