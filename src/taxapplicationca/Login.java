/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxapplicationca;

import Calculation.*;
import Database.*;
import User.*;

/**
 *
 * @author 4istik
 */
public class Login extends AdminAndUserOptions {
    UserCredentialsManager user = new UserCredentialsManager();
    UserAction action = new UserAction();
    Calculation cal = new Calculation();
    DatabaseInteraction inter = new DatabaseInteraction();
    
    // Method to handle login for regular users
    private void regularUserLogin() throws ClassNotFoundException {
    System.out.println("---User Login---");

    // Get username and password from user input
    String username = input.getUserText("Enter username: ");
    String password = input.getUserPassword("Enter password: ");

    int userCount = Integer.parseInt(properties.getProperty("userCount", "0"));
    boolean userLoggedIn = false;

    // Loop through user credentials to find a match
    for (int i = 1; i <= userCount; i++) {
        String storedUsername = properties.getProperty("userUsername" + i);
        String storedPassword = properties.getProperty("userPassword" + i);

        // Check if entered credentials match stored credentials
        if (username.equals(storedUsername) && password.equals(storedPassword)) {
            // Log user activity
            action.addOperation(username + " logged in. " + action.getTimestamp());
            userLoggedIn = true;

            // Display user options in a loop until the user chooses to exit
            int userChoice = 0;
            String userPrompt = "User logged in successfully.\n" +
                                "1. Change Username and Password.\n" +
                                "2. View data and save it.\n" +
                                "3. Exit.\n" +
                                "4. Calculate person's tax";

            while (userChoice != 3) {
                userChoice = input.getUserInt(userPrompt);

                // Perform actions based on user choice
                switch (userChoice) {
                    case 1:
                        // Change username and password
                        String newUsername = input.getUserText("Enter new username:");
                        String newPassword = input.getUserPassword("Enter new password:");
                        action.addOperation(username + " changed credentials to: " + newUsername + " | " + action.getTimestamp());
                        action.saveOperationsToFile("user_actions.txt");
                        user.changeUserCredentials(username, password, newUsername, newPassword);
                        break;
                    case 2:
                        // View and save data
                        inter.viewAndSave();
                        action.addOperation(username + " saved sql info. " + action.getTimestamp());
                        break;
                    case 3:
                        // Logout
                        action.addOperation(username + " logged out. " + action.getTimestamp());
                        action.saveOperationsToFile("user_actions.txt");
                        break;
                    case 4:
                        // Calculate tax
                        action.addOperation(username + " added data to the table. " + action.getTimestamp());
                        cal.CreateAndCalculate();
                        break;
                }
            }
            break;
        }
    }

    // If user credentials don't match any stored credentials
    if (!userLoggedIn) {
        System.out.println("Invalid username or password.");
    }
}
    
    // Method to handle login for administrators
    private void adminLogin() throws ClassNotFoundException {
    System.out.println("---Admin Login---");

    // Get username and password from user input
    String username = input.getUserText("Enter username: ");
    String password = input.getUserPassword("Enter password: ");

    // Retrieve stored admin credentials
    String storedUsername = properties.getProperty("adminUsername");
    String storedPassword = properties.getProperty("adminPassword");

    // Check if entered credentials match stored admin credentials
    if (username.equals(storedUsername) && password.equals(storedPassword)) {
        // Display admin options in a loop until the admin chooses to exit
        String adminPrompt = "Admin logged in successfully.\n" +
                "1. Change Username and Password.\n" +
                "2. Access a list of Users in the system.\n" +
                "3. Remove a user from the system.\n" +
                "4. View operations performed by a user.\n" +
                "5. Insert data into SQL table.\n" +
                "6. View data and save it.\n" +
                "7. Alter data in the table.\n" +
                "8. Exit.\n" +
                "9. Add user.";

        int adminChoice = 0;
        while (adminChoice != 8) {
            adminChoice = input.getUserInt(adminPrompt);

            // Perform actions based on admin choice
            switch (adminChoice) {
                case 1:
                    // Change admin username and password
                    String newAdminUsername = input.getUserText("Enter new admin username:");
                    String newAdminPassword = input.getUserPassword("Enter new admin password:");
                    AdminCredentialsManager adminManager = new AdminCredentialsManager();
                    adminManager.changeAdminCredentials(newAdminUsername, newAdminPassword);
                    break;
                case 2:
                    // Access a list of users in the system
                    listUsers();
                    break;
                case 3:
                    // Remove a user from the system
                    removeUser(input.getUserText("User to remove:"));
                    break;
                case 4:
                    // View operations performed by a user
                    action.viewOperationsFromFile("user_actions.txt");
                    break;
                case 5:
                    // Insert data into SQL table
                    cal.CreateAndCalculate();
                    break;
                case 6:
                    // View data and save it
                    inter.viewAndSave();
                    break;
                case 7:
                    // Alter data in the table
                    inter.alterDataInvoke();
                    break;
                case 8:
                    // Exit
                    break;
                case 9:
                    // Add a new user
                    addUser();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    } else {
        System.out.println("Invalid username or password.");
    }
}


}
