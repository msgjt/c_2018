package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.BugDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import java.util.List;

public interface IBugBusinessService {

    List<BugDTO> getAllBugs();
    BugDTO addBug(BugDTO bugDTO);
    BugDTO findBugById(long id);
    AttachmentDTO addAttachment(AttachmentDTO attachmentDTO);
    List<AttachmentDTO> getAllAttachments();
}
