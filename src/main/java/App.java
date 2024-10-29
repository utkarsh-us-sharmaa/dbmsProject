import java.time.LocalDate;
import java.util.Scanner;
import models.User;
import service.UserService;

public class App {
    public static void main(String[] args) {
        UserService userService = new UserService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the E-Learning Platform");
        System.out.println("-----------------------------------");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Select an option (1 or 2): ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                // Registration
                User newUser = new User();
                System.out.print("Enter UserID: ");
                newUser.setUserID(scanner.nextLine());
                System.out.print("Enter First Name: ");
                newUser.setFirstName(scanner.nextLine());
                System.out.print("Enter Last Name: ");
                newUser.setLastName(scanner.nextLine());
                System.out.print("Enter Email: ");
                newUser.setEmail(scanner.nextLine());
                System.out.print("Enter Password: ");
                newUser.setPassword(scanner.nextLine());
                System.out.print("Enter Role (Student/Faculty/Admin): ");
                newUser.setRole(scanner.nextLine());
                newUser.setAccountCreationDate(LocalDate.now());

                boolean registrationSuccess = userService.registerUser(newUser);
                if (registrationSuccess) {
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("Registration failed. Please try again.");
                }
                break;

            case "2":
                // Login
                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                System.out.print("Enter Password: ");
                String password = scanner.nextLine();

                User authenticatedUser = userService.authenticateUser(email, password);
                if (authenticatedUser != null) {
                    System.out.println("Login successful! Welcome, " + authenticatedUser.getFirstName() + "!");
                    // Proceed to user dashboard or functionalities
                } else {
                    System.out.println("Invalid email or password.");
                }
                break;

            default:
                System.out.println("Invalid option selected.");
                break;
        }

        scanner.close();
    }
}
