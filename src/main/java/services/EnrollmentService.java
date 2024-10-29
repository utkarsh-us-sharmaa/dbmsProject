package services;

import dao.EnrollmentDAO;
import java.util.Date;
import java.util.List;
import models.Enrollment;

public class EnrollmentService {
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    // Request enrollment
    public boolean requestEnrollment(Enrollment enrollment) {
        // Set status to 'Pending' by default
        enrollment.setStatus("Pending");
        enrollment.setRequestDate(new Date()); // java.util.Date
        enrollment.setApprovalDate(null);
        return enrollmentDAO.addEnrollment(enrollment);
    }

    // Approve enrollment
    public boolean approveEnrollment(int enrollmentID) {
        Date utilDate = new Date(); // java.util.Date
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Conversion
        return enrollmentDAO.updateEnrollmentStatus(enrollmentID, "Approved", sqlDate);
    }

    // Retrieve enrollments by CourseID
    public List<Enrollment> getEnrollmentsByCourseID(String courseID) {
        return enrollmentDAO.getEnrollmentsByCourseID(courseID);
    }

    // Retrieve enrollments by StudentID
    public List<Enrollment> getEnrollmentsByStudentID(String studentID) {
        return enrollmentDAO.getEnrollmentsByStudentID(studentID);
    }

    // Cancel enrollment
    public boolean cancelEnrollment(int enrollmentID) {
        return enrollmentDAO.deleteEnrollment(enrollmentID);
    }

    // Additional enrollment-related business logic can be added here
}
