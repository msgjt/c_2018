package ro.msg.edu.jbugs.userManagement.business.service.bug;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.CommentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.AttachmentDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.BugDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.CommentDTOHelper;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Comment;
import ro.msg.edu.jbugs.userManagement.persistence.service.IBugPersistenceService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class BugBusinessService implements IBugBusinessService {

    @EJB
    private IBugPersistenceService bugPersistenceService;
    @EJB
    private BugDTOHelper bugDTOHelper;
    @EJB
    private AttachmentDTOHelper attachmentDTOHelper;
    @EJB
    private CommentDTOHelper commentDTOHelper;


    @Override
    public List<BugDTO> getAllBugs() {
        List<Bug> bugs = bugPersistenceService.getAllBugs();
        return bugs.stream().map(bug -> bugDTOHelper.fromEntity(bug)).collect(Collectors.toList());
    }

    @Override
    public BugDTO addBug(BugDTO bugDTO,AttachmentDTO attachmentDTO) {
        Bug bug = bugDTOHelper.toEntity(bugDTO);
        Attachment attachment = attachmentDTOHelper.toEntity(attachmentDTO);
        return bugDTOHelper.fromEntity(bugPersistenceService.addBug(bug,attachment).get());
    }

    @Override
    public BugDTO findBugById(long id) {
        return bugDTOHelper.fromEntity(bugPersistenceService.findBugById(id).get());
    }

    @Override
    public AttachmentDTO addAttachment(AttachmentDTO attachmentDTO) {
        Attachment attachment = attachmentDTOHelper.toEntity(attachmentDTO);
        return attachmentDTOHelper.fromEntity(bugPersistenceService.addAttachment(attachment).get());
    }

    @Override
    public List<AttachmentDTO> getAllAttachments() {
        List<Attachment> attachments = bugPersistenceService.getAllAttachments();
        return attachments.stream().map(x -> attachmentDTOHelper.fromEntity(x)).collect(Collectors.toList());
    }

    @Override
    public BugDTO updateBug(BugDTO bugDTO) {
        Bug bug = bugDTOHelper.toEntity(bugDTO);
        return bugDTOHelper.fromEntity(bugPersistenceService.updateBug(bug).get());
    }

    public List<CommentDTO> getCommentsForBug(Long bugId){
        BugDTO bugDTO = findBugById(bugId);
        List<Comment> comments = bugPersistenceService.getCommentsForBug(bugDTOHelper.toEntity(bugDTO));
        return comments.stream().map(c -> commentDTOHelper.fromEntity(c)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment = commentDTOHelper.toEntity(commentDTO);
        return commentDTOHelper.fromEntity(bugPersistenceService.addComment(comment).get());
    }

    @Override
    public AttachmentDTO deleteAttachment(AttachmentDTO attachmentDTO) {
        Attachment attachment = attachmentDTOHelper.toEntity(attachmentDTO);
        return attachmentDTOHelper.fromEntity(bugPersistenceService.deleteAttachment(attachment).get());
    }
}
