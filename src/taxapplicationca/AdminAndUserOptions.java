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
}

