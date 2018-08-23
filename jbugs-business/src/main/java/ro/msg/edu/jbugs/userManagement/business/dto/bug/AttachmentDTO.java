package ro.msg.edu.jbugs.userManagement.business.dto.bug;

public class AttachmentDTO {
    private long idAttachment;
    private BugDTO bugDTO;
    private String blob;


    public long getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(long idAttachment) {
        this.idAttachment = idAttachment;
    }

    public BugDTO getBugDTO() {
        return bugDTO;
    }

    public void setBugDTO(BugDTO bugDTO) {
        this.bugDTO = bugDTO;
    }

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }
}
