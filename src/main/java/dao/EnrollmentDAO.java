package dao;

import models.Enrollment;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {

    // Add a new enrollment
    public boolean addEnrollment(Enrollment enrollment) {
        String sql = "INSERT INTO Enrollment (StudentID, CourseID, Status, RequestDate, ApprovalDate) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, enrollment.getStudentID());
            pstmt.setString(2, enrollment.getCourseID());
            pstmt.setString(3, enrollment.getStatus());
            pstmt.setDate(4, new java.sql.Date(enrollment.getRequestDate().getTime()));
            if (enrollment.getApprovalDate() != null) {
                pstmt.setDate(5, new java.sql.Date(enrollment.getApprovalDate().getTime()));
            } else {
                pstmt.setNull(5, Types.DATE);
            }

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated EnrollmentID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    enrollment.setEnrollmentID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve enrollment by ID
    public Enrollment getEnrollmentByID(int enrollmentID) {
        String sql = "SELECT * FROM Enrollment WHERE EnrollmentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, enrollmentID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractEnrollment(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve enrollments by CourseID
    public List<Enrollment> getEnrollmentsByCourseID(String courseID) {
        String sql = "SELECT * FROM Enrollment WHERE CourseID = ?";
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                enrollments.add(extractEnrollment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    // Retrieve enrollments by StudentID
    public List<Enrollment> getEnrollmentsByStudentID(String studentID) {
        String sql = "SELECT * FROM Enrollment WHERE StudentID = ?";
        List<Enrollment> enrollments = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                enrollments.add(extractEnrollment(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollments;
    }

    // Update enrollment status
    public boolean updateEnrollmentStatus(int enrollmentID, String status, Date approvalDate) {
        String sql = "UPDATE Enrollment SET Status = ?, ApprovalDate = ? WHERE EnrollmentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, status);
            if (approvalDate != null) {
                pstmt.setDate(2, new java.sql.Date(approvalDate.getTime()));
            } else {
                pstmt.setNull(2, Types.DATE);
            }
            pstmt.setInt(3, enrollmentID);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete enrollment
    public boolean deleteEnrollment(int enrollmentID) {
        String sql = "DELETE FROM Enrollment WHERE EnrollmentID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, enrollmentID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility method to extract Enrollment from ResultSet
    private Enrollment extractEnrollment(ResultSet rs) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentID(rs.getInt("EnrollmentID"));
        enrollment.setStudentID(rs.getString("StudentID"));
        enrollment.setCourseID(rs.getString("CourseID"));
        enrollment.setStatus(rs.getString("Status"));
        enrollment.setRequestDate(rs.getDate("RequestDate"));
        enrollment.setApprovalDate(rs.getDate("ApprovalDate"));
        return enrollment;
    }
}
