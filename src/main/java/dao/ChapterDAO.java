package dao;

import models.Chapter;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChapterDAO {

    // Add a new chapter
    public boolean addChapter(Chapter chapter) {
        String sql = "INSERT INTO Chapter (ChapterNumber, Title, TextbookID) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, chapter.getChapterNumber());
            pstmt.setString(2, chapter.getTitle());
            pstmt.setInt(3, chapter.getTextbookID());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated ChapterID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    chapter.setChapterID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve chapter by ID
    public Chapter getChapterByID(int chapterID) {
        String sql = "SELECT * FROM Chapter WHERE ChapterID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, chapterID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractChapter(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all chapters for a textbook
    public List<Chapter> getChaptersByTextbookID(int textbookID) {
        String sql = "SELECT * FROM Chapter WHERE TextbookID = ?";
        List<Chapter> chapters = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, textbookID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                chapters.add(extractChapter(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return chapters;
    }

    // Update chapter
    public boolean updateChapter(Chapter chapter) {
        String sql = "UPDATE Chapter SET ChapterNumber = ?, Title = ?, TextbookID = ? WHERE ChapterID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, chapter.getChapterNumber());
            pstmt.setString(2, chapter.getTitle());
            pstmt.setInt(3, chapter.getTextbookID());
            pstmt.setInt(4, chapter.getChapterID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete chapter
    public boolean deleteChapter(int chapterID) {
        String sql = "DELETE FROM Chapter WHERE ChapterID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, chapterID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Utility method to extract Chapter from ResultSet
    private Chapter extractChapter(ResultSet rs) throws SQLException {
        Chapter chapter = new Chapter();
        chapter.setChapterID(rs.getInt("ChapterID"));
        chapter.setChapterNumber(rs.getString("ChapterNumber"));
        chapter.setTitle(rs.getString("Title"));
        chapter.setTextbookID(rs.getInt("TextbookID"));
        return chapter;
    }
}
