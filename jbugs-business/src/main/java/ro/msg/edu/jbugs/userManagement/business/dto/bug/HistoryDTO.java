package ro.msg.edu.jbugs.userManagement.business.dto.bug;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.StatusType;

import java.util.Date;


public class HistoryDTO {
    private long idHistory;
    private BugDTO bugDTO;
    private Date modifiedDate;
    private StatusType afterStatus;
    private StatusType beforeStatus;
    private UserDTO userDTO;

    public long getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(long idHistory) {
        this.idHistory = idHistory;
    }

    public BugDTO getBugDTO() {
        return bugDTO;
    }

    public void setBugDTO(BugDTO bugDTO) {
        this.bugDTO = bugDTO;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public StatusType getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(StatusType afterStatus) {
        this.afterStatus = afterStatus;
    }

    public StatusType getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(StatusType beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
