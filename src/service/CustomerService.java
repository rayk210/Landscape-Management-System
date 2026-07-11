/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import enums.SortOption;
import java.util.ArrayList;
import model.Customer;
import enums.YardType;

/**
 *
 * @author rayk2
 */
public interface CustomerService {
    
    // methods for customer service
    void addCustomer(Customer customer);
    
    ArrayList<Customer> getAllCustomers();
    
    ArrayList<Customer> sortCustomersBasedOnAttribute(SortOption option);
    
    ArrayList<Customer> searchCustomerByNameOrAddress(String search);
    
    void editCustomer(Customer customer);
    
    void deleteCustomer(int id);
    
    double calculateTotalCost(YardType yardType, double width, double length);
}
