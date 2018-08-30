package ro.msg.edu.jbugs.userManagement.persistence.service;


import ro.msg.edu.jbugs.userManagement.persistence.cache.InMemoryCache;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Permission;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;


@Stateless(name = "PermissionManagementImpl", mappedName = "PermissionPersistenceService")
public class PermissionPersistenceService implements IPermissionPersistenceService {
    private static final long serialVersionUID = 1L;
    private InMemoryCache memoryCache = InMemoryCache.getInstance();

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    @Override
    public Optional<Permission> addPermission(@NotNull Permission permission) {
        em.persist(permission);
        memoryCache.addValue(Permission.class.getSimpleName(), (Object) permission);
        return Optional.of(permission);
    }

    @Override
    public Optional<Permission> updatePermission(@NotNull Permission permission) {
        em.merge(permission);
        return Optional.of(permission);
    }

    @Override
    public void removePermission(@NotNull Permission permission) {
        em.remove(permission);
    }

    @Override
    public Optional<Permission> getPermissionForId(long id) {
        Query query = em.createQuery("SELECT p FROM Permission p WHERE p.idPermission=:id");
        query.setParameter("id", id);
        return Optional.of((Permission) query.getSingleResult());
    }

    @Override
    public List<Permission> getAllPermissions() {
        List<Object> permissionObjectsList = memoryCache.get(Permission.class.getSimpleName());
        if (permissionObjectsList != null)
            return (List) permissionObjectsList;
        else {
            Query query = em.createQuery("SELECT p FROM Permission p");
            memoryCache.add(Permission.class.getSimpleName(), query.getResultList(), 86400000);
            return query.getResultList();
        }
    }
}
