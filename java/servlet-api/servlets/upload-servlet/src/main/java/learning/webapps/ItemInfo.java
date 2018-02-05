package learning.webapps;


public class ItemInfo {

    private boolean uploadToMemory;

    private String description;

    private Long fileSize;

    private String fileName;

    private String contentType;

    public boolean isUploadToMemory() {
        return uploadToMemory;
    }

    public String getDescription() {
        return description;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setUploadToMemory(boolean uploadToMemory) {
        this.uploadToMemory = uploadToMemory;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
