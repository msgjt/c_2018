package ro.msg.edu.jbugs.userManagement.business.dto.bug;

import ro.msg.edu.jbugs.userManagement.persistence.entity.ExtensionEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class AttachmentDTO {
    private long idAttachment;
    private BugDTO bugDTO;
    private byte[] blob;
    private ExtensionEnum extension;
    private String name;


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

    public byte[] getBlob() {
        return blob;
    }

    public void setBlob(byte[] blob) {
        this.blob = blob;
    }


    public ExtensionEnum getExtension() {
        return extension;
    }

    public void setExtension(ExtensionEnum extension) {
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
