package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.Bug;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Stateless
public class BugDTOHelper {

    @EJB
    private UserDTOHelper userDTOHelper;

    public BugDTO fromEntity(Bug bug) {
        BugDTO bugDTO = new BugDTO();
        bugDTO.setIdBug(bug.getIdBug());
        bugDTO.setAssignedTo(userDTOHelper.fromEntity(bug.getAssignedTo()));
        bugDTO.setCreatedByUser(userDTOHelper.fromEntity(bug.getCreatedByUser()));
        bugDTO.setDescription(bug.getDescription());
        bugDTO.setFixedVersion(bug.getFixedVersion());
        bugDTO.setSeverity(bug.getSeverity());
        bugDTO.setTargetDate(fromDateToString(bug.getTargetDate()));
        bugDTO.setStatus(bug.getStatus());
        bugDTO.setTitle(bug.getTitle());
        bugDTO.setVersion(bug.getVersion());
        return bugDTO;
    }

    public Bug toEntity(BugDTO bugDTO) {
        Bug bug = new Bug();
        bug.setIdBug(bugDTO.getIdBug());
        bug.setAssignedTo(userDTOHelper.toEntity(bugDTO.getAssignedTo()));
        bug.setDescription(bugDTO.getDescription());
        bug.setFixedVersion(bugDTO.getFixedVersion());
        bug.setSeverity(bugDTO.getSeverity());
        bug.setTargetDate(fromStringToDateYearLast(bugDTO.getTargetDate()));
        bug.setStatus(bugDTO.getStatus());
        bug.setTitle(bugDTO.getTitle());
        bug.setVersion(bugDTO.getVersion());
        bug.setCreatedByUser(userDTOHelper.toEntity(bugDTO.getCreatedByUser()));
        return bug;
    }

    public Date fromStringToDate(String stringToBeParsed){
        stringToBeParsed = stringToBeParsed + " 20:00:00";
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = formatter.parse(stringToBeParsed);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public Date fromStringToDateYearLast(String stringToBeParsed){
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
    private String fromDateToString(Date date){
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String stringDate;
        stringDate = formatter.format(date);
        return stringDate;
    }

}
