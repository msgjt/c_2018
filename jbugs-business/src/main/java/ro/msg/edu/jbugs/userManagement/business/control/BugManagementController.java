package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.helper.BugDTOHelper;
import ro.msg.edu.jbugs.userManagement.persistence.dao.BugPersistanceManagement;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class BugManagementController implements BugManagement {

    @EJB
    private BugPersistanceManagement bugPersistanceManagement;

    @Override
    public List<BugDTO> getAllBugs() {
        List<Bug> bugs = bugPersistanceManagement.getAllBugs();
        return bugs.stream().map(bug -> BugDTOHelper.fromEntity(bug)).collect(Collectors.toList());
    }
}
