package models;

import java.util.Date;

public class Course {
    private String courseID;
    private String title;
    private String courseType;
    private String token;
    private Integer capacity;
    private Date startDate;
    private Date endDate;
    private String facultyID;
    private Integer textbookID;

    // Default Constructor
    public Course() {
    }

    // Parameterized Constructor
    public Course(String courseID, String title, String courseType, String token, Integer capacity, Date startDate, Date endDate, String facultyID, Integer textbookID) {
        this.courseID = courseID;
        this.title = title;
        this.courseType = courseType;
        this.token = token;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
        this.facultyID = facultyID;
        this.textbookID = textbookID;
    }

    // Getters and Setters
    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getTitle() {
        return title;
    }
 
    public void setTitle(String title) {
        this.title = title;
    }
 
    public String getCourseType() {
        return courseType;
    }
 
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
 
    public String getToken() {
        return token;
    }
 
    public void setToken(String token) {
        this.token = token;
    }
 
    public Integer getCapacity() {
        return capacity;
    }
 
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
 
    public Date getStartDate() {
        return startDate;
    }
 
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
 
    public Date getEndDate() {
        return endDate;
    }
 
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
 
    public String getFacultyID() {
        return facultyID;
    }
 
    public void setFacultyID(String facultyID) {
        this.facultyID = facultyID;
    }
 
    public Integer getTextbookID() {
        return textbookID;
    }
 
    public void setTextbookID(Integer textbookID) {
        this.textbookID = textbookID;
    }
}
