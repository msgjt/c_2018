package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

public class AttachmentDTOHelper {


    public static AttachmentDTO fromEntity(Attachment attachment){
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setIdAttachment(attachment.getIdAttachment());
        attachmentDTO.setBugDto(BugDTOHelper.fromEntity(attachment.getBug()));
        attachmentDTO.setBlob(attachment.getFile());
        return attachmentDTO;
    }

    public static Attachment toEntity(AttachmentDTO attachmentDTO){
        Attachment attachment = new Attachment();
        attachment.setIdAttachment(attachmentDTO.getIdAttachment());
        attachment.setBug(BugDTOHelper.toEntity(attachmentDTO.getBugDto()));
        attachment.setFile(attachmentDTO.getBlob());
        return attachment;
    }
}
