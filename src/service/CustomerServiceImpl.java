/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.CustomerRepository;
import enums.SortOption;
import enums.YardType;
import exceptions.CustomerNotFoundException;
import java.util.ArrayList;
import model.Customer;
import util.template.TransactionTemplate;

/**
 *
 * @author rayk2
 */
public class CustomerServiceImpl implements CustomerService {
    
    // attributes
    // CustomerServiceImpl is dependent on CustomerRepository
    private final CustomerRepository customerRepository;
    
    // constructor-based dependency injection
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    
    // methods for customer service
    @Override
    public void addCustomer(Customer customer) {
        TransactionTemplate.run(conn -> {customerRepository.add(conn, customer);
        return null;
        });
    }
    
    @Override
    public ArrayList<Customer> getAllCustomers() {
        return customerRepository.getAll();
    }
    
    @Override
    public ArrayList<Customer> sortCustomersBasedOnAttribute(SortOption option) {
        return customerRepository.sortCustomers(option);
    }
    
    @Override
    public ArrayList<Customer> searchCustomerByNameOrAddress(String search) {
        ArrayList<Customer> custList = customerRepository.searchCustomer(search);
        
        if (custList.isEmpty()) {
            throw new CustomerNotFoundException("No customer name or address found for search containing: " + search);
        }
        return custList;
    }
    
    @Override
    public void editCustomer(Customer customer) {
        TransactionTemplate.run(conn -> {customerRepository.updateCustomer(conn, customer);
        return null;
        });
    }
    
    @Override
    public void deleteCustomer(int id) {
        TransactionTemplate.run(conn -> {customerRepository.delete(conn, id);
        return null;
        });
    }
    
    @Override
    public double calculateTotalCost(YardType yardType, double width, double length) {
        return width * length * yardType.getCostPerSqFt();
    }
}
