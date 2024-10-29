package dao;

import models.Section;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectionDAO {

    // Add a new section
    public boolean addSection(Section section) {
        String sql = "INSERT INTO Section (SectionNumber, Title, ChapterID) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, section.getSectionNumber());
            pstmt.setString(2, section.getTitle());
            pstmt.setInt(3, section.getChapterID());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated SectionID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    section.setSectionID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve section by ID
    public Section getSectionByID(int sectionID) {
        String sql = "SELECT * FROM Section WHERE SectionID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sectionID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractSection(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all sections for a chapter
    public List<Section> getSectionsByChapterID(int chapterID) {
        String sql = "SELECT * FROM Section WHERE ChapterID = ?";
        List<Section> sections = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, chapterID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                sections.add(extractSection(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sections;
    }

    // Update section
    public boolean updateSection(Section section) {
        String sql = "UPDATE Section SET SectionNumber = ?, Title = ?, ChapterID = ? WHERE SectionID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, section.getSectionNumber());
            pstmt.setString(2, section.getTitle());
            pstmt.setInt(3, section.getChapterID());
            pstmt.setInt(4, section.getSectionID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete section
    public boolean deleteSection(int sectionID) {
        String sql = "DELETE FROM Section WHERE SectionID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, sectionID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Utility method to extract Section from ResultSet
    private Section extractSection(ResultSet rs) throws SQLException {
        Section section = new Section();
        section.setSectionID(rs.getInt("SectionID"));
        section.setSectionNumber(rs.getString("SectionNumber"));
        section.setTitle(rs.getString("Title"));
        section.setChapterID(rs.getInt("ChapterID"));
        return section;
    }
}
