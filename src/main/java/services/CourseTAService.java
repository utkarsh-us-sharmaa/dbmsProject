package services;

import dao.CourseTADAO;
import models.CourseTA;

import java.util.List;

public class CourseTAService {
    private CourseTADAO courseTADAO = new CourseTADAO();

    // Assign TA to a course
    public boolean assignTA(CourseTA courseTA) {
        return courseTADAO.addCourseTA(courseTA);
    }

    // Remove TA from a course
    public boolean removeTA(int courseTAID) {
        return courseTADAO.deleteCourseTA(courseTAID);
    }

    // Retrieve TAs for a course
    public List<CourseTA> getTAsByCourseID(String courseID) {
        return courseTADAO.getCourseTAsByCourseID(courseID);
    }

    // Retrieve courses assigned to a TA
    public List<CourseTA> getCoursesByTAID(String taID) {
        return courseTADAO.getCourseTAsByTAID(taID);
    }

    // Additional TA-related business logic can be added here
}
