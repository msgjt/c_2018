package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
@NamedQueries({
        @NamedQuery(name = History.GET_ALL_HISTORY, query = "select h from History h")
})
public class History {

    public final static String GET_ALL_HISTORY = "get_All_History";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistory;

    @ManyToOne
    @JoinColumn(name = "idBug")
    private Bug bug;

    private Date modifiedDate;

    @Enumerated(EnumType.STRING)
    private StatusType afterStatus;

    @Enumerated(EnumType.STRING)
    private StatusType beforeStatus;

    @ManyToOne
    @JoinColumn(name = "modifiedBy", nullable = false)
    private User modifiedByUser;

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

    public User getModifiedByUser() {
        return modifiedByUser;
    }

    public void setModifiedByUser(User modifiedByUser) {
        this.modifiedByUser = modifiedByUser;
    }
}
