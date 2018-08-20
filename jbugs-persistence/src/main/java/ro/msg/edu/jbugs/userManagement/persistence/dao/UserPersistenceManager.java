package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Provides functions for working with users in the persistence layer.
 */
@Stateless
public class UserPersistenceManager implements UserPersistenceManagement {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;


    /**
     * Persists a user in the database.
     *
     * @param user : user entity to be created, should not be null
     * @return : inserted user entity from database
     */
    public Optional<User> createUser(@NotNull User user) {
        em.persist(user);
        em.flush();
        return Optional.of(user);
    }

    /**
     * Updates a user from the database.
     *
     * @param user : user entity to be updated, should not be null
     * @return : updated user entity from database
     */
    public Optional<User> updateUser(@NotNull User user) {
        return Optional.of(em.merge(user));
    }

    /**
     * Get a list of all users from the database.
     *
     * @return : ResultList, empty if there are no users in the database.
     */
    public List<User> getAllUsers() {
        return em.createNamedQuery(User.GET_ALL_USERS, User.class).getResultList();
    }


    /**
     * Returns a user entity with the matching username wrapped in an optional.
     * If none exist, returns an empty Optional Object
     *
     * @param username : String containing the username.
     * @return : Optional, containing a user entity.
     */
    public Optional<User> getUserByUsername(@NotNull String username) {
        TypedQuery<User> q = em.createNamedQuery(User.GET_USER_BY_USERNAME, User.class);
        q.setParameter("username", username);
        try {
            return Optional.of(q.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }

    }


    /**
     * Persists a user in the database.
     *
     * @param role : role entity to be created, should not be null
     * @return : Optional, containing a role entity.
     */
    public Optional<Role> createRole(@NotNull Role role) {
        em.persist(role);
        em.flush();
        return Optional.of(role);
    }

    /**
     * Removes a role from the database.
     *
     * @param role : role entity to be removed, should not be null
     * @return : Optional, containing a role entity.
     */
    public Optional<Role> removeRole(@NotNull Role role) {
        em.remove(role);
        return Optional.of(role);

    }

    /**
     * Updates a role in the database using the given Role entity.
     *
     * @param role : role entity to be updated, should not be null
     * @return : returns the updated Optional role entity
     */
    public Optional<Role> updateRole(@NotNull Role role) {
        em.merge(role);
        return Optional.of(role);
    }

    /**
     * TODO: nu cred ca avem nevoie de metoda asta - nu am mai facut-o frumoasa
     * Returns the role with the given id
     *
     * @param id : id
     * @return : Optional role entity
     */
    public Optional<Role> getRoleForId(long id) {
        Query q = em.createQuery("SELECT r FROM Role r WHERE r.idRole=" + id);
        return Optional.of((Role) q.getSingleResult());
    }

    /**
     * Get a list of all roles stored in the database.
     *
     * @return : An Optional list of Roles, empty if there are no roles in the database.
     */
    public List<Role> getAllRoles() {
        TypedQuery<Role> q = em.createNamedQuery(Role.GET_ALL_ROLES, Role.class);
        return q.getResultList();
    }


    /**
     * Returns a user entity with the matching email wrapped in an optional.
     * If none exist, returns an empty Optional Object
     *
     * @param email : String containing the email.
     * @return : Optional, containing a user entity.
     */
    public Optional<User> getUserByEmail(@NotNull String email) {
        TypedQuery<User> q = em.createNamedQuery(User.GET_USER_BY_EMAIL, User.class)
                .setParameter("email", email);
        try {
            return Optional.of(q.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    /**
     * @param username
     * @return Optinal list of a username
     */
    public List<String> getUsernamesLike(String username) {
        Query q = em.createQuery("select u.username from User u where u.username like '" + username + "%'");
        return q.getResultList();
    }
}