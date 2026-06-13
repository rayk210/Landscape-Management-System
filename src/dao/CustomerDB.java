package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import model.Customer;
import enums.SortOption;
import java.sql.*;
import java.util.ArrayList;
import enums.YardType;
/**
 *
 * @author rayk2
 */
public class CustomerDB implements CustomerRepository {
    
    // attributes
    private static final String URL = "jdbc:mysql://localhost:3306/landscaping";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "devry123";
    
    private static final String INSERT_CUSTOMER = "INSERT INTO customers (name, address, yardType, width, length, totalCost) "
                                                  + "VALUES (?,?,?,?,?,?)";
    private static final String GET_ALL_CUSTOMERS = "SELECT * FROM customers";
    private static final String SEARCH_CUSTOMERS = "SELECT * FROM customers WHERE name LIKE ? OR address LIKE ?";
    private static final String SORT_CUSTOMERS = "SELECT * FROM customers ORDER BY ";
    private static final String UPDATE_CUSTOMER = "UPDATE customers SET name = ?, address = ?, yardType = ?, width = ?, length = ?, totalCost = ? WHERE customerID = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customers WHERE customerID = ?";
    
    // insert customer into Database
    @Override
    public boolean add(Customer customer) {
        
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             
            // create prepared statment
            PreparedStatement pStmt = conn.prepareStatement(INSERT_CUSTOMER))
        {
            // set values for statement
            pStmt.setString(1, customer.getName());
            pStmt.setString(2, customer.getAddress());
            pStmt.setString(3, customer.getYardType().name());
            pStmt.setDouble(4, customer.getWidth());
            pStmt.setDouble(5, customer.getLength());
            pStmt.setDouble(6, customer.getTotalCost());
            
            // execute statement
            return pStmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // retrieve all customers from Database
    @Override
    public ArrayList<Customer> getAll() {
        
        // create array list to hold query results
        ArrayList<Customer> custList = new ArrayList<>();
        
        // establish connection
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
                
            // create statement
            Statement stmt = conn.createStatement();
                
            // execute statement
            ResultSet rs = stmt.executeQuery(GET_ALL_CUSTOMERS))
        {
            
            // process the results from the query
            while(rs.next()) {
                custList.add(mapCustomer(rs));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return custList;
    }
    
    // sort customer based on name, yard type, or total cost
    @Override
    public ArrayList<Customer> sortCustomers(SortOption option) {
        // create array list to hold results
        ArrayList<Customer> custList = new ArrayList<>();
        
        // create sql query based on chosen option of sorting
        //String sqlQuery = SORT_CUSTOMERS + option.getColumn();
        
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            // create statement
            Statement stmt = conn.createStatement();
                
            // execute statement
            ResultSet rs = stmt.executeQuery(SORT_CUSTOMERS + option.getColumn()))
        {
            // process results of the query
            while (rs.next()) {
                custList.add(mapCustomer(rs));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        // return customer array list
        return custList;
    }
    
    // update customer based on customer ID
    @Override
    public boolean updateCustomer(Customer customer) {
        
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            // make prepared statement
            PreparedStatement pStmt = conn.prepareStatement(UPDATE_CUSTOMER))
        {
            // set value for prepared statment
            pStmt.setString(1, customer.getName());
            pStmt.setString(2, customer.getAddress());
            pStmt.setString(3, customer.getYardType().name());
            pStmt.setDouble(4, customer.getWidth());
            pStmt.setDouble(5, customer.getLength());
            pStmt.setDouble(6, customer.getTotalCost());
            pStmt.setInt(7, customer.getCustomerID());
            
            // execute statement
            return pStmt.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // get customer based on name or address
    @Override
    public ArrayList<Customer> searchCustomer(String search) {
        // create arraylist to hold search results
        ArrayList<Customer> custList = new ArrayList<>();
        
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            // create prepared statemnt
            PreparedStatement pStmt = conn.prepareStatement(SEARCH_CUSTOMERS))
        {
            // wildcard string format for searches
            String wildCard = "%" + search + "%";
            
            // set values for prepared statement
            pStmt.setString(1, wildCard);
            pStmt.setString(2, wildCard);
            
            // execute statement
            ResultSet rs = pStmt.executeQuery();
            
            // process the results of query
            while (rs.next()) {
                custList.add(mapCustomer(rs));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return custList;
    }
    
    // delete a customer from the database
    @Override
    public boolean delete(int customerID) {
        
        try (Connection conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
             PreparedStatement pStmt = conn.prepareStatement(DELETE_CUSTOMER))
        {
            // set values for statment
            pStmt.setInt(1, customerID);
            
            // execute statement
            return pStmt.executeUpdate() > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private Customer mapCustomer(ResultSet rs) throws SQLException {
        
        // extract customer fields from result set to build customer
        int id = rs.getInt("customerID");
        String name = rs.getString("name");
        String address = rs.getString("address");
        YardType yardType = YardType.fromString(rs.getString("yardType"));
        double width = rs.getDouble("width");
        double length = rs.getDouble("length");
        double totalCost = rs.getDouble("totalCost");
        
         return new Customer(id, name, address, yardType, width, length, totalCost);
    }
}
