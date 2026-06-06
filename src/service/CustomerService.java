/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CustomerDB;
import java.util.ArrayList;
import model.Customer;
import enums.YardType;

/**
 *
 * @author rayk2
 */
public class CustomerService {
    
    // attributs
    private final CustomerDB customerDB;
    
    // constructor
    public CustomerService() {
        customerDB = new CustomerDB();
    }
    
    // methods for customer service
    public void addCustomer(Customer customer) {
        customerDB.add(customer);
    }
    
    public ArrayList<Customer> getAllCustomers() {
        return customerDB.getList();
    }
    
    public ArrayList<Customer> searchCustomerByNameOrAddress(String search) {
        return customerDB.searchCustomer(search);
    }
    
    public void editCustomer(Customer customer) {
        customerDB.updateCustomer(customer);
    }
    
    public void deleteCustomer(int id) {
        customerDB.delete(id);
    }
    
    public double calculateTotalCost(YardType yardType, double width, double length) {
        return width * length * yardType.getCostPerSqFt();
    }
}
