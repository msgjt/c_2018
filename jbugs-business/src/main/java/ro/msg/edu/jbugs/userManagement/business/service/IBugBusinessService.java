package ro.msg.edu.jbugs.userManagement.business.service;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import java.util.List;

public interface IBugBusinessService {

    List<BugDTO> getAllBugs();
    BugDTO addBug(BugDTO bugDTO);
}
