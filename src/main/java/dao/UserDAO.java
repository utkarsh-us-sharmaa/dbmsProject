package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseConnection;

public class UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);

    /**
     * Adds a new user to the database.
     *
     * @param user The user object containing user details.
     * @return true if the user was added successfully; false otherwise.
     */
    public boolean addUser(User user) {
        String sql = "INSERT INTO Users (UserID, FirstName, LastName, Email, Password, Role, AccountCreationDate) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getUserID());
            pstmt.setString(2, user.getFirstName());
            pstmt.setString(3, user.getLastName());
            pstmt.setString(4, user.getEmail());
            pstmt.setString(5, user.getPassword());
            pstmt.setString(6, user.getRole());

            // Convert java.time.LocalDate to java.sql.Date
            LocalDate localDate = user.getAccountCreationDate();
            if (localDate != null) {
                Date sqlDate = Date.valueOf(localDate);
                pstmt.setDate(7, sqlDate);
            } else {
                pstmt.setDate(7, null); // Handle null dates if necessary
            }

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("User added successfully: {}", user.getUserID());
                return true;
            } else {
                logger.warn("Failed to add user: {}", user.getUserID());
                return false;
            }

        } catch (SQLException e) {
            logger.error("SQL Exception when adding user {}: {}", user.getUserID(), e.getMessage());
            return false;
        }
    }

    /**
     * Retrieves a user by their email.
     *
     * @param email The email of the user.
     * @return The User object if found; null otherwise.
     */
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUser(rs);
            }

        } catch (SQLException e) {
            logger.error("SQL Exception when retrieving user by email {}: {}", email, e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves a user by their UserID.
     *
     * @param userID The UserID of the user.
     * @return The User object if found; null otherwise.
     */
    public User getUserByID(String userID) {
        String sql = "SELECT * FROM Users WHERE UserID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return extractUser(rs);
            }
        } catch (SQLException e) {
            logger.error("SQL Exception when retrieving user by ID {}: {}", userID, e.getMessage());
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all users.
     */
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                users.add(extractUser(rs));
            }
        } catch (SQLException e) {
            logger.error("SQL Exception when retrieving all users: {}", e.getMessage());
        }
        return users;
    }

    /**
     * Updates user information.
     *
     * @param user The user object containing updated details.
     * @return true if the update was successful; false otherwise.
     */
    public boolean updateUser(User user) {
        String sql = "UPDATE Users SET FirstName = ?, LastName = ?, Email = ?, Password = ?, Role = ?, AccountCreationDate = ? WHERE UserID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPassword()); // Hashed password
            pstmt.setString(5, user.getRole());

            // Convert java.time.LocalDate to java.sql.Date
            LocalDate localDate = user.getAccountCreationDate();
            if (localDate != null) {
                Date sqlDate = Date.valueOf(localDate);
                pstmt.setDate(6, sqlDate);
            } else {
                pstmt.setDate(6, null);
            }

            pstmt.setString(7, user.getUserID());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("User updated successfully: {}", user.getUserID());
                return true;
            } else {
                logger.warn("Failed to update user: {}", user.getUserID());
                return false;
            }

        } catch (SQLException e) {
            logger.error("SQL Exception when updating user {}: {}", user.getUserID(), e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a user from the database.
     *
     * @param userID The UserID of the user to delete.
     * @return true if the deletion was successful; false otherwise.
     */
    public boolean deleteUser(String userID) {
        String sql = "DELETE FROM Users WHERE UserID = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, userID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("User deleted successfully: {}", userID);
                return true;
            } else {
                logger.warn("Failed to delete user: {}", userID);
                return false;
            }

        } catch (SQLException e) {
            logger.error("SQL Exception when deleting user {}: {}", userID, e.getMessage());
            return false;
        }
    }

    /**
     * Utility method to extract User from ResultSet.
     *
     * @param rs The ResultSet from which to extract user data.
     * @return The User object populated with data from the ResultSet.
     * @throws SQLException If an SQL error occurs.
     */
    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserID(rs.getString("UserID"));
        user.setFirstName(rs.getString("FirstName"));
        user.setLastName(rs.getString("LastName"));
        user.setEmail(rs.getString("Email"));
        user.setPassword(rs.getString("Password"));
        user.setRole(rs.getString("Role"));

        // Convert java.sql.Date to java.time.LocalDate
        Date sqlDate = rs.getDate("AccountCreationDate");
        if (sqlDate != null) {
            LocalDate localDate = sqlDate.toLocalDate();
            user.setAccountCreationDate(localDate);
        } else {
            user.setAccountCreationDate(null);
        }

        return user;
    }
}
