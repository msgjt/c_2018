package ro.msg.edu.jbugs.business.service.bug;

import ro.msg.edu.jbugs.business.dto.bug.*;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;

import java.util.List;
import java.util.Map;

public interface IBugBusinessService {

    List<BugDTO> getAllBugs() ;
    BugDTO addBug(BugDTO bugDTO) throws BusinessException;
    BugDTO findBugById(long id);
    AttachmentDTO addAttachment(AttachmentDTO attachmentDTO) throws BusinessException;
    List<AttachmentDTO> getAllAttachments();
    BugDTO updateBug(BugDTO bugDTO) throws BusinessException;
    AttachmentDTO deleteAttachment(AttachmentDTO attachmentDTO) throws  BusinessException;
    List<CommentDTO> getCommentsForBug(Long bugId);
    CommentDTO addComment(CommentDTO commentDTO) throws BusinessException;
    List<BugDTO> filterBugs(List<BugFiltersDTO> filtersDTOs);
    HistoryDTO addHistory(HistoryDTO historyDTO);
    List<HistoryDTO> getAllHistory();
    Map<String,Long> getStatistics();
    Map<String, Long> getFixedBugsForUser();
    Map<String, Long> getStatisticsForNewAndRejectedBugs();
}
