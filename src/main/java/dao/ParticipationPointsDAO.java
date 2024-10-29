package dao;

import models.ParticipationPoints;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParticipationPointsDAO {

    // Add a new ParticipationPoints record
    public boolean addParticipationPoints(ParticipationPoints pp) {
        String sql = "INSERT INTO ParticipationPoints (StudentID, CourseID, TotalPoints, MaxPoints) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, pp.getStudentID());
            pstmt.setString(2, pp.getCourseID());
            pstmt.setInt(3, pp.getTotalPoints());
            pstmt.setInt(4, pp.getMaxPoints());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated ParticipationID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    pp.setParticipationID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve ParticipationPoints by ID
    public ParticipationPoints getParticipationPointsByID(int ppID) {
        String sql = "SELECT * FROM ParticipationPoints WHERE ParticipationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ppID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractParticipationPoints(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve ParticipationPoints by StudentID and CourseID
    public ParticipationPoints getParticipationPoints(String studentID, String courseID) {
        String sql = "SELECT * FROM ParticipationPoints WHERE StudentID = ? AND CourseID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentID);
            pstmt.setString(2, courseID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractParticipationPoints(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update ParticipationPoints
    public boolean updateParticipationPoints(ParticipationPoints pp) {
        String sql = "UPDATE ParticipationPoints SET TotalPoints = ?, MaxPoints = ? WHERE ParticipationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pp.getTotalPoints());
            pstmt.setInt(2, pp.getMaxPoints());
            pstmt.setInt(3, pp.getParticipationID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete ParticipationPoints
    public boolean deleteParticipationPoints(int ppID) {
        String sql = "DELETE FROM ParticipationPoints WHERE ParticipationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ppID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility method to extract ParticipationPoints from ResultSet
    private ParticipationPoints extractParticipationPoints(ResultSet rs) throws SQLException {
        ParticipationPoints pp = new ParticipationPoints();
        pp.setParticipationID(rs.getInt("ParticipationID"));
        pp.setStudentID(rs.getString("StudentID"));
        pp.setCourseID(rs.getString("CourseID"));
        pp.setTotalPoints(rs.getInt("TotalPoints"));
        pp.setMaxPoints(rs.getInt("MaxPoints"));
        return pp;
    }
}
