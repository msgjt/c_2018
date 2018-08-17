package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "bugs")
public class Bug {
    @Transient
    private final static int MAX_STRING_LENGTH = 40;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBug;

    @Column(name = "title", length = MAX_STRING_LENGTH, nullable = false)
    private String title;

    @Column(name = "description", length = MAX_STRING_LENGTH, nullable = false)
    private String description;

    @Column(name = "version", length = MAX_STRING_LENGTH, nullable = false)
    private String version;

    @Column(name = "targetDate", length = MAX_STRING_LENGTH, nullable = false)
    private Date targetdate;

    @Column(name = "status", length = MAX_STRING_LENGTH, nullable = false)
    private String status;

    @Column(name = "fixedVersion", length = MAX_STRING_LENGTH, nullable = false)
    private String fixedVersion;

    @Column(name = "severity", length = MAX_STRING_LENGTH, nullable = false)
    private String severity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bug")
    private List<Comment> comments;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "createdByUser", nullable = false)
    private User createdByUser;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "assignedTo", nullable = false)
    private User assignedTo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bug")
    private List<Attachment> attachments;


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

    public Date getTargetdate() {
        return targetdate;
    }

    public void setTargetdate(Date targetdate) {
        this.targetdate = targetdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFixedVersion() {
        return fixedVersion;
    }

    public void setFixedVersion(String fixedVersion) {
        this.fixedVersion = fixedVersion;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public User getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(User assignedTo) {
        this.assignedTo = assignedTo;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
}
