package service;

import dao.UserDAO;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PasswordUtils;

public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private UserDAO userDAO = new UserDAO();

    /**
     * Registers a new user after hashing the password.
     *
     * @param user The user object containing user details.
     * @return true if registration is successful; false otherwise.
     */
    public boolean registerUser(User user) {
        try {
            // Hash the password before storing
            String hashedPassword = PasswordUtils.hashPassword(user.getPassword());
            user.setPassword(hashedPassword);
            boolean result = userDAO.addUser(user);
            if (result) {
                logger.info("User registered successfully: {}", user.getUserID());
                return true;
            } else {
                logger.warn("User registration failed for: {}", user.getUserID());
                return false;
            }
        } catch (Exception e) {
            logger.error("Exception during user registration for {}: {}", user.getUserID(), e.getMessage());
            return false;
        }
    }

    /**
     * Authenticates a user based on email and password.
     *
     * @param email    The user's email.
     * @param password The user's plaintext password.
     * @return The authenticated User object if successful; null otherwise.
     */
    public User authenticateUser(String email, String password) {
        logger.info("Attempting to authenticate user with email: {}", email);
        try {
            User user = userDAO.getUserByEmail(email);
            if (user != null && PasswordUtils.checkPassword(password, user.getPassword())) {
                logger.info("Authentication successful for user: {}", user.getUserID());
                return user;
            } else {
                logger.warn("Authentication failed for email: {}", email);
                return null;
            }
        } catch (Exception e) {
            logger.error("Error during authentication for email {}: {}", email, e.getMessage());
            return null;
        }
    }

    // You can add more service methods as needed
}
