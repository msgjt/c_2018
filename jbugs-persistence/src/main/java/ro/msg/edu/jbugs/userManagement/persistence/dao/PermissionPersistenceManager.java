package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Stateless(name = "PermissionManagementImpl", mappedName = "PermissionPersistenceManager")
public class PermissionPersistenceManager implements PermissionPersistenceManagement {

    private static final Logger logger = LogManager.getLogger(PermissionPersistenceManager.class);

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    @Override
    public Optional<Permission> addPermission(Permission permission) {
        em.persist(permission);
        return Optional.of(permission);
    }

    @Override
    public Optional<Permission> updatePermission(Permission permission) {
        em.merge(permission);
        return Optional.of(permission);
    }

    @Override
    public Optional<Permission> removePermissionById(long id) {
        Optional<Permission> permission = getPermissionForId(id);
        if(!permission.isPresent())
            return Optional.empty();
        em.remove(permission);
        return permission;
    }

    @Override
    public Optional<Permission> removePermissionForRole(Role role, Permission permission) {
        List<Permission> permissions = getPermissionsForRole(role);
        permissions.remove(permission);
        role.setPermissions(permissions);
        em.merge(role);
        return Optional.of(permission);

    }

    @Override
    public Optional<Role> removeAllPermissionsForRole(Role role) {
        List<Permission> permissions = getPermissionsForRole(role);
        permissions.clear();
        role.setPermissions(permissions);
        em.merge(role);
        return Optional.of(role);
    }

    @Override
    public Optional<Permission> getPermissionForId(long id) {
        Query query = em.createQuery("SELECT p FROM Permission p WHERE p.idPermission=:id");
        query.setParameter("id",id);
        return Optional.of((Permission) query.getSingleResult());
    }

    @Override
    public List<Permission> getPermissionsForRole(Role role) {
        Query query = em.createQuery("SELECT r.permissions FROM Role r WHERE r=:role");
        query.setParameter("role",role);
        return query.getResultList();

    }

    @Override
    public List<Permission> getAllPermissions() {
        Query query = em.createQuery("SELECT p FROM Permission p");
        return query.getResultList();
    }

    @Override
    public Optional<Permission> createPermissionForRole(Role role, Permission permission) {
        List<Permission> permissions = new ArrayList<>();
        permissions.add(permission);
        role.setPermissions(permissions);
        em.merge(role);
        return Optional.of(permission);
    }
}
