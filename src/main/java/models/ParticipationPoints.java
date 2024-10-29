package models;

public class ParticipationPoints {
    private int participationID;
    private String studentID;
    private String courseID;
    private int totalPoints;
    private int maxPoints;

    // Default Constructor
    public ParticipationPoints() {
    }

    // Parameterized Constructor
    public ParticipationPoints(int participationID, String studentID, String courseID, int totalPoints, int maxPoints) {
        this.participationID = participationID;
        this.studentID = studentID;
        this.courseID = courseID;
        this.totalPoints = totalPoints;
        this.maxPoints = maxPoints;
    }

    // Getters and Setters
    public int getParticipationID() {
        return participationID;
    }

    public void setParticipationID(int participationID) {
        this.participationID = participationID;
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

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }
}
