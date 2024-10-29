package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
    private static final String PROPERTIES_FILE = "application.properties";
    private static String url;
    private static String user;
    private static String password;

    static {
        try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            Properties prop = new Properties();
            if (input == null) {
                throw new RuntimeException("Sorry, unable to find " + PROPERTIES_FILE);
            }

            // Load properties from the file
            prop.load(input);
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");

        } catch (IOException ex) {
            throw new RuntimeException("Error loading " + PROPERTIES_FILE, ex);
        }
    }

    /**
     * Establishes and returns a database connection.
     *
     * @return The established Connection object.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
