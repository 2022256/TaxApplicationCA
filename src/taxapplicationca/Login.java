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

}
