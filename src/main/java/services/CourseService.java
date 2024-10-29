package services;

import dao.CourseDAO;
import java.util.List;
import models.Course;

public class CourseService {
    private CourseDAO courseDAO = new CourseDAO();

    // Create a new course
    public boolean createCourse(Course course) {
        // Additional business logic can be added here
        return courseDAO.addCourse(course);
    }

    // Retrieve a course by ID
    public Course getCourseByID(String courseID) {
        return courseDAO.getCourseByID(courseID);
    }

    // Retrieve all courses
    public List<Course> getAllCourses() {
        return courseDAO.getAllCourses();
    }

    // Retrieve courses by FacultyID
    public List<Course> getCoursesByFacultyID(String facultyID) {
        return courseDAO.getCoursesByFacultyID(facultyID);
    }

    // Update a course
    public boolean updateCourse(Course course) {
        return courseDAO.updateCourse(course);
    }

    // Delete a course
    public boolean deleteCourse(String courseID) {
        return courseDAO.deleteCourse(courseID);
    }

    // Additional course-related business logic can be added here
}
