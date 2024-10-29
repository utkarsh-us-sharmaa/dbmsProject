package models;

import java.util.Date;

public class StudentActivity {
    private int studentActivityID;
    private String studentID;
    private int activityID;
    private String courseID;
    private Date attemptDate;
    private Integer score;

    // Default Constructor
    public StudentActivity() {
    }

    // Parameterized Constructor
    public StudentActivity(int studentActivityID, String studentID, int activityID, String courseID, Date attemptDate, Integer score) {
        this.studentActivityID = studentActivityID;
        this.studentID = studentID;
        this.activityID = activityID;
        this.courseID = courseID;
        this.attemptDate = attemptDate;
        this.score = score;
    }

    // Getters and Setters
    public int getStudentActivityID() {
        return studentActivityID;
    }

    public void setStudentActivityID(int studentActivityID) {
        this.studentActivityID = studentActivityID;
    }

    public String getStudentID() {
        return studentID;
    }
 
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
 
    public int getActivityID() {
        return activityID;
    }
 
    public void setActivityID(int activityID) {
        this.activityID = activityID;
    }
 
    public String getCourseID() {
        return courseID;
    }
 
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
 
    public Date getAttemptDate() {
        return attemptDate;
    }
 
    public void setAttemptDate(Date attemptDate) {
        this.attemptDate = attemptDate;
    }
 
    public Integer getScore() {
        return score;
    }
 
    public void setScore(Integer score) {
        this.score = score;
    }
}
