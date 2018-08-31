package ro.msg.edu.jbugs.userManagement.business.dto.helper;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.HistoryDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.History;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class HistoryDTOHelper {
    @EJB
    private UserDTOHelper userDTOHelper;
    @EJB
    private BugDTOHelper bugDTOHelper;

    public HistoryDTO fromEntity(History history) {
        HistoryDTO historyDTO = new HistoryDTO();
        historyDTO.setAfterStatus(history.getAfterStatus());
        historyDTO.setBeforeStatus(history.getBeforeStatus());
        historyDTO.setBugDTO(bugDTOHelper.fromEntity(history.getBug()));
        historyDTO.setIdHistory(history.getIdHistory());
        historyDTO.setUserDTO(userDTOHelper.fromEntity(history.getModifiedByUser()));
        historyDTO.setModifiedDate(history.getModifiedDate());
        return historyDTO;
    }

    public History toEntity(HistoryDTO historyDTO) {
        History history = new History();
        history.setAfterStatus(historyDTO.getAfterStatus());
        history.setBeforeStatus(historyDTO.getBeforeStatus());
        history.setBug(bugDTOHelper.toEntity(historyDTO.getBugDTO()));
        history.setIdHistory(historyDTO.getIdHistory());
        history.setModifiedByUser(userDTOHelper.toEntity(historyDTO.getUserDTO()));
        history.setModifiedDate(historyDTO.getModifiedDate());
        return history;
    }

}
