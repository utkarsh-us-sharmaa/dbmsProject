package models;

import java.util.Date;

public class Notification {
    private int notificationID;
    private String userID;
    private String message;
    private Date notificationDate;
    private boolean isRead;

    // Constructors
    public Notification() {}

    public Notification(int notificationID, String userID, String message, Date notificationDate, boolean isRead) {
        this.notificationID = notificationID;
        this.userID = userID;
        this.message = message;
        this.notificationDate = notificationDate;
        this.isRead = isRead;
    }

    // Getters and Setters
    public int getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(int notificationID) {
        this.notificationID = notificationID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }
}
