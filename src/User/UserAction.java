/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.*;
import java.time.format.*;
import java.util.ArrayList;
/**
 *
 * @author ivanp
 */


// Represents an operation performed by a user
class UserOperations {
    private String operation;

    // Constructor to initialize the UserOperations with a specified operation
    public UserOperations(String operation) {
        this.operation = operation;
    }

    // Getter method to retrieve the operation
    public String getOperation() {
        return operation;
    }
}

// Represents a collection of user operations and provides methods to manipulate and store them
public class UserAction {
    private ArrayList<UserOperations> operations;

    // Constructor to initialize UserAction with an empty list of operations
    public UserAction() {
        this.operations = new ArrayList<>();
    }

    // Adds a new operation to the list
    public void addOperation(String operation) {
        operations.add(new UserOperations(operation));
    }

    // Saves user operations to a specified file
    public void saveOperationsToFile(String filename) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter(filename))) {
            // Write header and each user operation to the file
            writer.println("User Operations: \n" + "~~~~~");
            for (UserOperations operation : operations) {
                writer.println(operation.getOperation());
            }
        } catch (java.io.IOException e) {
            // Print stack trace if an IOException occurs during file writing
            e.printStackTrace();
        }
    }

    // Reads and displays user operations from a specified file
    public void viewOperationsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("-----");
            // Print each line from the file
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("-----");
        } catch (IOException e) {
            // Print a message if the file is not found
            System.out.println("File not found");
        }
    }

    // Retrieves the current timestamp in the format "yyyy-MM-dd HH:mm:ss"
    public String getTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
}

