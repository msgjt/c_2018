package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

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
//        Query q = em.createNativeQuery("insert into users_roles (id_user,id_role) values (?1,?2) ON DUPLICATE KEY UPDATE id_user=?3,id_role=?4;");
//        user.getRoles().forEach(r ->{q.setParameter(1,user.getIdUser()); q.setParameter(2,r.getIdRole()); q.setParameter(3,user.getIdUser()); q.setParameter(4,r.getIdRole()); q.executeUpdate();});
        return Optional.of(em.merge(user));
    }

    public void removeUser(@NotNull User user){

//            Query q = em.createNativeQuery("delete from users_roles where (id_user,id_role)=(?1,?2)");
//            user.getRoles().forEach(r -> {
//                q.setParameter(1, user.getIdUser());
//                q.setParameter(2, r.getIdRole());
//                q.executeUpdate();
//            });
            em.remove(user);
    }

    public Optional<User> getUserById(long id){
        Query q = em.createQuery("SELECT u FROM User u WHERE u.idUser=" + id);
        return Optional.of((User) q.getSingleResult());
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