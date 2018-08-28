package ro.msg.edu.jbugs.userManagement.business.service.bug;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugFiltersDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.CommentDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.AttachmentDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.BugDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.CommentDTOHelper;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Attachment;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Comment;
import ro.msg.edu.jbugs.userManagement.persistence.service.IBugPersistenceService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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


    @Override
    public List<BugDTO> getAllBugs() {
        List<Bug> bugs = bugPersistenceService.getAllBugs();
        return bugs.stream().map(bug -> bugDTOHelper.fromEntity(bug)).collect(Collectors.toList());
    }

    @Override
    public BugDTO addBug(BugDTO bugDTO) {
        Bug bug = bugDTOHelper.toEntity(bugDTO);
        Bug addedBug = bugPersistenceService.addBug(bug,new Attachment()).get();
        return bugDTOHelper.fromEntity(addedBug);
    }

    @Override
    public BugDTO findBugById(long id) {
        return bugDTOHelper.fromEntity(bugPersistenceService.findBugById(id).get());
    }

    @Override
    public AttachmentDTO addAttachment(AttachmentDTO attachmentDTO) {
        Attachment attachment = attachmentDTOHelper.toEntity(attachmentDTO);
        return attachmentDTOHelper.fromEntity(bugPersistenceService.addAttachment(attachment).get());
    }

    @Override
    public List<AttachmentDTO> getAllAttachments() {
        List<Attachment> attachments = bugPersistenceService.getAllAttachments();
        return attachments.stream().map(x -> attachmentDTOHelper.fromEntity(x)).collect(Collectors.toList());
    }

    @Override
    public BugDTO updateBug(BugDTO bugDTO) {
        Bug bug = bugDTOHelper.toEntity(bugDTO);
        return bugDTOHelper.fromEntity(bugPersistenceService.updateBug(bug).get());
    }

    public List<CommentDTO> getCommentsForBug(Long bugId){
        BugDTO bugDTO = findBugById(bugId);
        List<Comment> comments = bugPersistenceService.getCommentsForBug(bugDTOHelper.toEntity(bugDTO));
        return comments.stream().map(c -> commentDTOHelper.fromEntity(c)).collect(Collectors.toList());
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment = commentDTOHelper.toEntity(commentDTO);
        return commentDTOHelper.fromEntity(bugPersistenceService.addComment(comment).get());
    }

    @Override
    public List<BugDTO> filterBugs(List<BugFiltersDTO> filtersDTOs) {
        Predicate<BugDTO> bugFilter = x -> true;

        List<BugDTO> bugDTOs = new ArrayList<>();
        bugDTOs = getAllBugs();
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
                    bugFilter = bugFilter.and(cl -> cl.getSeverity().name().equals(criteria.getData().toUpperCase()));
                    break;
                case "status":
                    bugFilter = bugFilter.and(cl -> cl.getStatus().name().equals(criteria.getData().toUpperCase()));
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
            }
        }
        return bugDTOs.stream().filter(bugFilter).collect(Collectors.toList());
    }


    private boolean isBetweenDates(String date, String start, String end){
        Date filterDate = fromStringToDateYearLast(date);
        return filterDate.after(bugDTOHelper.fromStringToDate(start)) &&
                filterDate.before(bugDTOHelper.fromStringToDate(end));
    }

    private Date fromStringToDateYearLast(String stringToBeParsed){
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(stringToBeParsed);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Override
    public AttachmentDTO deleteAttachment(AttachmentDTO attachmentDTO) {
        Attachment attachment = attachmentDTOHelper.toEntity(attachmentDTO);
        return attachmentDTOHelper.fromEntity(bugPersistenceService.deleteAttachment(attachment).get());
    }
}
