package models;

public class Chapter {
    private int chapterID;
    private String chapterNumber;
    private String title;
    private int textbookID;

    // Default Constructor
    public Chapter() {
    }

    // Parameterized Constructor
    public Chapter(int chapterID, String chapterNumber, String title, int textbookID) {
        this.chapterID = chapterID;
        this.chapterNumber = chapterNumber;
        this.title = title;
        this.textbookID = textbookID;
    }

    // Getters and Setters
    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }

    public String getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(String chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTextbookID() {
        return textbookID;
    }

    public void setTextbookID(int textbookID) {
        this.textbookID = textbookID;
    }
}
