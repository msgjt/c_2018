package ro.msg.edu.jbugs.persistence.service.user;


import ro.msg.edu.jbugs.persistence.cache.InMemoryCache;
import ro.msg.edu.jbugs.persistence.entity.Permission;
import ro.msg.edu.jbugs.persistence.service.user.IPermissionPersistenceService;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


@Stateless(name = "PermissionManagementImpl", mappedName = "PermissionPersistenceService")
public class PermissionPersistenceService implements IPermissionPersistenceService {

    private InMemoryCache memoryCache = InMemoryCache.getInstance();

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;


    @Override
    public Optional<Permission> getPermissionForId(long id) {
        Query query = em.createQuery("SELECT p FROM Permission p WHERE p.idPermission=:id");
        query.setParameter("id", id);
        return Optional.of((Permission) query.getSingleResult());
    }

    @Override
    public List<Permission> getAllPermissions() {
        List<Object> permissionObjectsList = memoryCache.getCachedValues(Permission.class.getSimpleName());
        if (permissionObjectsList != null)
            return (List) permissionObjectsList;
        else {
            Query query = em.createQuery("SELECT p FROM Permission p");
            memoryCache.add(Permission.class.getSimpleName(), query.getResultList(), 86400000);
            return query.getResultList();
        }
    }
}
