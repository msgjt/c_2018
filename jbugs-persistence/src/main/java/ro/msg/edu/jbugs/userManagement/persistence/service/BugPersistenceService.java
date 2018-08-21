package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class BugPersistenceService implements IBugPersistenceService {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    /***
     * @return a list of bugs which is in descending order by target date.
     */
    @Override
    public List<Bug> getAllBugs() {
        return em.createNamedQuery(Bug.GET_ALL_BUGS, Bug.class).getResultList();
    }

    /**
     * Method for adding a bug
     * @param bug
     * @return optional of the added bug
     */
    @Override
    public Optional<Bug> addBug(Bug bug) {
        em.persist(bug);
        return Optional.of(bug);
    }


}
