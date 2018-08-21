package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.BugDTOHelper;
import ro.msg.edu.jbugs.userManagement.business.service.IBugBusinessService;
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
}
