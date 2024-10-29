package dao;

import models.CourseCustomization;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseCustomizationDAO {

    // Add a new CourseCustomization
    public boolean addCourseCustomization(CourseCustomization cc) {
        String sql = "INSERT INTO CourseCustomization (CourseID, ContentBlockID, ActivityID, IsHidden, AddedByRole, IsOriginalContent, DisplayOrder, CreatedByUserID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cc.getCourseID());
            if (cc.getContentBlockID() != null) {
                pstmt.setInt(2, cc.getContentBlockID());
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            if (cc.getActivityID() != null) {
                pstmt.setInt(3, cc.getActivityID());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setBoolean(4, cc.isHidden());
            pstmt.setString(5, cc.getAddedByRole());
            pstmt.setBoolean(6, cc.isOriginalContent());
            pstmt.setInt(7, cc.getDisplayOrder());
            pstmt.setString(8, cc.getCreatedByUserID());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated CustomizationID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    cc.setCustomizationID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve CourseCustomization by ID
    public CourseCustomization getCourseCustomizationByID(int customizationID) {
        String sql = "SELECT * FROM CourseCustomization WHERE CustomizationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customizationID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractCourseCustomization(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve CourseCustomizations by CourseID
    public List<CourseCustomization> getCourseCustomizationsByCourseID(String courseID) {
        String sql = "SELECT * FROM CourseCustomization WHERE CourseID = ? ORDER BY DisplayOrder";
        List<CourseCustomization> customizations = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, courseID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                customizations.add(extractCourseCustomization(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customizations;
    }

    // Update CourseCustomization
    public boolean updateCourseCustomization(CourseCustomization cc) {
        String sql = "UPDATE CourseCustomization SET CourseID = ?, ContentBlockID = ?, ActivityID = ?, IsHidden = ?, AddedByRole = ?, IsOriginalContent = ?, DisplayOrder = ?, CreatedByUserID = ? WHERE CustomizationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cc.getCourseID());
            if (cc.getContentBlockID() != null) {
                pstmt.setInt(2, cc.getContentBlockID());
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            if (cc.getActivityID() != null) {
                pstmt.setInt(3, cc.getActivityID());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setBoolean(4, cc.isHidden());
            pstmt.setString(5, cc.getAddedByRole());
            pstmt.setBoolean(6, cc.isOriginalContent());
            pstmt.setInt(7, cc.getDisplayOrder());
            pstmt.setString(8, cc.getCreatedByUserID());
            pstmt.setInt(9, cc.getCustomizationID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete CourseCustomization
    public boolean deleteCourseCustomization(int customizationID) {
        String sql = "DELETE FROM CourseCustomization WHERE CustomizationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customizationID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility method to extract CourseCustomization from ResultSet
    private CourseCustomization extractCourseCustomization(ResultSet rs) throws SQLException {
        CourseCustomization cc = new CourseCustomization();
        cc.setCustomizationID(rs.getInt("CustomizationID"));
        cc.setCourseID(rs.getString("CourseID"));
        int cbID = rs.getInt("ContentBlockID");
        if (!rs.wasNull()) {
            cc.setContentBlockID(cbID);
        }
        int activityID = rs.getInt("ActivityID");
        if (!rs.wasNull()) {
            cc.setActivityID(activityID);
        }
        cc.setHidden(rs.getBoolean("IsHidden"));
        cc.setAddedByRole(rs.getString("AddedByRole"));
        cc.setOriginalContent(rs.getBoolean("IsOriginalContent"));
        cc.setDisplayOrder(rs.getInt("DisplayOrder"));
        cc.setCreatedByUserID(rs.getString("CreatedByUserID"));
        return cc;
    }
}
