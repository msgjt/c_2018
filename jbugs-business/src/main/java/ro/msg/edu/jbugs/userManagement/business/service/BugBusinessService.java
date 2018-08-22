package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.AttachmentDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.BugDTOHelper;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;
import ro.msg.edu.jbugs.userManagement.persistence.service.IBugPersistenceService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class BugBusinessService implements ro.msg.edu.jbugs.userManagement.business.service.IBugBusinessService {

    @EJB
    private IBugPersistenceService IBugPersistenceService;


    @Override
    public List<BugDTO> getAllBugs() {
        List<Bug> bugs = IBugPersistenceService.getAllBugs();
        return bugs.stream().map(bug -> BugDTOHelper.fromEntity(bug)).collect(Collectors.toList());
    }

    @Override
    public BugDTO addBug(BugDTO bugDTO) {
        Bug bug = BugDTOHelper.toEntity(bugDTO);
        return BugDTOHelper.fromEntity(IBugPersistenceService.addBug(bug).get());
    }

    @Override
    public BugDTO findBugById(long id) {
        return BugDTOHelper.fromEntity(IBugPersistenceService.findBugById(id).get());
    }

    @Override
    public AttachmentDTO addAttachment(AttachmentDTO attachmentDTO) {
        System.out.println(attachmentDTO.getBlob() + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(attachmentDTO.getBugDto().getCreatedByUser() + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        Attachment attachment = AttachmentDTOHelper.toEntity(attachmentDTO);
        return AttachmentDTOHelper.fromEntity(IBugPersistenceService.addAttachment(attachment).get());
    }

    @Override
    public List<AttachmentDTO> getAllAttachments() {
        List<Attachment> attachments = IBugPersistenceService.getAllAttachments();
        return attachments.stream().map(x -> AttachmentDTOHelper.fromEntity(x)).collect(Collectors.toList());
    }
}
