package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        bug.setAssignedTo(user);
        User createByUser = userPersistenceService.getUserByUsername(bug.getCreatedByUser().getUsername()).get();
        bug.setCreatedByUser(createByUser);
        em.persist(bug);
        return Optional.of(bug);
    }


    /**
     * Method for finding a bug
     * @param id
     * @return optional of the bug or empty if doesn't exist
     */
    @Override
    public Optional<Bug> findBugById(long id) {
        Query q = em.createQuery("SELECT b FROM Bug b WHERE b.idBug=" + id);
        return Optional.of((Bug) q.getSingleResult());
    }

    /**
     * Method for adding a bug
     * @param attachment
     * @return optional of the attachment
     */
    @Override
    public Optional<Attachment> addAttachment(Attachment attachment) {
//        Bug bug = this.findBugById(attachment.getBug().getIdBug()).get();
//        bug.getAttachments().add(attachment);
        em.persist(attachment);
        return Optional.of(attachment);
    }

    @Override
    public List<Attachment> getAllAttachments() {
        return em.createNamedQuery(Attachment.GET_ALL_ATTACHMENTS, Attachment.class).getResultList();
    }


}
