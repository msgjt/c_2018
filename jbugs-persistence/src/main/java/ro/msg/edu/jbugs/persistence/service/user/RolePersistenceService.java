package ro.msg.edu.jbugs.persistence.service.user;

import ro.msg.edu.jbugs.persistence.entity.Role;
import ro.msg.edu.jbugs.persistence.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.persistence.exceptions.PersistenceException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Stateless
public class RolePersistenceService implements IRolePersistenceService {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    @Override
    public Optional<Role> createRole(@NotNull Role role) {
        em.persist(role);
        em.flush();
        return Optional.of(role);
    }

    @Override
    public Optional<Role> updateRole(@NotNull Role role) throws PersistenceException {
        Role roleToUpdate = this.getRoleById(role.getIdRole()).get();
        roleToUpdate.setIdRole(role.getIdRole());
        roleToUpdate.setType(role.getType());
        roleToUpdate.setPermissions(role.getPermissions());
        return Optional.of(em.merge(roleToUpdate));
    }

    @Override
    public Optional<Role> getRoleById(long id) throws PersistenceException {
        String query = "SELECT r FROM Role r WHERE r.idRole= :id";
        Query q = em.createQuery(query).setParameter("id",id);

        Optional<Role> optionalRole= Optional.of((Role) q.getSingleResult());

        if (optionalRole.isPresent())
            return Optional.of((Role) q.getSingleResult());
        throw new PersistenceException(ExceptionCode.ROLE_NOT_FOUND_EXCEPTION);
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(em.createNamedQuery(Role.GET_ALL_ROLES, Role.class).getResultList());
    }

}
