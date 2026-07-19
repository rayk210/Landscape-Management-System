/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package service;

import dao.CustomerRepository;
import enums.YardType;
import exceptions.*;
import java.util.ArrayList;
import java.sql.Connection;
import model.Customer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;


/**
 *
 * @author rayk2
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {
   
    static CustomerRepository customerRepository;
    static CustomerService service;
   
    private Customer eve;
    private Customer alice;
    private Customer bob;
    
    public CustomerServiceImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
       
    }
    
    @Before
    public void setUp() {
        customerRepository = mock(CustomerRepository.class);
        service = new CustomerServiceImpl(customerRepository);
        
        eve = new Customer(2, "Eve", "123 green rd, NJ 3939", YardType.GRASS, 300.0, 400.0, 600000.0);
        alice = new Customer(3, "Alice", "694 red rd, NJ 43899", YardType.GRASS, 200.0, 250.0, 250000.0);
        bob = new Customer(4, "Bob", "345 blue rd, WI 6400", YardType.GRAVEL, 150.0, 140.0, 42000.0);
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of addCustomer method, of class CustomerServiceImpl.
     */
    @Test
    public void testAddCustomer() {
        Customer customer = new Customer(1, "Test", "123 Testing rd. NJ, 39394", YardType.GRASS, 90.0, 95.0, 42750.0);
        service.addCustomer(customer);
        
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        verify(customerRepository).add(any(Connection.class), customerCaptor.capture());
        
        Customer captured = customerCaptor.getValue();
        assertEquals("Test", captured.getName());
        assertEquals(YardType.GRASS, captured.getYardType());
    }
    
    @Test
    public void testGetAllCustomers() {
        ArrayList<Customer> custList = new ArrayList<>();
        
        custList.add(eve);
        custList.add(alice);
        custList.add(bob);
        
        when(customerRepository.getAll()).thenReturn(custList);
        
        ArrayList<Customer> result = service.getAllCustomers();
        
        assertEquals(3, result.size());
        assertEquals("Eve", result.get(0).getName());
        assertEquals("Alice", result.get(1).getName());
        assertEquals("Bob", result.get(2).getName());
        
    }
    
    @Test
    public void testSearchCustomerByNameOrAddress() {
        ArrayList<Customer> custList = new ArrayList<>();
        custList.add(alice);
        
        when(customerRepository.searchCustomer("Ali")).thenReturn(custList);
        
        ArrayList<Customer> result = service.searchCustomerByNameOrAddress("Ali");
        
        assertEquals("Alice", result.get(0).getName());
        
        verify(customerRepository).searchCustomer("Ali");
    }
    
    @Test
    public void testUpdateCustomer() {
        Customer updatedCustomer = new Customer(2, "Updated Eve", "123 new rd, NJ 4939", YardType.GRAVEL, 250.0, 260.0, 130000.0);
        
        service.editCustomer(updatedCustomer);
        
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        
        verify(customerRepository).updateCustomer(any(Connection.class), customerCaptor.capture());
        
        Customer captured = customerCaptor.getValue();
        assertEquals("Updated Eve", captured.getName());
        assertEquals(250.0, captured.getWidth(), 0.001);
    }
    
    @Test
    public void testDeleteCustomer() {
        
        int customerID = 4;
        
        service.deleteCustomer(customerID);
        
        verify(customerRepository).delete(any(Connection.class), eq(customerID));
    }
    
    @Test
    public void testCustomerNotFoundException() {
        ArrayList<Customer> emptyList = new ArrayList<>();
        
        when(customerRepository.searchCustomer("zs")).thenReturn(emptyList);
             
        assertThrows(CustomerNotFoundException.class, () -> service.searchCustomerByNameOrAddress("zs"));
        
        verify(customerRepository).searchCustomer("zs");
    }
    
    @Test
    public void testDatabaseException() {
        
        when(customerRepository.getAll()).thenThrow(new DatabaseException());
        
        assertThrows(DatabaseException.class, () -> service.getAllCustomers());
    }
}