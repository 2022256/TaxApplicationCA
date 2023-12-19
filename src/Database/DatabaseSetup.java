/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;


import java.sql.*;

/**
 *
 * @author ivanp
 */

    public class DatabaseSetup extends Database {    
public void createTable() {    
    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); // Establish a connection to the database
         Statement stmt = conn.createStatement()) { // Create a statement for executing SQL queries
        
    
        // SQL script to create the user_income table        
        String sql = "CREATE TABLE IF NOT EXISTS user_income (" // Define the SQL query to create the table if it doesn't exist
                + "user_id INT AUTO_INCREMENT PRIMARY KEY," // Define columns: user_id as primary key                
                + "username VARCHAR(50)," // username column with VARCHAR datatype and length 50
                + "gross_income DECIMAL(10, 2)," // gross_income column with DECIMAL datatype and precision 10, scale 2                
                + "tax_credits DECIMAL(10, 2)," // tax_credits column with DECIMAL datatype and precision 10, scale 2
                + "total_tax_owed DECIMAL(10, 2)," // total_tax_owed column with DECIMAL datatype and precision 10, scale 2                
                + "paye DECIMAL(10, 2)," // paye column with DECIMAL datatype and precision 10, scale 2
                + "usc DECIMAL(10, 2)," // usc column with DECIMAL datatype and precision 10, scale 2                
                + "prsi DECIMAL(10, 2)" // prsi column with DECIMAL datatype and precision 10, scale 2
                + ")";
        // Execute the SQL statement to create the table        
        stmt.executeUpdate(sql); // Execute the SQL query to create the 'user_income' table
        
        System.out.println("Table 'user_income' created."); // Print a message indicating successful table creation
        
    } catch (SQLException e) { // Catch any SQL exceptions that may occur
        e.printStackTrace(); // Print the stack trace if an exception occurs       
}
}
    }

