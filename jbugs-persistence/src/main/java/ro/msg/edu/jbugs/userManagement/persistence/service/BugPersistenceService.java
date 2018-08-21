package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class BugPersistenceService implements IBugPersistenceService {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    @EJB
    private IUserPersistenceService userPersistenceService;

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
        User user = userPersistenceService.getUserByUsername(bug.getAssignedTo().getUsername()).get();
        //user.getBugs().add(bug);
        bug.setAssignedTo(user);
        User createByUser = userPersistenceService.getUserByUsername(bug.getCreatedByUser().getUsername()).get();
        //createByUser.getBugs().add(bug);
        bug.setCreatedByUser(createByUser);
        em.persist(bug);
        return Optional.of(bug);
    }


}
