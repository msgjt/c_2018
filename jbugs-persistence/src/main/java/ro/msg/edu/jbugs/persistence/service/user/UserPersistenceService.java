package ro.msg.edu.jbugs.persistence.service.user;

import ro.msg.edu.jbugs.persistence.entity.User;
import ro.msg.edu.jbugs.persistence.service.user.IUserPersistenceService;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Stateless
public class UserPersistenceService implements IUserPersistenceService {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    public Optional<User> createUser(@NotNull User user) {
        em.persist(user);
        em.flush();
        return Optional.of(user);
    }

    public Optional<User> updateUser(@NotNull User user) {
        User userToUpdate = this.getUserById(user.getIdUser()).get();
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setIsActive(user.getIsActive());
        userToUpdate.setRoles(user.getRoles());
        return Optional.of(em.merge(userToUpdate));
    }

    public void changePassword(@NotNull String username, @NotNull String password){
        User user = this.getUserByUsername(username).get();
        user.setPassword(password);
        em.merge(user);
    }

    public Optional<User> getUserById(long id) {
        Query q = em.createQuery("SELECT u FROM User u WHERE u.idUser=" + id);
        return Optional.of((User) q.getSingleResult());
    }

    public int countUnfinishedTasks(@NotNull User user){
        Query q = em.createQuery("select b from Bug b where (b.status like '%FIXED%' or b.status like '%CLOSED%') and b.assignedTo=?1");
        q.setParameter(1,this.getUserById(user.getIdUser()).get());
        return q.getResultList().size();
    }

    public Set<User> getAllUsers() {
        return new HashSet<User>(em.createNamedQuery(User.GET_ALL_USERS, User.class).getResultList());
    }

    public Optional<User> getUserByUsername(@NotNull String username) {
        TypedQuery<User> q = em.createNamedQuery(User.GET_USER_BY_USERNAME, User.class);
        q.setParameter("username", username);
        try {
            return Optional.of(q.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }

    }

    public Optional<User> getUserByEmail(@NotNull String email) {
        TypedQuery<User> q = em.createNamedQuery(User.GET_USER_BY_EMAIL, User.class)
                .setParameter("email", email);
        try {
            return Optional.of(q.getSingleResult());
        } catch (NoResultException ex) {
            return Optional.empty();
        }
    }

    public Set<String> getUsernamesLike(@NotNull String username) {
        Query q = em.createQuery("select u.username from User u where u.username like '" + username + "%'");
        return new HashSet<String>(q.getResultList());
    }

}