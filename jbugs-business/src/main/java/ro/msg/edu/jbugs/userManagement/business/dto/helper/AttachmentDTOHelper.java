package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.nio.charset.StandardCharsets;

@Stateless
public class AttachmentDTOHelper {
    @EJB
    private BugDTOHelper bugDTOHelper;


    public AttachmentDTO fromEntity(Attachment attachment) {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setIdAttachment(attachment.getIdAttachment());
        attachmentDTO.setBugDTO(bugDTOHelper.fromEntity(attachment.getBug()));
        attachmentDTO.setBlob(new String(attachment.getFile(), StandardCharsets.UTF_8));
        return attachmentDTO;
    }

    public Attachment toEntity(AttachmentDTO attachmentDTO) {
        Attachment attachment = new Attachment();
        attachment.setIdAttachment(attachmentDTO.getIdAttachment());
        attachment.setBug(bugDTOHelper.toEntity(attachmentDTO.getBugDTO()));
        attachment.setFile(attachmentDTO.getBlob().getBytes());
        return attachment;
    }
}
