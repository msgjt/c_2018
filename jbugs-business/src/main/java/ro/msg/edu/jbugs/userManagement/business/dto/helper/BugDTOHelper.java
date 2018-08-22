package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.service.IUserBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.EJB;

public class BugDTOHelper {

    @EJB
    IUserBusinessService userBussinesService;

    public static BugDTO fromEntity(Bug bug){
        BugDTO bugDTO = new BugDTO();
        bugDTO.setIdBug(bug.getIdBug());
        bugDTO.setAssignedTo(UserDTOHelper.fromEntity(bug.getAssignedTo()));
        bugDTO.setCreatedByUser(UserDTOHelper.fromEntity(bug.getCreatedByUser()));
        bugDTO.setDescription(bug.getDescription());
        bugDTO.setFixedVersion(bug.getFixedVersion());
        bugDTO.setSeverity(bug.getSeverity());
        bugDTO.setTargetDate(bug.getTargetDate());
        bugDTO.setStatus(bug.getStatus());
        bugDTO.setTitle(bug.getTitle());
        bugDTO.setVersion(bug.getVersion());
        return bugDTO;
    }

    public static Bug toEntity(BugDTO bugDTO){
        Bug bug = new Bug();
        bug.setCreatedByUser(UserDTOHelper.toEntity(bugDTO.getCreatedByUser()));
        bug.setIdBug(bugDTO.getIdBug());
        bug.setAssignedTo(UserDTOHelper.toEntity(bugDTO.getAssignedTo()));
        bug.setDescription(bugDTO.getDescription());
        bug.setFixedVersion(bugDTO.getFixedVersion());
        bug.setSeverity(bugDTO.getSeverity());
        bug.setTargetDate(bugDTO.getTargetDate());
        bug.setStatus(bugDTO.getStatus());
        bug.setTitle(bugDTO.getTitle());
        bug.setVersion(bugDTO.getVersion());
        return bug;
    }

}
