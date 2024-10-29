package models;

public class Section {
    private int sectionID;
    private String sectionNumber;
    private String title;
    private int chapterID;

    // Default Constructor
    public Section() {
    }

    // Parameterized Constructor
    public Section(int sectionID, String sectionNumber, String title, int chapterID) {
        this.sectionID = sectionID;
        this.sectionNumber = sectionNumber;
        this.title = title;
        this.chapterID = chapterID;
    }

    // Getters and Setters
    public int getSectionID() {
        return sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }
}
