package dao;

import models.ContentBlock;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContentBlockDAO {

    // Add a new content block
    public boolean addContentBlock(ContentBlock cb) {
        String sql = "INSERT INTO ContentBlock (ContentType, Content, SequenceNumber, SectionID) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, cb.getContentType());
            pstmt.setString(2, cb.getContent());
            pstmt.setInt(3, cb.getSequenceNumber());
            pstmt.setInt(4, cb.getSectionID());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated ContentBlockID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    cb.setContentBlockID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve content block by ID
    public ContentBlock getContentBlockByID(int cbID) {
        String sql = "SELECT * FROM ContentBlock WHERE ContentBlockID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cbID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractContentBlock(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all content blocks for a section
    public List<ContentBlock> getContentBlocksBySectionID(int sectionID) {
        String sql = "SELECT * FROM ContentBlock WHERE SectionID = ? ORDER BY SequenceNumber";
        List<ContentBlock> contentBlocks = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sectionID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                contentBlocks.add(extractContentBlock(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contentBlocks;
    }

    // Update content block
    public boolean updateContentBlock(ContentBlock cb) {
        String sql = "UPDATE ContentBlock SET ContentType = ?, Content = ?, SequenceNumber = ?, SectionID = ? WHERE ContentBlockID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cb.getContentType());
            pstmt.setString(2, cb.getContent());
            pstmt.setInt(3, cb.getSequenceNumber());
            pstmt.setInt(4, cb.getSectionID());
            pstmt.setInt(5, cb.getContentBlockID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete content block
    public boolean deleteContentBlock(int cbID) {
        String sql = "DELETE FROM ContentBlock WHERE ContentBlockID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cbID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Utility method to extract ContentBlock from ResultSet
    private ContentBlock extractContentBlock(ResultSet rs) throws SQLException {
        ContentBlock cb = new ContentBlock();
        cb.setContentBlockID(rs.getInt("ContentBlockID"));
        cb.setContentType(rs.getString("ContentType"));
        cb.setContent(rs.getString("Content"));
        cb.setSequenceNumber(rs.getInt("SequenceNumber"));
        cb.setSectionID(rs.getInt("SectionID"));
        return cb;
    }
}
