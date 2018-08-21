package ro.msg.edu.jbugs.userManagement.business.control;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import java.util.List;

public interface BugManagement {

    List<BugDTO> getAllBugs();
    BugDTO addBug(BugDTO bugDTO);
}
