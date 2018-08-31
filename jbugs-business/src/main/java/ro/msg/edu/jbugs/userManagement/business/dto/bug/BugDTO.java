package ro.msg.edu.jbugs.userManagement.business.dto.bug;

import ro.msg.edu.jbugs.userManagement.business.dto.user.UserDTO;
import ro.msg.edu.jbugs.userManagement.persistence.entity.SeverityType;
import ro.msg.edu.jbugs.userManagement.persistence.entity.StatusType;

public class BugDTO {
    private Long idBug;
    private String title;
    private String description;
    private String version;
    private String targetDate;
    private StatusType status;
    private String fixedVersion;
    private SeverityType severity; //combobox
    private UserDTO createdByUser;
    private UserDTO assignedTo;//combobox


    public Long getIdBug() {
        return idBug;
    }

    public void setIdBug(Long idBug) {
        this.idBug = idBug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(String targetDate) {
        this.targetDate = targetDate;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public String getFixedVersion() {
        return fixedVersion;
    }

    public void setFixedVersion(String fixedVersion) {
        this.fixedVersion = fixedVersion;
    }

    public SeverityType getSeverity() {
        return severity;
    }

    public void setSeverity(SeverityType severity) {
        this.severity = severity;
    }

    public UserDTO getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(UserDTO createdByUser) {
        this.createdByUser = createdByUser;
    }

    public UserDTO getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(UserDTO assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return  "title: " + title +
                ", description: " + description +
                ", version: " + version +
                ", targetDate: " + targetDate +
                ", fixedVersion: " + fixedVersion +
                ", severity: " + severity +
                ", createdByUser: " + createdByUser.getUsername() +
                ", assignedTo: " + assignedTo.getUsername();
    }
}