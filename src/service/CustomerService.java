/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CustomerDB;
import enums.SortOption;
import java.util.ArrayList;
import model.Customer;
import enums.YardType;

/**
 *
 * @author rayk2
 */
public class CustomerService {
    
    // attributes
    private final CustomerDB customerDB;
    
    // constructor
    public CustomerService() {
        customerDB = new CustomerDB();
    }
    
    // methods for customer service
    public boolean addCustomer(Customer customer) {
        return customerDB.add(customer);
    }
    
    public ArrayList<Customer> getAllCustomers() {
        return customerDB.getList();
    }
    
    public ArrayList<Customer> sortCustomersBasedOnAttribute(SortOption option) {
        return customerDB.sortCustomers(option);
    }
    
    public ArrayList<Customer> searchCustomerByNameOrAddress(String search) {
        return customerDB.searchCustomer(search);
    }
    
    public boolean editCustomer(Customer customer) {
        return customerDB.updateCustomer(customer);
    }
    
    public boolean deleteCustomer(int id) {
        return customerDB.delete(id);
    }
    
    public double calculateTotalCost(YardType yardType, double width, double length) {
        return width * length * yardType.getCostPerSqFt();
    }
}
