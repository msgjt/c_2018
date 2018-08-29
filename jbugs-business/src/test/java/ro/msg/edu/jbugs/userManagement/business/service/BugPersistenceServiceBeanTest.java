package ro.msg.edu.jbugs.userManagement.business.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugFiltersDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.BugDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.service.bug.BugBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.service.BugPersistenceService;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class BugPersistenceServiceBeanTest {

    @InjectMocks
    private BugBusinessService bugBusinessService;

    @Mock
    private BugDTOHelper bugDTOHelper;

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
        Bug buug = new Bug();
        buug.setIdBug(2L);
        buug.setTitle("h");
        bugs.add(buug);
        BugDTO bugDTO = new BugDTO();
        bugDTO.setIdBug(1L);
        bugDTO.setTitle("high bug");

        BugDTO bugDTO1 = new BugDTO();
        bugDTO1.setIdBug(2L);
        bugDTO1.setTitle("h");
        
        BugFiltersDTO bugFiltersDTO = new BugFiltersDTO();
        bugFiltersDTO.setField("title");
        bugFiltersDTO.setData("high bug");
        bugFiltersDTOList.add(bugFiltersDTO);
        when(bugPersistenceService.getAllBugs()).thenReturn(bugs);
        when(bugDTOHelper.fromEntity(bugs.get(0))).thenReturn(bugDTO);
        when(bugDTOHelper.fromEntity(bugs.get(1))).thenReturn(bugDTO1);
        List<BugDTO> resultList = bugBusinessService.filterBugs(bugFiltersDTOList);
        assertEquals(1,resultList.size());



    }

    @Test
    public void updateBug_ExpectedOK(){
        Bug bug = new Bug();
        BugDTO bugDTO = new BugDTO();
        bugDTO.setTitle("Test title");
        when(bugDTOHelper.toEntity(bugDTO))
                .thenReturn(bug);
        when(bugPersistenceService.updateBug(bug))
                .thenReturn(Optional.of(bug));
        when(bugDTOHelper.fromEntity(bug))
                .thenReturn(bugDTO);
        BugDTO updatedBug = bugBusinessService.updateBug(bugDTO);
        assertEquals(bugDTO.getTitle(),updatedBug.getTitle());

    }


    @Test
    public void findById_ExpectedTrue(){
        Bug bug = new Bug();
        BugDTO bugDTO = new BugDTO();
        when(bugPersistenceService.findBugById(1))
                .thenReturn(Optional.of(bug));
        when(bugDTOHelper.fromEntity(bug)).thenReturn(bugDTO);
        assertEquals(bugDTO.getIdBug(),bugBusinessService.findBugById(1).getIdBug());
    }



    @Test
    public void getAllBugs_ExpectedOK(){
        Bug bug = new Bug();
        when(bugPersistenceService.getAllBugs())
                .thenReturn(Arrays.asList(bug));
        //when(bugDTOHelper.fromEntity(any(Bug.class))).thenReturn(new BugDTO());
        assertEquals(1,bugBusinessService.getAllBugs().size());
    }
}
