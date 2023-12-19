/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.*; //created import for sql
/**
 *
 * @author ivanp and andreic
 */
public class Database {
    static final String DB_URL = "jdbc:mysql://localhost:3306/mysql_db";    
    static final String USER = "ooc2023";
    static final String PASS = "ooc2023";
    
    public static Database instance; // Singleton instance
    public Connection conn; // Connection object

    // Method to get the singleton instance of the Database class
    public static Database getInstance() {
        if (instance == null) { // If instance is null, create a new instance
            instance = new Database();
        }
        return instance; // Return the instance
    }

    // Method to get the connection
    public Connection getConnection() {
        return conn; // Return the connection object
    }

    // Method to connect to the database using default credentials
    public void connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL driver
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS); // Establish connection using default credentials
    }
    
    // Method to connect to the database using specific user credentials
    public void connectUser() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL driver
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL, "RegularUser", "password"); // Establish connection using provided user credentials
    }
    
    // Method to close the database connection
    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close(); // Close the connection if it's not already closed
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace in case of SQL exception
        }
    }
}
