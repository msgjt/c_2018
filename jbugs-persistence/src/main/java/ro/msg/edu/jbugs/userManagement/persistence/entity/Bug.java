package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bugs")
@NamedQueries({
        @NamedQuery(name = Bug.GET_ALL_BUGS, query = "select b from Bug b order by b.targetDate desc")
})
public class Bug {
    @Transient
    private final static int MAX_STRING_LENGTH = 40;
    public static final String GET_ALL_BUGS = "get_All_Bugs";

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
    private Date targetDate;

    @Column(name = "status", length = MAX_STRING_LENGTH, nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "fixedVersion", length = MAX_STRING_LENGTH, nullable = false)

    private String fixedVersion;

    @Column(name = "severity", length = MAX_STRING_LENGTH, nullable = false)
    @Enumerated(EnumType.STRING)
    private SeverityEnum severity;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bug")
    private List<Comment> comments;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "createdByUser")
    private User createdByUser;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "assignedTo")
    private User assignedTo;

    @OneToMany(cascade = CascadeType.PERSIST,mappedBy = "bug")
    private List<Attachment> attachments = new ArrayList<>();


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

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetdate) {
        this.targetDate = targetdate;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getFixedVersion() {
        return fixedVersion;
    }

    public void setFixedVersion(String fixedVersion) {
        this.fixedVersion = fixedVersion;
    }

    public SeverityEnum getSeverity() {
        return severity;
    }

    public void setSeverity(SeverityEnum severity) {
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
