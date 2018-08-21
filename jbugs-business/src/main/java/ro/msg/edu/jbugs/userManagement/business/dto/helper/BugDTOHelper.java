package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.BugDTO;
import ro.msg.edu.jbugs.userManagement.business.dto.UserDTO;
import ro.msg.edu.jbugs.userManagement.business.service.IUserBusinessService;
import ro.msg.edu.jbugs.userManagement.business.service.UserBusinessService;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;
import ro.msg.edu.jbugs.userManagement.persistence.service.UserPersistenceService;

public class BugDTOHelper {


    static IUserBusinessService userBussinesService = new UserBusinessService();

    public static BugDTO fromEntity(Bug bug){
        BugDTO bugDTO = new BugDTO();
        bugDTO.setIdBug(bug.getIdBug());
        bugDTO.setAssignedTo(bug.getAssignedTo().getUsername());
        bugDTO.setCreatedByUser(bug.getCreatedByUser().getUsername());
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
       

        UserDTO userDTO = userBussinesService.getUserByUsername(bugDTO.getCreatedByUser());
        System.out.println(userDTO.getUsername()+ "ssssssssssssssssssssssssssss");
        bug.setCreatedByUser(UserDTOHelper.toEntity(userDTO));
        userDTO = userBussinesService.getUserByUsername(bugDTO.getAssignedTo());
        bug.setIdBug(bugDTO.getIdBug());
        bug.setAssignedTo(UserDTOHelper.toEntity(userDTO));
        bug.setDescription(bugDTO.getDescription());
        bug.setFixedVersion(bugDTO.getFixedVersion());
        bug.setSeverity(bugDTO.getSeverity());
        bug.setTargetdate(bugDTO.getTargetDate());
        bug.setStatus(bugDTO.getStatus());
        bug.setTitle(bugDTO.getTitle());
        bug.setVersion(bugDTO.getVersion());
        return bug;
    }

}
