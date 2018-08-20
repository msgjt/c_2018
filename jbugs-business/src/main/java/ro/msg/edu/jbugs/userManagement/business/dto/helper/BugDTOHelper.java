package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.EJB;

public class BugDTOHelper {

    public static BugDTO fromEntity(Bug bug){
        BugDTO bugDTO = new BugDTO();
        bugDTO.setIdBug(bug.getIdBug());
        bugDTO.setAssignedTo(UserDTOHelper.fromEntity(bug.getAssignedTo()));
        bugDTO.setCreatedByUser(UserDTOHelper.fromEntity(bug.getCreatedByUser()));
        bugDTO.setDescription(bug.getDescription());
        bugDTO.setFixedVersion(bug.getFixedVersion());
        bugDTO.setSeverity(bug.getSeverity());
        bugDTO.setTargetDate(bug.getTargetdate());
        bugDTO.setStatus(bug.getStatus());
        bugDTO.setTitle(bug.getTitle());
        bugDTO.setVersion(bug.getVersion());
        return bugDTO;
    }

    public static Bug toEntity(BugDTO bugDTO){
        Bug bug = new Bug();
        bug.setIdBug(bugDTO.getIdBug());
        bug.setCreatedByUser(UserDTOHelper.toEntity(bugDTO.getCreatedByUser()));
        bug.setDescription(bugDTO.getDescription());
        bug.setFixedVersion(bugDTO.getFixedVersion());
        bug.setSeverity(bugDTO.getSeverity());
        //bug.setTargetdate(bugDTO.getTargetDate());
        bug.setStatus(bugDTO.getStatus());
        bug.setTitle(bugDTO.getTitle());
        bug.setVersion(bugDTO.getVersion());
        return bug;
    }
}
