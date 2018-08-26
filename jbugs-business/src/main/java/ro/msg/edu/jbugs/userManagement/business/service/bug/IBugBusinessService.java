package ro.msg.edu.jbugs.userManagement.business.service.bug;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.CommentDTO;

import java.util.List;

public interface IBugBusinessService {

    List<BugDTO> getAllBugs();
    BugDTO addBug(BugDTO bugDTO,AttachmentDTO attachmentDTO);
    BugDTO findBugById(long id);
    AttachmentDTO addAttachment(AttachmentDTO attachmentDTO);
    List<AttachmentDTO> getAllAttachments();
    BugDTO updateBug(BugDTO bugDTO);
    AttachmentDTO deleteAttachment(AttachmentDTO attachmentDTO);
    List<CommentDTO> getCommentsForBug(Long bugId);
    CommentDTO addComment(CommentDTO commentDTO);
}
