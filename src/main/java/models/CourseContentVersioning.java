package models;

public class CourseContentVersioning {
    private int versionID;
    private String courseID;
    private int chapterID;
    private Integer sectionID;
    private int displayOrder;

    // Default Constructor
    public CourseContentVersioning() {
    }

    // Parameterized Constructor
    public CourseContentVersioning(int versionID, String courseID, int chapterID, Integer sectionID, int displayOrder) {
        this.versionID = versionID;
        this.courseID = courseID;
        this.chapterID = chapterID;
        this.sectionID = sectionID;
        this.displayOrder = displayOrder;
    }

    // Getters and Setters
    public int getVersionID() {
        return versionID;
    }

    public void setVersionID(int versionID) {
        this.versionID = versionID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public int getChapterID() {
        return chapterID;
    }

    public void setChapterID(int chapterID) {
        this.chapterID = chapterID;
    }

    public Integer getSectionID() {
        return sectionID;
    }

    public void setSectionID(Integer sectionID) {
        this.sectionID = sectionID;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }
}
