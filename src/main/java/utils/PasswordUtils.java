package utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    /**
     * Hashes a plaintext password using BCrypt.
     *
     * @param plainPassword The plaintext password.
     * @return The hashed password.
     */
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * Checks if a plaintext password matches the hashed password.
     *
     * @param plainPassword  The plaintext password.
     * @param hashedPassword The hashed password.
     * @return true if the passwords match; false otherwise.
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$")) {
            throw new IllegalArgumentException("Invalid hashed password");
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
