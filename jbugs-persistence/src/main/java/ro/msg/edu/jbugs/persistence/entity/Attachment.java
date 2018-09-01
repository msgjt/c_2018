package ro.msg.edu.jbugs.persistence.entity;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "attachments")
@NamedQueries({
        @NamedQuery(name = Attachment.GET_ALL_ATTACHMENTS, query = "select a from Attachment a")
})
public class Attachment {
    @Transient
    private static final int MAX_STRING_LENGTH = 40;
    public static final String GET_ALL_ATTACHMENTS = "get_All_Attachments";

    @Lob
    @Column(name="file")
    private byte[] file;

    @Column(name="fileName")
    private String fileName;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAttachment;

    @Enumerated(EnumType.STRING)
    private ExtensionEnum extension;


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

    public ExtensionEnum getExtension() {

        return extension;
    }

    public void setExtension(ExtensionEnum extension) {
        this.extension = extension;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
