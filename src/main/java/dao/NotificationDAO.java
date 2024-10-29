package dao;

import models.Notification;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDAO {

    // Add a new notification
    public boolean addNotification(Notification notification) {
        String sql = "INSERT INTO Notification (UserID, Message, NotificationDate, IsRead) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, notification.getUserID());
            pstmt.setString(2, notification.getMessage());
            pstmt.setTimestamp(3, new java.sql.Timestamp(notification.getNotificationDate().getTime()));
            pstmt.setBoolean(4, notification.isRead());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated NotificationID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    notification.setNotificationID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve notification by ID
    public Notification getNotificationByID(int notificationID) {
        String sql = "SELECT * FROM Notification WHERE NotificationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, notificationID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractNotification(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve notifications by UserID
    public List<Notification> getNotificationsByUserID(String userID) {
        String sql = "SELECT * FROM Notification WHERE UserID = ? ORDER BY NotificationDate DESC";
        List<Notification> notifications = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                notifications.add(extractNotification(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notifications;
    }

    // Mark notification as read
    public boolean markAsRead(int notificationID) {
        String sql = "UPDATE Notification SET IsRead = TRUE WHERE NotificationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, notificationID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete notification
    public boolean deleteNotification(int notificationID) {
        String sql = "DELETE FROM Notification WHERE NotificationID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, notificationID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Utility method to extract Notification from ResultSet
    private Notification extractNotification(ResultSet rs) throws SQLException {
        Notification notification = new Notification();
        notification.setNotificationID(rs.getInt("NotificationID"));
        notification.setUserID(rs.getString("UserID"));
        notification.setMessage(rs.getString("Message"));
        notification.setNotificationDate(rs.getTimestamp("NotificationDate"));
        notification.setRead(rs.getBoolean("IsRead"));
        return notification;
    }
}
