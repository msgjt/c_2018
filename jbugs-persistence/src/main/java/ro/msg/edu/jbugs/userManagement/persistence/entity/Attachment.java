package ro.msg.edu.jbugs.userManagement.persistence.entity;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Objects;

@Entity
@Table(name = "attachments")
@NamedQueries({
        @NamedQuery(name = Attachment.GET_ALL_ATTACHMENTS, query = "select a from Attachment a")
})
public class Attachment implements Serializable {
    @Transient
    private final static int MAX_STRING_LENGTH = 40;
    public final static String GET_ALL_ATTACHMENTS = "get_All_Attachments";

    @Lob
    @Column(name="file")
    private byte[] file;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAttachment;

    @ManyToOne
    @JoinColumn(name = "idBug")
    private Bug bug;


    public Long getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(Long idAtt) {
        this.idAttachment = idAtt;
    }

    public Bug getBug() {
        return bug;
    }

    public void setBug(Bug bug) {
        this.bug = bug;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attachment that = (Attachment) o;
        return Objects.equals(idAttachment, that.idAttachment) &&
                Objects.equals(bug, that.bug);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAttachment, bug);
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "idAtt=" + idAttachment +
                ", bug=" + bug +
                '}';
    }
}
