package dao;

import models.CourseContentVersioning;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseContentVersioningDAO {

    // Add a new CourseContentVersioning
    public boolean addCourseContentVersioning(CourseContentVersioning ccv) {
        String sql = "INSERT INTO CourseContentVersioning (CourseID, ChapterID, SectionID, DisplayOrder) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, ccv.getCourseID());
            pstmt.setInt(2, ccv.getChapterID());
            if (ccv.getSectionID() != null) {
                pstmt.setInt(3, ccv.getSectionID());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setInt(4, ccv.getDisplayOrder());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated VersionID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    ccv.setVersionID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve CourseContentVersioning by ID
    public CourseContentVersioning getCourseContentVersioningByID(int versionID) {
        String sql = "SELECT * FROM CourseContentVersioning WHERE VersionID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, versionID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractCourseContentVersioning(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve CourseContentVersionings by CourseID
    public List<CourseContentVersioning> getCourseContentVersioningsByCourseID(String courseID) {
        String sql = "SELECT * FROM CourseContentVersioning WHERE CourseID = ? ORDER BY DisplayOrder";
        List<CourseContentVersioning> versionings = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                versionings.add(extractCourseContentVersioning(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return versionings;
    }

    // Update CourseContentVersioning
    public boolean updateCourseContentVersioning(CourseContentVersioning ccv) {
        String sql = "UPDATE CourseContentVersioning SET CourseID = ?, ChapterID = ?, SectionID = ?, DisplayOrder = ? WHERE VersionID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, ccv.getCourseID());
            pstmt.setInt(2, ccv.getChapterID());
            if (ccv.getSectionID() != null) {
                pstmt.setInt(3, ccv.getSectionID());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setInt(4, ccv.getDisplayOrder());
            pstmt.setInt(5, ccv.getVersionID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete CourseContentVersioning
    public boolean deleteCourseContentVersioning(int versionID) {
        String sql = "DELETE FROM CourseContentVersioning WHERE VersionID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, versionID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility method to extract CourseContentVersioning from ResultSet
    private CourseContentVersioning extractCourseContentVersioning(ResultSet rs) throws SQLException {
        CourseContentVersioning ccv = new CourseContentVersioning();
        ccv.setVersionID(rs.getInt("VersionID"));
        ccv.setCourseID(rs.getString("CourseID"));
        ccv.setChapterID(rs.getInt("ChapterID"));
        int sectionID = rs.getInt("SectionID");
        if (!rs.wasNull()) {
            ccv.setSectionID(sectionID);
        }
        ccv.setDisplayOrder(rs.getInt("DisplayOrder"));
        return ccv;
    }
}
