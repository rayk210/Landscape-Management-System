/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CustomerDB;
import dao.CustomerRepository;
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
    private final CustomerRepository customerRepository;
    
    // constructor
    public CustomerService() {
        customerRepository = new CustomerDB();
    }
    
    // methods for customer service
    public boolean addCustomer(Customer customer) {
        return customerRepository.add(customer);
    }
    
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }
    
    public ArrayList<Customer> sortCustomersBasedOnAttribute(SortOption option) {
        return customerRepository.sortCustomers(option);
    }
    
    public ArrayList<Customer> searchCustomerByNameOrAddress(String search) {
        return customerRepository.searchCustomer(search);
    }
    
    public boolean editCustomer(Customer customer) {
        return customerRepository.updateCustomer(customer);
    }
    
    public boolean deleteCustomer(int id) {
        return customerRepository.delete(id);
    }
    
    public double calculateTotalCost(YardType yardType, double width, double length) {
        return width * length * yardType.getCostPerSqFt();
    }
}
