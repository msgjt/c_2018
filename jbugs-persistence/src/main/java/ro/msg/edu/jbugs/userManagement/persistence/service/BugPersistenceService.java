package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Comment;
import ro.msg.edu.jbugs.userManagement.persistence.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
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
     *
     * @param bug
     * @return optional of the added bug
     */
    @Override
    public Optional<Bug> addBug(Bug bug,Attachment attachment) {
     //   bug.getAttachments().add(attachment);
        User user = userPersistenceService.getUserByUsername(bug.getAssignedTo().getUsername()).get();
        bug.setAssignedTo(user);
        User createByUser = userPersistenceService.getUserByUsername(bug.getCreatedByUser().getUsername()).get();
        bug.setCreatedByUser(createByUser);
        em.persist(bug);
        return Optional.of(bug);
    }


    /**
     * Method for finding a bug
     *
     * @param id
     * @return optional of the bug or empty if doesn't exist
     */
    @Override
    public Optional<Bug> findBugById(long id) {
        Query q = em.createQuery("SELECT b FROM Bug b WHERE b.idBug=" + id);
        return Optional.of((Bug) q.getSingleResult());
    }

    /**
     * Method for adding an attachment
     *
     * @param attachment
     * @return optional of the attachment
     */
    @Override
    public Optional<Attachment> addAttachment(Attachment attachment) {
        long idBug = attachment.getBug().getIdBug();
        if(idBug==0){
            attachment.getBug().setIdBug((long) this.getAllBugs().size());
        }
        em.persist(attachment);
        return Optional.of(attachment);
    }

    /**
     * Method for getting all the attachments from db
     *
     * @param
     * @return list of founded attachments
     */
    @Override
    public List<Attachment> getAllAttachments() {
        return em.createNamedQuery(Attachment.GET_ALL_ATTACHMENTS, Attachment.class).getResultList();
    }


    /**
     * Method for updating a bug
     *
     * @param bug
     * @return optional of the updated bug
     */
    @Override
    public Optional<Bug> updateBug(Bug bug) {
        em.merge(bug);
        return Optional.of(bug);
    }


    @Override
    public List<Comment> getCommentsForBug(Bug bug) {
        TypedQuery<Comment> q = em.createNamedQuery(Comment.GET_COMMENTS_FOR_BUG, Comment.class);
        q.setParameter("bugId", bug);
        try {
            return q.getResultList();
        } catch (NoResultException ex) {
            return new ArrayList<>();
        }
    }

    @Override
    public Optional<Attachment> deleteAttachment(Attachment attachment) {
       Attachment attachmentToBeDeleted = em.getReference(Attachment.class,attachment.getIdAttachment());
       em.remove(attachmentToBeDeleted);
        return Optional.of(attachment);
    }


    /**
     * Method for adding a commment
     * @param comment
     * @return optional of the added comment
     */
    @Override
    public Optional<Comment> addComment(Comment comment) {
        comment.setDate(new Date());
        em.persist(comment);
        return Optional.of(comment);
    }


}
