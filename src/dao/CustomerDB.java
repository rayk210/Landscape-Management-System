package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import model.Customer;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import enums.YardType;
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
    public boolean add(Customer cust) {
        
        try {
            // establish connection
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            
            // create sql query
            String sqlQuery = "INSERT INTO customers (name, address, yardType, width, length, totalCost) VALUES (?,?,?,?,?,?)";
            
            // create prepared statement
            PreparedStatement pStmt = conn.prepareStatement(sqlQuery);
            
            // set values for statement
            pStmt.setString(1, cust.getName());
            pStmt.setString(2, cust.getAddress());
            pStmt.setString(3, cust.getYardType().toString());
            pStmt.setDouble(4, cust.getWidth());
            pStmt.setDouble(5, cust.getLength());
            pStmt.setDouble(6, cust.getTotalCost());
            
            // execute statement
            pStmt.executeUpdate();
            
            // close connection
            conn.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return true;
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
                YardType yardType = YardType.fromString(rs.getString("yardType"));
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
    
    // update customer fields
    public boolean updateCustomer(Customer customer) {
        
        try {
            // establish connection
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            
            // create sql query
            String sqlQuery = "UPDATE customers SET name = ?, address = ?, yardType = ?, width = ?, length = ?, totalCost = ? WHERE customerID = ?";
            
            // create prepared statement
            PreparedStatement pStmt = conn.prepareStatement(sqlQuery);
            
            // set values for statement
            pStmt.setString(1, customer.getName());
            pStmt.setString(2, customer.getAddress());
            pStmt.setString(3, customer.getYardType().name());
            pStmt.setDouble(4, customer.getWidth());
            pStmt.setDouble(5, customer.getLength());
            pStmt.setDouble(6, customer.getTotalCost());
            pStmt.setInt(7, customer.getCustomerID());
            
            // execute statement
            pStmt.executeUpdate();
            
            // close connection
            conn.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }
    
    // get customer based on name or address
    public ArrayList<Customer> searchCustomer(String search) {
        // create arraylist to hold search results
        ArrayList<Customer> custList = new ArrayList<>();
        
        try {
            // establish connection
            conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            
            // create sql statement
            String sqlQuery = "SELECT * FROM customers WHERE name LIKE ? OR address LIKE ?";
            
            // create prepared statemnt
            PreparedStatement pStmt = conn.prepareStatement(sqlQuery);
            
            // wildcard string format for searches
            String wildCard = "%" + search + "%";
            
            // set values for prepared statement
            pStmt.setString(1, wildCard);
            pStmt.setString(2, wildCard);
            
            // execute statement
            ResultSet rs = pStmt.executeQuery();
            
            // process the results of query
            while (rs.next()) {
                
                int id = rs.getInt("customerID");
                String name = rs.getString("name");
                String address = rs.getString("address");
                YardType yardType = YardType.fromString(rs.getString("yardType"));
                double width = rs.getDouble("width");
                double length = rs.getDouble("length");
                double totalCost = rs.getDouble("totalCost");
                
                // fill array list with results
                custList.add(new Customer(id, name, address, yardType, width, length, totalCost));
            }
            // close connection
            conn.close();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Could not connect to database!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // return customer list
        return custList;
    }
    
    // delete a customer from the database
    public boolean delete(int customerID) {
        
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
            pStmt.executeUpdate();
            
            // close connection
            conn.close();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }
}
