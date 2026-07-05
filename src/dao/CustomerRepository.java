/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.util.ArrayList;
import model.Customer;
import enums.SortOption;


/**
 *
 * @author rayk2
 */
public interface CustomerRepository {
    
    void add(Connection conn, Customer customer);
    
    ArrayList<Customer> getAll();
    
    ArrayList<Customer> sortCustomers(SortOption option);
    
    ArrayList<Customer> searchCustomer(String search);
    
    void updateCustomer(Connection conn, Customer customer);
    
    void delete(Connection conn, int id);
}
