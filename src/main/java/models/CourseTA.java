package models;

public class CourseTA {
    private int courseTAID;
    private String courseID;
    private String taID;

    // Default Constructor
    public CourseTA() {
    }

    // Parameterized Constructor
    public CourseTA(int courseTAID, String courseID, String taID) {
        this.courseTAID = courseTAID;
        this.courseID = courseID;
        this.taID = taID;
    }

    // Getters and Setters
    public int getCourseTAID() {
        return courseTAID;
    }

    public void setCourseTAID(int courseTAID) {
        this.courseTAID = courseTAID;
    }

    public String getCourseID() {
        return courseID;
    }
 
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
 
    public String getTaID() {
        return taID;
    }
 
    public void setTaID(String taID) {
        this.taID = taID;
    }
}
