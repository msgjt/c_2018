package ro.msg.edu.jbugs.business.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ro.msg.edu.jbugs.business.dto.bug.CommentDTO;
import ro.msg.edu.jbugs.business.dto.helper.AttachmentDTOHelper;
import ro.msg.edu.jbugs.business.dto.helper.BugDTOHelper;
import ro.msg.edu.jbugs.business.dto.helper.CommentDTOHelper;
import ro.msg.edu.jbugs.business.service.bug.BugBusinessService;
import ro.msg.edu.jbugs.persistence.entity.*;
import ro.msg.edu.jbugs.business.dto.bug.AttachmentDTO;
import ro.msg.edu.jbugs.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.business.dto.bug.BugFiltersDTO;
import ro.msg.edu.jbugs.business.exceptions.BusinessException;
import ro.msg.edu.jbugs.persistence.service.bug.BugPersistenceService;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BugPersistenceServiceBeanTest {
    @InjectMocks
    private BugBusinessService bugBusinessService;

    @Mock
    private BugDTOHelper bugDTOHelper;

    @Mock
    private CommentDTOHelper commentDTOHelper;

    @Mock
    private AttachmentDTOHelper attachmentDTOHelper;

    @Mock
    private BugPersistenceService bugPersistenceService;


    private List<BugFiltersDTO> bugFiltersDTOList;
    private List<Bug> bugs;

    @Before
    public void initialize(){
        bugs = new ArrayList<>();
        bugFiltersDTOList = new ArrayList<>();
    }

    @Test
    public void filterBugs_expectedTrue(){
        Bug bug = new Bug();
        bug.setIdBug(1L);
        bug.setTitle("high bug");
        bugs.add(bug);

        Bug secondBug = new Bug();
        secondBug.setIdBug(2L);
        secondBug.setTitle("h");
        bugs.add(secondBug);
        BugDTO bugDTO = new BugDTO();
        bugDTO.setIdBug(1L);
        bugDTO.setTitle("high bug");

        BugDTO secondBugDto = new BugDTO();
        secondBugDto.setIdBug(2L);
        secondBugDto.setTitle("h");

        BugFiltersDTO bugFiltersDTO = new BugFiltersDTO();
        bugFiltersDTO.setField("title");
        bugFiltersDTO.setData("high bug");
        bugFiltersDTOList.add(bugFiltersDTO);

        when(bugPersistenceService.getAllBugs()).thenReturn(bugs);
        when(bugDTOHelper.fromEntity(bugs.get(0))).thenReturn(bugDTO);
        when(bugDTOHelper.fromEntity(bugs.get(1))).thenReturn(secondBugDto);
        List<BugDTO> resultList = bugBusinessService.filterBugs(bugFiltersDTOList);


        assertEquals(1,resultList.size());



    }

    @Test
    public void updateBug_ExpectedOK(){
        Bug bug = new Bug();
        BugDTO bugDTO = new BugDTO();
        bugDTO.setIdBug(2L);
        bugDTO.setTitle("Test title");
        when(bugDTOHelper.toEntity(bugDTO))
                .thenReturn(bug);
        when(bugPersistenceService.updateBug(bug))
                .thenReturn(Optional.of(bug));
        when(bugDTOHelper.fromEntity(bug))
                .thenReturn(bugDTO);
        BugDTO updatedBug = null;
        try {
            updatedBug = bugBusinessService.updateBug(bugDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        assertEquals(bugDTO.getTitle(),updatedBug.getTitle());

    }


    @Test
    public void findById_ExpectedTrue(){
        Bug bug = new Bug();
        BugDTO bugDTO = new BugDTO();
        when(bugPersistenceService.findBugById(1))
                .thenReturn(Optional.of(bug));
        when(bugDTOHelper.fromEntity(bug)).thenReturn(bugDTO);
        assertEquals(bug.getIdBug(),bugBusinessService.findBugById(1).getIdBug());
    }



    @Test
    public void getAllBugs_ExpectedOK(){
        Bug bug = new Bug();
        when(bugPersistenceService.getAllBugs())
                .thenReturn(Arrays.asList(bug));
        assertEquals(1,bugBusinessService.getAllBugs().size());
    }

    @Test
    public void addBug_ExpectedOK(){
        Bug bug = new Bug();
        BugDTO bugDTO = new BugDTO();
        bugDTO.setTitle("Test title");
        bugDTO.setSeverity(SeverityEnum.CRITICAL);
        bugDTO.setTargetDate("2018-01-01 00:00:00");
        bugDTO.setStatus(StatusEnum.CLOSED);
        when(bugDTOHelper.toEntity(bugDTO))
                .thenReturn(bug);
        when(bugPersistenceService.addBug(bug,new Attachment()))
                .thenReturn(Optional.of(bug));
        when(bugDTOHelper.fromEntity(bug))
                .thenReturn(bugDTO);
        BugDTO addedBug = null;
        try {
            addedBug = bugBusinessService.addBug(bugDTO);
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        assertEquals(bugDTO.getTitle(),addedBug.getTitle());

    }

    @Test
    public void addAttachment_ExpectedOK(){
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setIdAttachment(15);
        attachmentDTO.setBugDTO(new BugDTO());
        attachmentDTO.setBlob(new byte[100]);
        Attachment attachment = new Attachment();
        when(attachmentDTOHelper.toEntity(attachmentDTO))
                .thenReturn(attachment);
        when(bugPersistenceService.addAttachment(attachment))
                .thenReturn(Optional.of(attachment));
        when(attachmentDTOHelper.fromEntity(attachment))
                .thenReturn(attachmentDTO);
        try {
            assertEquals(attachmentDTO.getIdAttachment(),bugBusinessService.addAttachment(attachmentDTO).getIdAttachment());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteAttachment_ExpectedOK(){
        AttachmentDTO attachmentDTO = new AttachmentDTO();
        attachmentDTO.setIdAttachment(1);
        Attachment attachment = new Attachment();
        when(attachmentDTOHelper.toEntity(attachmentDTO))
                .thenReturn(attachment);
        when(bugPersistenceService.deleteAttachment(attachment))
                .thenReturn(Optional.of(attachment));
        when(attachmentDTOHelper.fromEntity(attachment))
                .thenReturn(attachmentDTO);
        try {
            assertEquals(attachmentDTO.getIdAttachment(),bugBusinessService.deleteAttachment(attachmentDTO).getIdAttachment());
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addComment_ExpectedOK(){
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setDate(new Date());
        Comment comment = new Comment();
        when(commentDTOHelper.toEntity(commentDTO))
                .thenReturn(comment);
        when(bugPersistenceService.addComment(comment))
                .thenReturn(Optional.of(comment));
        when(commentDTOHelper.fromEntity(comment))
                .thenReturn(commentDTO);
        try {
            Assert.assertEquals(commentDTO.getDate(), bugBusinessService.addComment(commentDTO).getDate());
        }catch(BusinessException e){
            e.printStackTrace();

        }
    }

    @Test
    public void getAllAttachments_ExpectedOK(){
        Attachment attachment = new Attachment();
        when(bugPersistenceService.getAllAttachments())
                .thenReturn(Arrays.asList(attachment));
        assertEquals(1,bugBusinessService.getAllAttachments().size());
    }

    @Test
    public void getCommentsForBug_ExpectedOK(){
        Bug bug = new Bug();
        bug.setIdBug(1L);
        Comment comment = new Comment();
        comment.setText("Test text");
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        BugDTO bugDTO = new BugDTO();
        CommentDTO commentDTO = new CommentDTO();
        when(bugPersistenceService.findBugById(1))
                .thenReturn(Optional.of(bug));
        when(bugDTOHelper.fromEntity(bug)).thenReturn(bugDTO);
        when(bugDTOHelper.toEntity(bugDTO)).thenReturn(bug);
        when(bugPersistenceService.getCommentsForBug(bug))
                .thenReturn(comments);
        when(commentDTOHelper.fromEntity(comments.get(0))).thenReturn(commentDTO);
        assertEquals(1,bugBusinessService.getCommentsForBug(1L).size());

    }
}
