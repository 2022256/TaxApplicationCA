/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package taxapplicationca;

import IOUtils.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author 4istik andreic
 */
public class AdminAndUserOptions {
    final IOUtils input = new IOUtils(); // Initializing IOUtils for user input
    private final String FILE_NAME = "config.properties"; // File name for properties storage
    Properties properties; // Properties object to manage configurations

    public AdminAndUserOptions() {
        properties = loadProperties(); // Loading properties from the file
    }
    
    // Method to load properties from the file
    private Properties loadProperties() {
        Properties loadedProperties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(FILE_NAME)) {
            loadedProperties.load(fileInputStream); // Load properties from the file
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace if an error occurs while loading properties
        }
        return loadedProperties; // Return loaded properties
    }
    
    // Method to save properties to the file
    private void saveProperties() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
            properties.store(fileOutputStream, null); // Store properties to the file
        } catch (IOException e) {
            e.printStackTrace(); // Print stack trace if an error occurs while saving properties
        }
    }

    // Inner class for managing credentials using properties
    public class CredentialManager {
        private final Properties properties;

        public CredentialManager() {
            properties = new Properties();
            try (FileInputStream fileInputStream = new FileInputStream(FILE_NAME)) {
                properties.load(fileInputStream); // Load properties for CredentialManager
            } catch (IOException e) {
                e.printStackTrace(); // Print stack trace if an error occurs while loading properties
            }
        }

        // Method to retrieve admin username from properties
        public String getAdminUsername() {
            return properties.getProperty("adminUsername");
        }

        // Method to retrieve admin password from properties
        public String getAdminPassword() {
            return properties.getProperty("adminPassword");
        }

        // Method to retrieve user username from properties
        public String getUserUsername() {
            return properties.getProperty("userUsername");
        }

        // Method to retrieve user password from properties
        public String getUserPassword() {
            return properties.getProperty("userPassword");
        }
    }
    
   // A protected inner class for managing admin credentials
   protected class AdminCredentialsManager {
    // Constructor for AdminCredentialsManager
    public AdminCredentialsManager() {
        // Initialize properties object to store admin credentials
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(FILE_NAME)) {
            // Load properties from the file
            properties.load(fileInputStream);
        } catch (IOException e) {
            // Print stack trace if there is an IOException while loading properties
            e.printStackTrace();
        }
    }

    // Method to change admin credentials
    protected void changeAdminCredentials(String newUsername, String newPassword) {
        // Set new admin username and password in the properties
        properties.setProperty("adminUsername", newUsername);
        properties.setProperty("adminPassword", newPassword);

        // Save the updated properties to the file
        saveProperties();

        // Print a message indicating successful admin credentials change
        System.out.println("Admin credentials changed successfully.");
    }
}

// Another protected inner class for managing user credentials
protected class UserCredentialsManager {
    // Constructor for UserCredentialsManager
    public UserCredentialsManager() {
        // Initialize properties object to store user credentials
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(FILE_NAME)) {
            // Load properties from the file
            properties.load(fileInputStream);
        } catch (IOException e) {
            // Print stack trace if there is an IOException while loading properties
            e.printStackTrace();
        }
    }

    // Method to change user credentials
    protected void changeUserCredentials(String currentUsername, String currentPassword,
                                         String newUsername, String newPassword) {
        // Get the total count of users stored in the properties
        int userCount = Integer.parseInt(properties.getProperty("userCount", "0"));
        boolean userFound = false;

        // Iterate through the stored users to find the one to update
        for (int i = 1; i <= userCount; i++) {
            String storedUsername = properties.getProperty("userUsername" + i);

            // Check if the current username matches the stored username
            if (currentUsername.equals(storedUsername)) {
                String key = "userPassword" + i;
                String storedPassword = properties.getProperty(key);

                // Check if the current password matches the stored password
                if (currentPassword.equals(storedPassword)) {
                    // Update the username and password for the found user
                    properties.setProperty("userUsername" + i, newUsername);
                    properties.setProperty(key, newPassword);

                    // Save the updated properties to the file
                    saveProperties();

                    // Print a message indicating successful user credentials change
                    System.out.println("User credentials changed successfully.");
                    userFound = true;
                    break;
                } else {
                    // Print a message indicating an invalid password
                    System.out.println("Invalid password.");
                    userFound = true;
                    break;
                }
            }
        }

        // If the user is not found, print a message indicating so
        if (!userFound) {
            System.out.println("User not found.");
        }
    }
}

}

