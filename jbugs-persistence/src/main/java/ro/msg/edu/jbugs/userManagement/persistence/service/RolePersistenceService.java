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
//                Query q = em.createNativeQuery("delete from roles_permissions where (id_role,id_permission)=(?1,?2)");
//                role.getPermissions().forEach(p -> {q.setParameter(1,role.getIdRole()); q.setParameter(2,p.getIdPermission()); q.executeUpdate();});
                em.remove(role);
    }

    @Override
    public Optional<Role> updateRole(@NotNull Role role) {
//        Query q = em.createNativeQuery("insert into roles_permissions (id_role,id_permission) values (?1,?2) ON DUPLICATE KEY UPDATE id_role=?3,id_permission=?4;");
//        role.getPermissions().forEach(p ->{q.setParameter(1,role.getIdRole()); q.setParameter(2,p.getIdPermission()); q.setParameter(3,role.getIdRole()); q.setParameter(4,p.getIdPermission()); q.executeUpdate();});
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
