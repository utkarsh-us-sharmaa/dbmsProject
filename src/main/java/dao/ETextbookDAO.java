package dao;

import models.ETextbook;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ETextbookDAO {

    // Add a new e-textbook
    public boolean addETextbook(ETextbook textbook) {
        String sql = "INSERT INTO ETextbook (Title, TextContent, ImageURL) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, textbook.getTitle());
            pstmt.setString(2, textbook.getTextContent());
            pstmt.setString(3, textbook.getImageURL());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated TextbookID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    textbook.setTextbookID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve e-textbook by ID
    public ETextbook getETextbookByID(int textbookID) {
        String sql = "SELECT * FROM ETextbook WHERE TextbookID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, textbookID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractETextbook(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all e-textbooks
    public List<ETextbook> getAllETextbooks() {
        String sql = "SELECT * FROM ETextbook";
        List<ETextbook> textbooks = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                textbooks.add(extractETextbook(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return textbooks;
    }

    // Update e-textbook
    public boolean updateETextbook(ETextbook textbook) {
        String sql = "UPDATE ETextbook SET Title = ?, TextContent = ?, ImageURL = ? WHERE TextbookID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, textbook.getTitle());
            pstmt.setString(2, textbook.getTextContent());
            pstmt.setString(3, textbook.getImageURL());
            pstmt.setInt(4, textbook.getTextbookID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete e-textbook
    public boolean deleteETextbook(int textbookID) {
        String sql = "DELETE FROM ETextbook WHERE TextbookID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, textbookID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Utility method to extract ETextbook from ResultSet
    private ETextbook extractETextbook(ResultSet rs) throws SQLException {
        ETextbook textbook = new ETextbook();
        textbook.setTextbookID(rs.getInt("TextbookID"));
        textbook.setTitle(rs.getString("Title"));
        textbook.setTextContent(rs.getString("TextContent"));
        textbook.setImageURL(rs.getString("ImageURL"));
        return textbook;
    }
}
