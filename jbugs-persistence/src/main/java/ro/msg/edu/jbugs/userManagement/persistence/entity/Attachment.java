package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "attachments")
public class Attachment {
    @Transient
    private final static int MAX_STRING_LENGTH = 40;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAtt;

    @ManyToOne
    @JoinColumn(name = "idBug", nullable = false)
    public Bug bug;

    public Long getIdAtt() {
        return idAtt;
    }

    public void setIdAtt(Long idAtt) {
        this.idAtt = idAtt;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(idAtt, that.idAtt) &&
                Objects.equals(bug, that.bug);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAtt, bug);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "idAtt=" + idAtt +
                ", bug=" + bug +
                '}';
    }
}
