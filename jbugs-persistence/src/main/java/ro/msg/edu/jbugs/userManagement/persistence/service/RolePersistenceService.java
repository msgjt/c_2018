package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Stateless
public class RolePersistenceService implements IRolePersistenceService{
    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    @Override
    public Optional<Role> createRole(@NotNull Role role) {
        em.persist(role);
        em.flush();
        return Optional.of(role);
    }

    @Override
    public void removeRole(@NotNull Role role) {
                em.remove(role);
    }

    @Override
    public Optional<Role> updateRole(@NotNull Role role) {
        return Optional.of(em.merge(role));
    }

    @Override
    public Optional<Role> getRoleById(long id) {
        Query q = em.createQuery("SELECT r FROM Role r WHERE r.idRole=" + id);
        return Optional.of((Role) q.getSingleResult());
    }

    @Override
    public Optional<Role> getRoleByType(@NotNull String type){
        Query q = em.createQuery("SELECT r from Role r WHERE r.type='" + type +"'");
        return Optional.of((Role) q.getSingleResult());
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<Role>(em.createNamedQuery(Role.GET_ALL_ROLES, Role.class).getResultList());
    }

}
