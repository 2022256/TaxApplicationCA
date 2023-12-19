/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Calculation;
import Database.*;
import java.util.*;
import IOUtils.*;
import java.sql.*;
/**
 *
 * @author 4istik
 */
public class Calculation implements TaxCalculator {
    IOUtils input = new IOUtils(); // Initializing IOUtils for user input
    
    // Method to create data and perform calculations
    public void CreateAndCalculate() {
        Database db = new Database(); // Initializing Database object
        DatabaseSetup dbSetup = new DatabaseSetup(); // Initializing DatabaseSetup object
        
        try {
            db.connect(); // Connecting to the database
            dbSetup.createTable(); // Creating a table in the database for user_income
            
            // Lists to store user input data
            ArrayList<String> usernames = new ArrayList<>();
            ArrayList<Double> grossIncomes = new ArrayList<>();
            ArrayList<Double> taxCredits = new ArrayList<>();
            
            while (true) {
                // Getting user input for username
                String userInput = input.getUserText("Enter name (or 'done' to finish input): ");
                
                // Break loop if user inputs 'done'
                if (userInput.equalsIgnoreCase("done")) {
                    break;
                }
                
                // Formatting username and adding to the list
                usernames.add(userInput.substring(0, 1).toUpperCase() + userInput.substring(1).toLowerCase());
                
                // Getting user input for gross income and tax credits
                double grossIncomeInput = input.getUserDecimal("Enter gross income: ");
                grossIncomes.add(grossIncomeInput);
                
                double taxCreditsInput = input.getUserDecimal("Enter tax credits: ");
                taxCredits.add(taxCreditsInput);
            }
            
            // Calculate tax data and insert into the database
            calculateAndInsertTaxData(db, usernames, grossIncomes, taxCredits);
            
            System.out.println("Data inserted successfully into user_income table.");
            
        } catch (SQLException | ClassNotFoundException se) {
            se.printStackTrace(); // Print stack trace in case of SQL or Class Not Found exceptions
        } finally {
            db.close(); // Close the database connection
        }
    }

    // Method to calculate tax data and insert it into the database
    public void calculateAndInsertTaxData(Database db, ArrayList<String> usernames,
                                          ArrayList<Double> grossIncomes, ArrayList<Double> taxCredits)
            throws SQLException {
        Connection conn = db.getConnection(); // Getting the database connection
        
        try (PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO user_income (username, gross_income, tax_credits, total_tax_owed, paye, usc, prsi) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
            for (int i = 0; i < usernames.size(); i++) {
                // Calculate tax-related data
                double taxOwed = calculateTax(grossIncomes.get(i), taxCredits.get(i));
                double paye = calculatePAYE(grossIncomes.get(i));
                double usc = calculateUSC(grossIncomes.get(i));
                double prsi = calculatePRSI(grossIncomes.get(i));
                
                // Set parameters and execute the SQL query to insert data into the database
                stmt.setString(1, usernames.get(i));
                stmt.setDouble(2, grossIncomes.get(i));
                stmt.setDouble(3, taxCredits.get(i));
                stmt.setDouble(4, taxOwed);
                stmt.setDouble(5, paye);
                stmt.setDouble(6, usc);
                stmt.setDouble(7, prsi);
                stmt.executeUpdate(); // Execute the SQL query
            }
        }
    }
    
    // Method to calculate overall tax based on gross income and tax credits
    @Override
    public double calculateTax(double grossIncome, double taxCredits) {
        if (grossIncome < 0 || taxCredits < 0) {
            throw new IllegalArgumentException("Gross income and tax credits must be positive values.");
        }
        
        double taxRate = 0.20; // Tax rate of 20%
        return (grossIncome * taxRate) - taxCredits; // Calculate and return tax owed
    }
    
    // Method to calculate PAYE tax based on gross income
    @Override
    public double calculatePAYE(double grossIncome) {
        double payeRate = 0.25; // PAYE rate of 25%
        return grossIncome * payeRate; // Calculate and return PAYE
    }
    
    // Method to calculate USC based on gross income
    @Override
    public double calculateUSC(double grossIncome) {
        double uscRate = 0.1; // USC rate of 10%
        return grossIncome * uscRate; // Calculate and return USC
    }
    
    // Method to calculate PRSI based on gross income
    @Override
    public double calculatePRSI(double grossIncome) {
        double prsiRate = 0.05; // PRSI rate of 5%
        return grossIncome * prsiRate; // Calculate and return PRSI
    }
}

