package ro.msg.edu.jbugs.business.dto.helper;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import ro.msg.edu.jbugs.business.dto.bug.BugDTO;
import ro.msg.edu.jbugs.persistence.entity.Bug;

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

    private Logger logger = LogManager.getLogger(BugDTOHelper.class);

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
        bug.setTargetDate(fromStringToDate(bugDTO.getTargetDate()+ " 00:00:00"));
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
            logger.info(e.getMessage());
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
            logger.info(e.getMessage());
        }
        return date;
    }
    public String fromDateToString(Date date){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String stringDate;
        stringDate = formatter.format(date);
        return stringDate;
    }

}
