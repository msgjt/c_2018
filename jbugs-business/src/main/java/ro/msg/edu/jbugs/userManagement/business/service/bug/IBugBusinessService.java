package ro.msg.edu.jbugs.userManagement.business.service.bug;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;

import java.util.List;

public interface IBugBusinessService {

    List<BugDTO> getAllBugs();
    BugDTO addBug(BugDTO bugDTO);
    BugDTO findBugById(long id);
    AttachmentDTO addAttachment(AttachmentDTO attachmentDTO);
    List<AttachmentDTO> getAllAttachments();
}
