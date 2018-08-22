package ro.msg.edu.jbugs.userManagement.business.utils;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;

public class BugAttachmentMapper {
    private BugDTO bugDTO;
    private AttachmentDTO attachmentDTO;

    public BugDTO getBug() {
        return bugDTO;
    }

    public void setBug(BugDTO bug) {
        this.bugDTO = bug;
    }

    public AttachmentDTO getAttachmentDTO() {
        return attachmentDTO;
    }

    public void setAttachmentDTO(AttachmentDTO attachmentDTO) {
        this.attachmentDTO = attachmentDTO;
    }
}
