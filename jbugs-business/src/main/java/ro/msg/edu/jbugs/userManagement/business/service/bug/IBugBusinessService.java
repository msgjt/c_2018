package ro.msg.edu.jbugs.userManagement.business.service.bug;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.*;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugFiltersDTO;
import ro.msg.edu.jbugs.userManagement.business.exceptions.BusinessException;

import java.util.List;

public interface IBugBusinessService {

    List<BugDTO> getAllBugs() ;
    BugDTO addBug(BugDTO bugDTO) throws BusinessException;
    BugDTO findBugById(long id);
    AttachmentDTO addAttachment(AttachmentDTO attachmentDTO) throws BusinessException;
    List<AttachmentDTO> getAllAttachments();
    BugDTO updateBug(BugDTO bugDTO) throws BusinessException;
    AttachmentDTO deleteAttachment(AttachmentDTO attachmentDTO) throws  BusinessException;
    List<CommentDTO> getCommentsForBug(Long bugId);
    CommentDTO addComment(CommentDTO commentDTO);
    List<BugDTO> filterBugs(List<BugFiltersDTO> filtersDTOs);
    HistoryDTO addHistory(HistoryDTO historyDTO);
    List<HistoryDTO> getAllHistory();
}
