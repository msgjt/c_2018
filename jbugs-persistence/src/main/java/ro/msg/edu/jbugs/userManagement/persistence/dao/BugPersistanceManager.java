package ro.msg.edu.jbugs.userManagement.persistence.dao;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class BugPersistanceManager implements BugPersistanceManagement {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    /***
     * @return a list of bugs which is in descending order by target date.
     */
    @Override
    public List<Bug> getAllBugs() {
        return em.createNamedQuery(Bug.GET_ALL_BUGS, Bug.class).getResultList();
    }
}
