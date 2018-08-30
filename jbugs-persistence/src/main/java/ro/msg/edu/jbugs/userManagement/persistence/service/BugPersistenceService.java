package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.util.*;

@Stateless
public class BugPersistenceService implements IBugPersistenceService {

    @PersistenceContext(unitName = "jbugs-persistence")
    private EntityManager em;

    @EJB
    private IUserPersistenceService userPersistenceService;


    @Override
    public List<Bug> getAllBugs() {
        return em.createNamedQuery(Bug.GET_ALL_BUGS, Bug.class).getResultList();
    }


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



    @Override
    public Optional<Bug> findBugById(long id) {
        Query q = em.createQuery("SELECT b FROM Bug b WHERE b.idBug=" + id);
        return Optional.of((Bug) q.getSingleResult());
    }


    @Override
    public Optional<Attachment> addAttachment(Attachment attachment) {
        long idBug = attachment.getBug().getIdBug();
        if(idBug==0){
            attachment.getBug().setIdBug((long) this.getAllBugs().size());
        }
        em.persist(attachment);
        return Optional.of(attachment);
    }


    @Override
    public List<Attachment> getAllAttachments() {
        return em.createNamedQuery(Attachment.GET_ALL_ATTACHMENTS, Attachment.class).getResultList();
    }



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



    @Override
    public Optional<Comment> addComment(Comment comment) {
        comment.setDate(new Date());
        em.persist(comment);
        return Optional.of(comment);
    }

    @Override
    public Optional<History> addHistory(History history) {
        em.persist(history);
        return Optional.of(history);
    }

    @Override
    public List<History> getAllHistory() {
        return em.createNamedQuery(History.GET_ALL_HISTORY, History.class).getResultList();
    }

    @Override
    public Map<String, Long> getStatistics() {
        List<Bug> bugs = this.getAllBugs();
        Map<String,Long> mapOfCountedStatuses = new HashMap<>();
        mapOfCountedStatuses.put("OPEN", bugs.stream().filter(x -> x.getStatus().equals(StatusEnum.OPEN)).count());
        mapOfCountedStatuses.put("FIXED", bugs.stream().filter(x -> x.getStatus().equals(StatusEnum.FIXED)).count());
        mapOfCountedStatuses.put("NEW", bugs.stream().filter(x -> x.getStatus().equals(StatusEnum.NEW)).count());
        mapOfCountedStatuses.put("IN_PROGRESS", bugs.stream().filter(x -> x.getStatus().equals(StatusEnum.IN_PROGRESS)).count());
        mapOfCountedStatuses.put("REJECTED", bugs.stream().filter(x -> x.getStatus().equals(StatusEnum.REJECTED)).count());
        mapOfCountedStatuses.put("INFO_NEEDED", bugs.stream().filter(x -> x.getStatus().equals(StatusEnum.INFO_NEEDED)).count());
        mapOfCountedStatuses.put("CLOSED", bugs.stream().filter(x -> x.getStatus().equals(StatusEnum.CLOSED)).count());
        return mapOfCountedStatuses;
    }

    @Override
    public Map<String, Long> getFixedBugsForUser() {
        Set<User> users = this.userPersistenceService.getAllUsers();
        List<Bug> bugs = this.getAllBugs();
        Map<String,Long> mapOfFixedBugs = new HashMap<>();
        users.forEach(x -> {
            long fixedBugs = bugs.stream().filter(y -> y.getStatus().equals(StatusEnum.FIXED)
                    && y.getAssignedTo().getUsername().equals(x.getUsername())).count();
            mapOfFixedBugs.put(x.getUsername(),fixedBugs);
        });
        return mapOfFixedBugs;
    }

    @Override
    public Map<String, Long> getStatisticsForNewAndRejectedBugs() {
        List<Bug> bugs = this.getAllBugs();
        Map<String,Long> mapOfCountedStatuses = new HashMap<>();
        mapOfCountedStatuses.put("NEW", bugs.stream().filter(x -> x.getStatus().equals(StatusEnum.NEW)).count());
        mapOfCountedStatuses.put("REJECTED", bugs.stream().filter(x -> x.getStatus().equals(StatusEnum.REJECTED)).count());
        return mapOfCountedStatuses;
    }


}
