package ro.msg.edu.jbugs.userManagement.persistence.service;

import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Comment;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface IBugPersistenceService extends Serializable {
    List<Bug> getAllBugs();
    Optional<Bug> addBug(Bug bug,Attachment attachment);
    Optional<Bug> findBugById(long id);
    Optional<Attachment> addAttachment(Attachment attachment);
    List<Attachment> getAllAttachments();
    Optional<Bug> updateBug(Bug bug);
    List<Comment> getCommentsForBug(Bug bug);
    Optional<Attachment> deleteAttachment(Attachment attachment);
}
