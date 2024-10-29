package services;

import dao.NotificationDAO;
import models.Notification;

import java.util.Date;
import java.util.List;

public class NotificationService {
    private NotificationDAO notificationDAO = new NotificationDAO();

    // Send a notification to a user
    public boolean sendNotification(Notification notification) {
        notification.setNotificationDate(new Date());
        notification.setRead(false);
        return notificationDAO.addNotification(notification);
    }

    // Retrieve notifications for a user
    public List<Notification> getNotificationsByUserID(String userID) {
        return notificationDAO.getNotificationsByUserID(userID);
    }

    // Mark a notification as read
    public boolean markAsRead(int notificationID) {
        return notificationDAO.markAsRead(notificationID);
    }

    // Delete a notification
    public boolean deleteNotification(int notificationID) {
        return notificationDAO.deleteNotification(notificationID);
    }

    // Additional notification-related business logic can be added here
}
