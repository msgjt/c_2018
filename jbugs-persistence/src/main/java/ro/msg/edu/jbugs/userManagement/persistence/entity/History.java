package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
public class History {
    @Transient
    private final static int MAX_STRING_LENGTH = 40;
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistory;

    @ManyToOne
    @JoinColumn(name = "idBug", nullable = false)
    public Bug bug;

    @Column(name = "modifiedDate", length = MAX_STRING_LENGTH, nullable = false)
    private Date modifiedDate;

    @Column(name = "afterStatus", length = MAX_STRING_LENGTH, nullable = false)
    private Date afterStatus;

    @Column(name = "beforeStatus", length = MAX_STRING_LENGTH, nullable = false)
    private Date beforeStatus;

    @ManyToOne
    @JoinColumn(name = "modifiedBy", nullable = false)
    public User user;

    public Long getIdHistory() {
        return idHistory;
    }

    public void setIdHistory(Long idHistory) {
        this.idHistory = idHistory;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getAfterStatus() {
        return afterStatus;
    }

    public void setAfterStatus(Date afterStatus) {
        this.afterStatus = afterStatus;
    }

    public Date getBeforeStatus() {
        return beforeStatus;
    }

    public void setBeforeStatus(Date beforeStatus) {
        this.beforeStatus = beforeStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
