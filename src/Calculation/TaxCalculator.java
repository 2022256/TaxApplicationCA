/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Calculation;

/**
 *
 * @author ivanp & andreic
 */
public interface TaxCalculator {
    
    // Interface named TaxCalculator defining tax calculation methodspublic interface TaxCalculator {
    // Method to calculate overall tax based on gross income and tax credits
    double calculateTax(double grossIncome, double taxCredits);    
    // Method to calculate Pay As You Earn (PAYE) tax based on gross income    
    double calculatePAYE(double grossIncome);
        // Method to calculate Universal Social Charge (USC) based on gross income
    double calculateUSC(double grossIncome);    
    // Method to calculate Pay Related Social Insurance (PRSI) based on gross income    
    double calculatePRSI(double grossIncome);
    }

