/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CustomerDB;
import util.template.*;
import dao.CustomerRepository;
import exceptions.CustomerNotFoundException;
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
    public void addCustomer(Customer customer) {
        TransactionTemplate.run(conn -> {customerRepository.add(conn, customer);
        return null;
        });
    }
    
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }
    
    public ArrayList<Customer> sortCustomersBasedOnAttribute(SortOption option) {
        return customerRepository.sortCustomers(option);
    }
    
    public ArrayList<Customer> searchCustomerByNameOrAddress(String search) {
        ArrayList<Customer> custList = customerRepository.searchCustomer(search);
        
        if (custList.isEmpty()) {
            throw new CustomerNotFoundException();
        }
        return custList;
    }
    
    public void editCustomer(Customer customer) {
        TransactionTemplate.run(conn -> {customerRepository.updateCustomer(conn, customer);
        return null;
        });
    }
    
    public void deleteCustomer(int id) {
        TransactionTemplate.run(conn -> {customerRepository.delete(conn, id);
        return null;
        });
    }
    
    public double calculateTotalCost(YardType yardType, double width, double length) {
        return width * length * yardType.getCostPerSqFt();
    }
}
