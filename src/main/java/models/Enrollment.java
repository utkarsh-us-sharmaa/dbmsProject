package models;

import java.util.Date;

public class Enrollment {
    private int enrollmentID;
    private String studentID;
    private String courseID;
    private String status;
    private Date requestDate;
    private Date approvalDate;

    // Default Constructor
    public Enrollment() {
    }

    // Parameterized Constructor
    public Enrollment(int enrollmentID, String studentID, String courseID, String status, Date requestDate, Date approvalDate) {
        this.enrollmentID = enrollmentID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.status = status;
        this.requestDate = requestDate;
        this.approvalDate = approvalDate;
    }

    // Getters and Setters
    public int getEnrollmentID() {
        return enrollmentID;
    }

    public void setEnrollmentID(int enrollmentID) {
        this.enrollmentID = enrollmentID;
    }

    public String getStudentID() {
        return studentID;
    }
 
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
 
    public String getCourseID() {
        return courseID;
    }
 
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
 
    public String getStatus() {
        return status;
    }
 
    public void setStatus(String status) {
        this.status = status;
    }
 
    public Date getRequestDate() {
        return requestDate;
    }
 
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
 
    public Date getApprovalDate() {
        return approvalDate;
    }
 
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }
}
