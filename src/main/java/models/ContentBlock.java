package models;

public class ContentBlock {
    private int contentBlockID;
    private String contentType;
    private String content;
    private int sequenceNumber;
    private int sectionID;

    // Default Constructor
    public ContentBlock() {
    }

    // Parameterized Constructor
    public ContentBlock(int contentBlockID, String contentType, String content, int sequenceNumber, int sectionID) {
        this.contentBlockID = contentBlockID;
        this.contentType = contentType;
        this.content = content;
        this.sequenceNumber = sequenceNumber;
        this.sectionID = sectionID;
    }

    // Getters and Setters
    public int getContentBlockID() {
        return contentBlockID;
    }

    public void setContentBlockID(int contentBlockID) {
        this.contentBlockID = contentBlockID;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }
}
