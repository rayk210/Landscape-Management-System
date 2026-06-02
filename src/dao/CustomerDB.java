package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author rayk2
 */
public class CustomerDB {
    
    // attributes
    private static final String URL = "jdbc:mysql://localhost:3306/landscaping";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "devry123";
    
    Connection conn;
    
    // insert customer into Database
    public void add(Customer cust) {
        
        try {
            // establish connection
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            
            // create sql query
            String sqlQuery = "INSERT INTO customers (name, address, yardType, width, length, totalCost) VALUES (?,?,?,?,?,?)";
            
            // create prepared statement
            PreparedStatement pStmt = conn.prepareStatement(sqlQuery);
            
            String name = cust.getName();
            String address = cust.getAddress();
            String yardType = cust.getYardType();
            double width = cust.getWidth();
            double length = cust.getLength();
            double totalCost = cust.getTotalCost();
            
            // set values for statement
            pStmt.setString(1,name);
            pStmt.setString(2, address);
            pStmt.setString(3, yardType);
            pStmt.setDouble(4, width);
            pStmt.setDouble(5, length);
            pStmt.setDouble(6, totalCost);
            
            // execute statement
            int rs = pStmt.executeUpdate();
            
            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Insert Successful!");
            }
            
            // close connection
            conn.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // retrieve a list of customers from Database
    public ArrayList<Customer> getList() {
        
        // create array list to hold query results
        ArrayList<Customer> custList = new ArrayList<>();
        
        try {
            // establish connection
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            
            // create sql query
            String sqlStmt = "SELECT * FROM customers";
            
            // create statement
            Statement stmt = conn.createStatement();
            
            // execute statement
            ResultSet rs = stmt.executeQuery(sqlStmt);
            
            // process the results from the query
            while(rs.next()) {
                int customerID = rs.getInt("customerID");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String yardType = rs.getString("yardType");
                double width = rs.getDouble("width");
                double length = rs.getDouble("length");
                double totalCost = rs.getDouble("totalCost");
                
                // add elements to array list
                custList.add(new Customer(customerID, name, address, yardType, width, length, totalCost));
            }
            // close connection
            conn.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return custList;
    }
    
    // delete a customer from the database
    public void delete(int customerID) {
        
        try {
            // establish connection
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            
            // create sql query
            String sqlStmt = "DELETE FROM customers WHERE customerID = ?";
            
            // create preparedstatment
            PreparedStatement pStmt = conn.prepareStatement(sqlStmt);
            
            // set values for statment
            pStmt.setInt(1, customerID);
            
            // execute statement
            int rs = pStmt.executeUpdate();
            
            if (rs > 0) {
                JOptionPane.showMessageDialog(null, "Customer Deleted!");
            }
            // close connection
            conn.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
