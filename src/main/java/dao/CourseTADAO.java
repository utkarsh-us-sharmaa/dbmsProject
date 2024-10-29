package dao;

import models.CourseTA;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseTADAO {

    // Add a new CourseTA
    public boolean addCourseTA(CourseTA courseTA) {
        String sql = "INSERT INTO CourseTA (CourseID, TAID) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, courseTA.getCourseID());
            pstmt.setString(2, courseTA.getTaID());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated CourseTAID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    courseTA.setCourseTAID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve CourseTA by ID
    public CourseTA getCourseTAByID(int courseTAID) {
        String sql = "SELECT * FROM CourseTA WHERE CourseTAID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, courseTAID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractCourseTA(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve CourseTAs by CourseID
    public List<CourseTA> getCourseTAsByCourseID(String courseID) {
        String sql = "SELECT * FROM CourseTA WHERE CourseID = ?";
        List<CourseTA> courseTAs = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                courseTAs.add(extractCourseTA(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseTAs;
    }

    // Retrieve CourseTAs by TAID
    public List<CourseTA> getCourseTAsByTAID(String taID) {
        String sql = "SELECT * FROM CourseTA WHERE TAID = ?";
        List<CourseTA> courseTAs = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, taID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                courseTAs.add(extractCourseTA(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courseTAs;
    }

    // Update CourseTA (e.g., change TA assignment)
    public boolean updateCourseTA(CourseTA courseTA) {
        String sql = "UPDATE CourseTA SET CourseID = ?, TAID = ? WHERE CourseTAID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseTA.getCourseID());
            pstmt.setString(2, courseTA.getTaID());
            pstmt.setInt(3, courseTA.getCourseTAID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete CourseTA
    public boolean deleteCourseTA(int courseTAID) {
        String sql = "DELETE FROM CourseTA WHERE CourseTAID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, courseTAID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility method to extract CourseTA from ResultSet
    private CourseTA extractCourseTA(ResultSet rs) throws SQLException {
        CourseTA courseTA = new CourseTA();
        courseTA.setCourseTAID(rs.getInt("CourseTAID"));
        courseTA.setCourseID(rs.getString("CourseID"));
        courseTA.setTaID(rs.getString("TAID"));
        return courseTA;
    }
}
