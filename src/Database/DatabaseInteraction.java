/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import IOUtils.*;
import java.io.*;
import java.sql.*;
import java.util.*;


/**
 *
 * @author ivanp
 */
public class DatabaseInteraction extends Database {
    IOUtils input = new IOUtils();
    public void alterData(Database db, String oldUsername, String newUsername) {
    try (Connection conn = db.getConnection();
         PreparedStatement stmt = conn.prepareStatement("UPDATE user_income SET username = ? WHERE username = ?")) {
        stmt.setString(1, newUsername);
        stmt.setString(2, oldUsername);
        
        int rowsUpdated = stmt.executeUpdate();

        if (rowsUpdated > 0) {
            System.out.println("Username updated successfully.");
        } else {
            System.out.println("No records found for the username: " + oldUsername);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
public ArrayList<String> fetchUserDataFromDatabase(Database db) throws SQLException {
    ArrayList<String> userData = new ArrayList<>();

    // Fetch data from the database using Database object (db)
    // Perform necessary database queries to retrieve user data

    // For example:
    try (Connection conn = db.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM user_income")) {

        while (rs.next()) {
            String username = rs.getString("username");
            double grossIncome = rs.getDouble("gross_income");
            double taxCredits = rs.getDouble("tax_credits");
            double total_tax_owed = rs.getDouble("total_tax_owed");
            double paye = rs.getDouble("paye");
            double usc = rs.getDouble("usc");
            // Add fetched data to the ArrayList
            userData.add("Username: " + username + ", Gross Income: " + grossIncome + ", Tax Credits: " + taxCredits + ", Total Tax Owed: " + total_tax_owed 
            + " PAYE: " + paye + " USC: " + usc);
        }
    }

    return userData;
}    
    
    

public void saveUserDataToFile(ArrayList<String> userData) {
    // Save the fetched data to a file

    // For example:
    try (PrintWriter writer = new PrintWriter(new FileWriter("sql_data.txt"))) {
        for (String data : userData) {
            writer.println(data);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    
  public void viewAndSave() throws ClassNotFoundException {
    // Create a new Database instance
    Database db = new Database();
    
    try {
        // Attempt to establish a connection to the database
        db.connect();
        
        // Fetch user data from the database
        ArrayList<String> userData = fetchUserDataFromDatabase(db);

        // Save the fetched data to a file
        saveUserDataToFile(userData);
        
        // Print a success message indicating that data has been saved to the file
        System.out.println("Data saved to file successfully.");
        
    } catch (SQLException e) {
        // Print the stack trace if a SQLException occurs during database operations
        e.printStackTrace();
    } finally {
        // Ensure the database connection is closed, whether an exception occurs or not
        db.close();
    }
}
  
}
