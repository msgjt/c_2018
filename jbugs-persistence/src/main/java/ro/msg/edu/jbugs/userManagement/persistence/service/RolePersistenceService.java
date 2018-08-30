package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import ro.msg.edu.jbugs.userManagement.persistence.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.userManagement.persistence.exceptions.PersistenceException;

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
public class RolePersistenceService implements IRolePersistenceService {
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
//                Query q = em.createNativeQuery("delete from roles_permissions where (id_role,id_permission)=(?1,?2)");
//                role.getPermissions().forEach(p -> {q.setParameter(1,role.getIdRole()); q.setParameter(2,p.getIdPermission()); q.executeUpdate();});
        em.remove(role);
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
        Query q = em.createQuery("SELECT r FROM Role r WHERE r.idRole=" + id);
        Optional<Role> optionalRole= Optional.of((Role) q.getSingleResult());

        if (optionalRole.isPresent())
            return Optional.of((Role) q.getSingleResult());
        throw new PersistenceException(ExceptionCode.ROLE_NOT_FOUND_EXCEPTION);
    }

    @Override
    public Optional<Role> getRoleByType(@NotNull String type) {
        Query q = em.createQuery("SELECT r from Role r WHERE r.type='" + type + "'");
        return Optional.of((Role) q.getSingleResult());
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<Role>(em.createNamedQuery(Role.GET_ALL_ROLES, Role.class).getResultList());
    }

}
