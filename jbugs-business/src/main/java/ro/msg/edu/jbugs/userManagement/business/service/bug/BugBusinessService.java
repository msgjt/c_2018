package ro.msg.edu.jbugs.userManagement.business.service.bug;

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
public class BugBusinessService implements IBugBusinessService {
    @EJB
    private IBugPersistenceService IBugPersistenceService;
    @EJB
    private BugDTOHelper bugDTOHelper;
    @EJB
    private AttachmentDTOHelper attachmentDTOHelper;


    @Override
    public List<BugDTO> getAllBugs() {
        List<Bug> bugs = IBugPersistenceService.getAllBugs();
        return bugs.stream().map(bugDTOHelper::fromEntity).collect(Collectors.toList());
    }

    @Override
    public BugDTO addBug(BugDTO bugDTO) {
        Bug bug = bugDTOHelper.toEntity(bugDTO);
        return bugDTOHelper.fromEntity(IBugPersistenceService.addBug(bug).get());
    }

    @Override
    public BugDTO findBugById(long id) {
        return bugDTOHelper.fromEntity(IBugPersistenceService.findBugById(id).get());
    }

    @Override
    public AttachmentDTO addAttachment(AttachmentDTO attachmentDTO) {
        Attachment attachment = attachmentDTOHelper.toEntity(attachmentDTO);
        return attachmentDTOHelper.fromEntity(IBugPersistenceService.addAttachment(attachment).get());
    }

    @Override
    public List<AttachmentDTO> getAllAttachments() {
        List<Attachment> attachments = IBugPersistenceService.getAllAttachments();
        return attachments.stream().map(attachmentDTOHelper::fromEntity).collect(Collectors.toList());
    }
}
