package ro.msg.edu.jbugs.business.dto.helper;

import ro.msg.edu.jbugs.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.persistence.entity.Attachment;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class AttachmentDTOHelper {
    @EJB
    private BugDTOHelper bugDTOHelper;


    public AttachmentDTO fromEntity(Attachment attachment) {
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setIdAttachment(attachment.getIdAttachment());
        attachmentDTO.setBugDTO(bugDTOHelper.fromEntity(attachment.getBug()));
        attachmentDTO.setBlob(attachment.getFile());
        attachmentDTO.setName(attachment.getFileName());
        attachmentDTO.setExtension(attachment.getExtension());
        return attachmentDTO;
    }

    public Attachment toEntity(AttachmentDTO attachmentDTO) {
        Attachment attachment = new Attachment();
        attachment.setIdAttachment(attachmentDTO.getIdAttachment());
        attachment.setBug(bugDTOHelper.toEntity(attachmentDTO.getBugDTO()));
        attachment.setFile(attachmentDTO.getBlob());
        attachment.setFileName(attachmentDTO.getName());
        attachment.setExtension(attachmentDTO.getExtension());
        return attachment;
    }
}
