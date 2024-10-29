package dao;

import models.Activity;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ActivityDAO {

    // Add a new activity
    public boolean addActivity(Activity activity) {
        String sql = "INSERT INTO Activity (Question, CorrectAnswer, IncorrectAnswer1, IncorrectAnswer2, IncorrectAnswer3, ExplanationCorrect, ExplanationIncorrect1, ExplanationIncorrect2, ExplanationIncorrect3, ContentBlockID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, activity.getQuestion());
            pstmt.setString(2, activity.getCorrectAnswer());
            pstmt.setString(3, activity.getIncorrectAnswer1());
            pstmt.setString(4, activity.getIncorrectAnswer2());
            pstmt.setString(5, activity.getIncorrectAnswer3());
            pstmt.setString(6, activity.getExplanationCorrect());
            pstmt.setString(7, activity.getExplanationIncorrect1());
            pstmt.setString(8, activity.getExplanationIncorrect2());
            pstmt.setString(9, activity.getExplanationIncorrect3());
            pstmt.setInt(10, activity.getContentBlockID());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the generated ActivityID
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    activity.setActivityID(rs.getInt(1));
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Retrieve activity by ID
    public Activity getActivityByID(int activityID) {
        String sql = "SELECT * FROM Activity WHERE ActivityID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, activityID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractActivity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all activities for a content block
    public List<Activity> getActivitiesByContentBlockID(int cbID) {
        String sql = "SELECT * FROM Activity WHERE ContentBlockID = ?";
        List<Activity> activities = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cbID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                activities.add(extractActivity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return activities;
    }

    // Update activity
    public boolean updateActivity(Activity activity) {
        String sql = "UPDATE Activity SET Question = ?, CorrectAnswer = ?, IncorrectAnswer1 = ?, IncorrectAnswer2 = ?, IncorrectAnswer3 = ?, ExplanationCorrect = ?, ExplanationIncorrect1 = ?, ExplanationIncorrect2 = ?, ExplanationIncorrect3 = ?, ContentBlockID = ? WHERE ActivityID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, activity.getQuestion());
            pstmt.setString(2, activity.getCorrectAnswer());
            pstmt.setString(3, activity.getIncorrectAnswer1());
            pstmt.setString(4, activity.getIncorrectAnswer2());
            pstmt.setString(5, activity.getIncorrectAnswer3());
            pstmt.setString(6, activity.getExplanationCorrect());
            pstmt.setString(7, activity.getExplanationIncorrect1());
            pstmt.setString(8, activity.getExplanationIncorrect2());
            pstmt.setString(9, activity.getExplanationIncorrect3());
            pstmt.setInt(10, activity.getContentBlockID());
            pstmt.setInt(11, activity.getActivityID());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete activity
    public boolean deleteActivity(int activityID) {
        String sql = "DELETE FROM Activity WHERE ActivityID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, activityID);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Utility method to extract Activity from ResultSet
    private Activity extractActivity(ResultSet rs) throws SQLException {
        Activity activity = new Activity();
        activity.setActivityID(rs.getInt("ActivityID"));
        activity.setQuestion(rs.getString("Question"));
        activity.setCorrectAnswer(rs.getString("CorrectAnswer"));
        activity.setIncorrectAnswer1(rs.getString("IncorrectAnswer1"));
        activity.setIncorrectAnswer2(rs.getString("IncorrectAnswer2"));
        activity.setIncorrectAnswer3(rs.getString("IncorrectAnswer3"));
        activity.setExplanationCorrect(rs.getString("ExplanationCorrect"));
        activity.setExplanationIncorrect1(rs.getString("ExplanationIncorrect1"));
        activity.setExplanationIncorrect2(rs.getString("ExplanationIncorrect2"));
        activity.setExplanationIncorrect3(rs.getString("ExplanationIncorrect3"));
        activity.setContentBlockID(rs.getInt("ContentBlockID"));
        return activity;
    }
}
