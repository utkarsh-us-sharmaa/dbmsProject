package dao;

import models.StudentActivity;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentActivityDAO {

    // Add a new StudentActivity
    public boolean addStudentActivity(StudentActivity sa) {
        String sql = "INSERT INTO StudentActivity (StudentID, ActivityID, CourseID, AttemptDate, Score) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, sa.getStudentID());
            pstmt.setInt(2, sa.getActivityID());
            pstmt.setString(3, sa.getCourseID());
            pstmt.setTimestamp(4, new java.sql.Timestamp(sa.getAttemptDate().getTime()));
            if (sa.getScore() != null) {
                pstmt.setInt(5, sa.getScore());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated StudentActivityID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    sa.setStudentActivityID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve StudentActivity by ID
    public StudentActivity getStudentActivityByID(int saID) {
        String sql = "SELECT * FROM StudentActivity WHERE StudentActivityID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, saID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractStudentActivity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve StudentActivities by StudentID
    public List<StudentActivity> getStudentActivitiesByStudentID(String studentID) {
        String sql = "SELECT * FROM StudentActivity WHERE StudentID = ?";
        List<StudentActivity> activities = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                activities.add(extractStudentActivity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    // Retrieve StudentActivities by CourseID
    public List<StudentActivity> getStudentActivitiesByCourseID(String courseID) {
        String sql = "SELECT * FROM StudentActivity WHERE CourseID = ?";
        List<StudentActivity> activities = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                activities.add(extractStudentActivity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    // Update StudentActivity
    public boolean updateStudentActivity(StudentActivity sa) {
        String sql = "UPDATE StudentActivity SET StudentID = ?, ActivityID = ?, CourseID = ?, AttemptDate = ?, Score = ? WHERE StudentActivityID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, sa.getStudentID());
            pstmt.setInt(2, sa.getActivityID());
            pstmt.setString(3, sa.getCourseID());
            pstmt.setTimestamp(4, new java.sql.Timestamp(sa.getAttemptDate().getTime()));
            if (sa.getScore() != null) {
                pstmt.setInt(5, sa.getScore());
            } else {
                pstmt.setNull(5, Types.INTEGER);
            }
            pstmt.setInt(6, sa.getStudentActivityID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete StudentActivity
    public boolean deleteStudentActivity(int saID) {
        String sql = "DELETE FROM StudentActivity WHERE StudentActivityID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, saID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility method to extract StudentActivity from ResultSet
    private StudentActivity extractStudentActivity(ResultSet rs) throws SQLException {
        StudentActivity sa = new StudentActivity();
        sa.setStudentActivityID(rs.getInt("StudentActivityID"));
        sa.setStudentID(rs.getString("StudentID"));
        sa.setActivityID(rs.getInt("ActivityID"));
        sa.setCourseID(rs.getString("CourseID"));
        sa.setAttemptDate(rs.getTimestamp("AttemptDate"));
        int score = rs.getInt("Score");
        if (!rs.wasNull()) {
            sa.setScore(score);
        } else {
            sa.setScore(null);
        }
        return sa;
    }
}
