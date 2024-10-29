// package dao;

// import models.User;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import utils.PasswordUtils;

// import java.time.LocalDate;

// import static org.junit.jupiter.api.Assertions.*;

// public class UserDAOTest {

//     private UserDAO userDAO;

//     @BeforeEach
//     public void setUp() {
//         userDAO = new UserDAO();
//         // Optionally, set up a test database or use a mocking framework like Mockito
//     }

//     @AfterEach
//     public void tearDown() {
//         // Clean up test data if necessary
//         // For example, delete the test user from the database
//     }

//     @Test
//     public void testAddUser_Success() {
//         User user = new User();
//         user.setUserID("testUser001");
//         user.setFirstName("Test");
//         user.setLastName("User");
//         user.setEmail("test.user@example.com");
//         user.setPassword(PasswordUtils.hashPassword("TestPass123")); // Hash the password
//         user.setRole("Student");
//         user.setAccountCreationDate(LocalDate.now());

//         boolean result = userDAO.addUser(user);
//         assertTrue(result, "User should be added successfully.");

//         // Retrieve the user and verify details
//         User retrievedUser = userDAO.getUserByEmail("test.user@example.com");
//         assertNotNull(retrievedUser, "Retrieved user should not be null.");
//         assertEquals("testUser001", retrievedUser.getUserID(), "UserID should match.");
//         assertEquals("Test", retrievedUser.getFirstName(), "First name should match.");
//         assertEquals("User", retrievedUser.getLastName(), "Last name should match.");
//         assertEquals("Student", retrievedUser.getRole(), "Role should match.");
//         assertEquals(LocalDate.now(), retrievedUser.getAccountCreationDate(), "Account creation date should match.");
//         assertTrue(PasswordUtils.checkPassword("TestPass123", retrievedUser.getPassword()), "Password should match.");
//     }

//     @Test
//     public void testAddUser_DuplicateEmail() {
//         // Add first user
//         User user1 = new User();
//         user1.setUserID("testUser002");
//         user1.setFirstName("First");
//         user1.setLastName("User");
//         user1.setEmail("duplicate.user@example.com");
//         user1.setPassword(PasswordUtils.hashPassword("Pass1234"));
//         user1.setRole("Faculty");
//         user1.setAccountCreationDate(LocalDate.now());

//         boolean firstAdd = userDAO.addUser(user1);
//         assertTrue(firstAdd, "First user should be added successfully.");

//         // Attempt to add second user with the same email
//         User user2 = new User();
//         user2.setUserID("testUser003");
//         user2.setFirstName("Second");
//         user2.setLastName("User");
//         user2.setEmail("duplicate.user@example.com"); // Duplicate email
//         user2.setPassword(PasswordUtils.hashPassword("Pass5678"));
//         user2.setRole("Admin");
//         user2.setAccountCreationDate(LocalDate.now());

//         boolean secondAdd = userDAO.addUser(user2);
//         assertFalse(secondAdd, "Second user with duplicate email should fail to add.");
//     }

//     // Add additional tests as needed...
// }
