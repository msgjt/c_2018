package ro.msg.edu.jbugs.business.service.bug;

import ro.msg.edu.jbugs.business.dto.bug.*;
import ro.msg.edu.jbugs.business.dto.helper.CommentDTOHelper;
import ro.msg.edu.jbugs.business.service.notification.NotificationBusinessService;
import ro.msg.edu.jbugs.persistence.entity.*;
import ro.msg.edu.jbugs.business.dto.helper.AttachmentDTOHelper;
import ro.msg.edu.jbugs.business.dto.helper.BugDTOHelper;
import ro.msg.edu.jbugs.business.dto.helper.HistoryDTOHelper;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.business.exceptions.ExceptionCode;
import ro.msg.edu.jbugs.persistence.service.bug.IBugPersistenceService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Stateless
public class BugBusinessService implements IBugBusinessService {
    @EJB
    private IBugPersistenceService bugPersistenceService;
    @EJB
    private BugDTOHelper bugDTOHelper;
    @EJB
    private AttachmentDTOHelper attachmentDTOHelper;
    @EJB
    private CommentDTOHelper commentDTOHelper;

    @EJB
    private HistoryDTOHelper historyDTOHelper;

    @EJB
    private NotificationBusinessService notificationBusinessService;


    @Override
    public List<BugDTO> getAllBugs() {
        List<Bug> bugs = bugPersistenceService.getAllBugs();
        return bugs.stream().map(bug -> bugDTOHelper.fromEntity(bug)).collect(Collectors.toList());
    }

    @Override
    public BugDTO addBug(BugDTO bugDTO) throws BusinessException {
        if(bugDTO.getTargetDate() == null || bugDTO.getStatus() == null || bugDTO.getSeverity()==null){
            throw new BusinessException(ExceptionCode.BUG_VALIDATION_EXCEPTION);
        }
        if(bugDTO.getDescription().length()>250){
            throw new BusinessException(ExceptionCode.DESCRIPTION_VALIDATION_EXCEPTION);
        }
        Bug bug = bugDTOHelper.toEntity(bugDTO);
        Optional bugOptional = bugPersistenceService.addBug(bug,new Attachment());
        Bug addedBug = null;
        if(bugOptional.isPresent()) {
            addedBug = (Bug) bugOptional.get();
            notificationBusinessService.generateNotification(NotificationEnum.BUG_UPDATED, null, bugDTO);
            return bugDTOHelper.fromEntity(addedBug);
        }
        return null;
    }

    @Override
    public BugDTO findBugById(long id) {
        Optional bugOptional = bugPersistenceService.findBugById(id);
        Bug foundBug = null;
        if(bugOptional.isPresent()){
            foundBug = (Bug) bugOptional.get();
            return bugDTOHelper.fromEntity(foundBug);
        }
        return null;
    }

    @Override
    public AttachmentDTO addAttachment(AttachmentDTO attachmentDTO) throws BusinessException {
        if(attachmentDTO.getBugDTO() == null  || attachmentDTO.getBlob()==null){
            throw new BusinessException(ExceptionCode.ATTACHMENT_VALIDATION_EXCEPTION);
        }
        Attachment attachment = attachmentDTOHelper.toEntity(attachmentDTO);
        Optional attachmentOptional = bugPersistenceService.addAttachment(attachment);
        Attachment addedAttachment = null;
        if(attachmentOptional.isPresent()){
            addedAttachment = (Attachment) attachmentOptional.get();
            return attachmentDTOHelper.fromEntity(addedAttachment);
        }
        return null;
    }

    @Override
    public List<AttachmentDTO> getAllAttachments() {
        List<Attachment> attachments = bugPersistenceService.getAllAttachments();
        return attachments.stream().map(x -> attachmentDTOHelper.fromEntity(x)).collect(Collectors.toList());
    }

    @Override
    public BugDTO updateBug(BugDTO bugDTO) throws BusinessException{
        if(bugDTO.getIdBug() <= 0){
            throw new BusinessException(ExceptionCode.BUG_VALIDATION_EXCEPTION);
        }
        Bug bug = bugDTOHelper.toEntity(bugDTO);
        Optional bugOptional = bugPersistenceService.updateBug(bug);
        Bug updatedBugBug = null;
        if(bugOptional.isPresent()){
            updatedBugBug = (Bug) bugOptional.get();
            return bugDTOHelper.fromEntity(updatedBugBug);
        }
        return null;
    }

    public List<CommentDTO> getCommentsForBug(Long bugId){
        BugDTO bugDTO = findBugById(bugId);
        List<Comment> comments = bugPersistenceService.getCommentsForBug(bugDTOHelper.toEntity(bugDTO));
        return comments.stream().map(c -> commentDTOHelper.fromEntity(c)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) throws BusinessException {
        Comment comment = commentDTOHelper.toEntity(commentDTO);
        if(commentDTO.getText().length()>100){
            throw new BusinessException(ExceptionCode.COMMENT_VALIDATION_EXCEPTION);
        }
        Optional commentOptional = bugPersistenceService.addComment(comment);
        Comment addedComment = null;
        if(commentOptional.isPresent()){
            addedComment = (Comment) commentOptional.get();
            return commentDTOHelper.fromEntity(addedComment);
        }
        return null;
    }

    @Override
    public List<BugDTO> filterBugs(List<BugFiltersDTO> filtersDTOs) {
        Predicate<BugDTO> bugFilter = x -> true;
        List<BugDTO> bugDTOs;
        bugDTOs = this.getAllBugs();
        for(BugFiltersDTO criteria: filtersDTOs){
            switch (criteria.getField()){
                case "title":
                    bugFilter = bugFilter.and(cl -> cl.getTitle().contains(criteria.getData().toLowerCase()));
                    break;
                case "description":
                    bugFilter = bugFilter.and(cl -> cl.getDescription().contains(criteria.getData()));
                    break;
                case "assignedTo":
                    bugFilter = bugFilter.and(cl -> cl.getAssignedTo().getUsername().equals(criteria.getData()));
                    break;
                case "createdByUser":
                    bugFilter = bugFilter.and(cl -> cl.getCreatedByUser().getUsername().equals(criteria.getData()));
                    break;
                case "severity":
                    bugFilter = bugFilter.and(cl -> cl.getSeverity().name().equalsIgnoreCase(criteria.getData()));
                    break;
                case "status":
                    bugFilter = bugFilter.and(cl -> cl.getStatus().name().equalsIgnoreCase(criteria.getData()));
                    break;
                case "version":
                    bugFilter = bugFilter.and(cl -> cl.getVersion().equals(criteria.getData()));
                    break;
                case "fixedVersion":
                    bugFilter = bugFilter.and(cl -> cl.getFixedVersion().equals(criteria.getData()));
                    break;
                case "targetDate":
                    bugFilter = bugFilter.and(cl -> isBetweenDates(cl.getTargetDate(),criteria.getData(),criteria.getEndData()));
                    break;
                default:
                    break;
            }
        }
        return bugDTOs.stream().filter(bugFilter).collect(Collectors.toList());
    }

    @Override
    public HistoryDTO addHistory(HistoryDTO historyDTO) {
        History history = historyDTOHelper.toEntity(historyDTO);
        Optional historyOptional = bugPersistenceService.addHistory(history);
        History addedHistory = null;
        if(historyOptional.isPresent()){
            addedHistory = (History) historyOptional.get();
            return historyDTOHelper.fromEntity(addedHistory);
        }
        return null;
    }

    @Override
    public List<HistoryDTO> getAllHistory() {
        List<History> histories = bugPersistenceService.getAllHistory();
        return histories.stream().map(x -> historyDTOHelper.fromEntity(x)).collect(Collectors.toList());
    }

    @Override
    public Map<String, Long> getStatistics() {
        return bugPersistenceService.getStatistics();
    }

    @Override
    public Map<String, Long> getFixedBugsForUser() {
        return bugPersistenceService.getFixedBugsForUser();
    }

    @Override
    public Map<String, Long> getStatisticsForNewAndRejectedBugs() {
        return bugPersistenceService.getStatisticsForNewAndRejectedBugs();
    }


    private boolean isBetweenDates(String date, String start, String end){
        Date filterDate = bugDTOHelper.fromStringToDateYearLast(date);
        return filterDate.after(bugDTOHelper.fromStringToDate(start)) &&
                filterDate.before(bugDTOHelper.fromStringToDate(end));
    }

    @Override
    public AttachmentDTO deleteAttachment(AttachmentDTO attachmentDTO) throws BusinessException{
        if(attachmentDTO.getIdAttachment() <= 0){
            throw new BusinessException(ExceptionCode.ATTACHMENT_VALIDATION_EXCEPTION);
        }
        Attachment attachment = attachmentDTOHelper.toEntity(attachmentDTO);
        return attachmentDTOHelper.fromEntity(bugPersistenceService.deleteAttachment(attachment).get());
    }
}
