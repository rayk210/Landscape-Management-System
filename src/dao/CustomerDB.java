package dao;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import dao.mapper.CustomerRowMapper;
import dao.mapper.RowMapper;
import util.AppLogger;
import exceptions.DatabaseException;
import model.Customer;
import enums.SortOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
/**
 *
 * @author rayk2
 */
public class CustomerDB implements CustomerRepository {
    
    private static final String INSERT_CUSTOMER = "INSERT INTO customers (name, address, yardType, width, length, totalCost) "
                                                  + "VALUES (?,?,?,?,?,?)";
    private static final String GET_ALL_CUSTOMERS = "SELECT * FROM customers";
    private static final String SEARCH_CUSTOMERS = "SELECT * FROM customers WHERE name LIKE ? OR address LIKE ?";
    private static final String SORT_CUSTOMERS = "SELECT * FROM customers ORDER BY ";
    private static final String UPDATE_CUSTOMER = "UPDATE customers SET name = ?, address = ?, yardType = ?, width = ?, length = ?, totalCost = ? WHERE customerID = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM customers WHERE customerID = ?";
    
    private static final RowMapper<Customer> CustomerMapper = new CustomerRowMapper();
    
    // insert customer into Database
    @Override
    public void add(Connection conn, Customer customer) {
        
        AppLogger.getLogger().log(Level.INFO, "Inserting customer: " + customer.getName());
        
        try (PreparedStatement pStmt = conn.prepareStatement(INSERT_CUSTOMER))
        {
            // set values for statement
            pStmt.setString(1, customer.getName());
            pStmt.setString(2, customer.getAddress());
            pStmt.setString(3, customer.getYardType().name());
            pStmt.setDouble(4, customer.getWidth());
            pStmt.setDouble(5, customer.getLength());
            pStmt.setDouble(6, customer.getTotalCost());
            
            // execute statement
            int rowsAffected = pStmt.executeUpdate();
            
            AppLogger.getLogger().log(Level.INFO, "Successfully inserted customer: " + customer.getName() + "." + " Rows affected: " + rowsAffected);
            
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    // retrieve all customers from Database
    @Override
    public ArrayList<Customer> getAll() {
        
        AppLogger.getLogger().log(Level.INFO, "Retrieving all customers.");
        
        try (Connection conn = DBConnect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(GET_ALL_CUSTOMERS))
        {
                 ArrayList<Customer> custList = mapRows(rs);
                 AppLogger.getLogger().log(Level.INFO, "Retrieved " + custList.size() + " customers.");
                 
                 return custList;
        }
        catch (SQLException e) {
                throw new DatabaseException(e);
        }
    }
    
    // sort customer based on name, yard type, or total cost
    @Override
    public ArrayList<Customer> sortCustomers(SortOption option) {
        
        AppLogger.getLogger().log(Level.INFO, "Sorting customers by " + option.name());
        
        try (Connection conn = DBConnect.getConnection();
            // create statement
            Statement stmt = conn.createStatement();
                
            // execute statement
            ResultSet rs = stmt.executeQuery(SORT_CUSTOMERS + option.getColumn()))
        {
            // process results of the query
            ArrayList<Customer> custList = mapRows(rs);
            
            AppLogger.getLogger().log(Level.INFO, "Sorted customers by " + option.name());
            
            return custList;
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    // update customer based on customer ID
    @Override
    public void updateCustomer(Connection conn, Customer customer) {
        
        AppLogger.getLogger().log(Level.INFO, "Updating customer ID: " + customer.getCustomerID() + ".");
        
        try (PreparedStatement pStmt = conn.prepareStatement(UPDATE_CUSTOMER))
        {
            fillUpdateStatement(pStmt, customer);
            
            // execute statement
            int rowsAffected =  pStmt.executeUpdate();
            
            AppLogger.getLogger().log(Level.INFO, "Successfully updated customer ID: " + customer.getCustomerID() + "." + " Rows affected: " + rowsAffected);
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    // get customer based on name or address
    @Override
    public ArrayList<Customer> searchCustomer(String search) {
        String fmt = "'" + search + "'";
        
        AppLogger.getLogger().log(Level.INFO, "Searching for customer. " + "Keyword: " + fmt);
        
        try (Connection conn = DBConnect.getConnection();
            // create prepared statement
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
            ArrayList<Customer> custList =  mapRows(rs);
            
            AppLogger.getLogger().log(Level.INFO, custList.size() + " customers found.");
            
            return custList;
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    // delete a customer from the database
    @Override
    public void delete(Connection conn, int customerID) {
        
        AppLogger.getLogger().log(Level.INFO, "Deleting customer ID: " + customerID);
        
        try (PreparedStatement pStmt = conn.prepareStatement(DELETE_CUSTOMER))
        {
            // set values for statment
            pStmt.setInt(1, customerID);
            
            // execute statement
            int rowsAffected =  pStmt.executeUpdate();
            
            AppLogger.getLogger().log(Level.INFO, "Successfully deleted customer ID: " + customerID + "." + " Rows affected: " + rowsAffected);
        }
        catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
    
    private void fillUpdateStatement(PreparedStatement pStmt, Customer customer) throws SQLException {
        
        pStmt.setString(1, customer.getName());
        pStmt.setString(2, customer.getAddress());
        pStmt.setString(3, customer.getYardType().name());
        pStmt.setDouble(4, customer.getWidth());
        pStmt.setDouble(5, customer.getLength());
        pStmt.setDouble(6, customer.getTotalCost());
        pStmt.setInt(7, customer.getCustomerID());
    }
    
    // helper method for mapping rows
    private ArrayList<Customer> mapRows(ResultSet rs) throws SQLException {
        
        ArrayList<Customer> custList = new ArrayList<>();
        int rowNum = 0;
        
        while(rs.next()) {
            custList.add(CustomerMapper.mapRow(rs, rowNum));
            rowNum ++;
        }
        return custList;
    }
}
