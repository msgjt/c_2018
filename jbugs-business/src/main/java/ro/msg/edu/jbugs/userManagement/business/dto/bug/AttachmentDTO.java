package ro.msg.edu.jbugs.userManagement.business.dto.bug;

import ro.msg.edu.jbugs.userManagement.business.dto.bug.BugDTO;

public class AttachmentDTO {
    private long idAttachment;
    private BugDTO bugDto;
    private String blob;


    public long getIdAttachment() {
        return idAttachment;
    }

    public void setIdAttachment(long idAttachment) {
        this.idAttachment = idAttachment;
    }

    public BugDTO getBugDto() {
        return bugDto;
    }

    public void setBugDto(BugDTO bugDto) {
        this.bugDto = bugDto;
    }

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }
}
