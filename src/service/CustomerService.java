/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CustomerDB;
import java.util.ArrayList;
import model.Customer;

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
    
    // customer service methods
    public void addCustomer(Customer customer) {
        customerDB.add(customer);
    }
    
    public ArrayList<Customer> getAllCustomers() {
        return customerDB.getList();
    }
    
    public void deleteCustomer(int id) {
        customerDB.delete(id);
    }
    
    public double calculateTotalCost(String yardType, double width, double length) {
        
        double area = width * length;
        
        switch (yardType.toLowerCase()) {
            
            case "grass":
                return area * 5.0;
                
            case "gravel":
                return area * 2.0;
                
            default:
                return 0.0;
        }
    }
}
