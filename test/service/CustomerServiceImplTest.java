/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package service;

import enums.YardType;
import exceptions.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Customer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author rayk2
 */
public class CustomerServiceImplTest {
    
    static CustomerRepository repo;
    static CustomerService service;
    
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
        repo = new FakeCustomerDB();
        service = new CustomerServiceImpl(repo);
        
        service.addCustomer(new Customer(2, "Dita", "123 Biru jl, Indo 3939", YardType.GRASS, 300.0, 400.0, 600000.0));
        service.addCustomer(new Customer(3, "Wahyu", "694 Merah Jaya jl, Indo 43899", YardType.GRASS, 200.0, 250.0, 250000.0));
        service.addCustomer(new Customer(4, "Ridzuone", "345 magyar rd., HU 6400", YardType.GRAVEL, 150.0, 140.0, 42000.0));
    }
    
    @After
    public void tearDown() {
        
    }

    /**
     * Test of addCustomer method, of class CustomerServiceImpl.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("Add customer");
        Customer customer = new Customer(1, "Test", "123 Testing rd. NJ, 39394", YardType.GRASS, 90.0, 95.0, 42750.0);
        service.addCustomer(customer);
        
        ArrayList<Customer> custList = service.getAllCustomers();
        
        Customer cust = custList.get(custList.size() -1);
        
        assertEquals(1, cust.getCustomerID());
        assertEquals("123 Testing rd. NJ, 39394", cust.getAddress());
        assertEquals(YardType.GRASS, cust.getYardType());
        assertEquals(90.0, cust.getWidth(), 0.0001);
        assertEquals(95.0, cust.getLength(), 0.0001);
        assertEquals(42750.0, cust.getTotalCost(), 0.0001);
    }
    
    @Test
    public void testGetAllCustomers() {
        System.out.println("Get all customers");
        ArrayList<Customer> custList = service.getAllCustomers();
        
        assertEquals(3, custList.size());
    }
    
    @Test
    public void testSearchCustomerByNameOrAddress() {
        System.out.println("Search customers by name or address");
        String search = "jl";
        String search2 = "MAGYAR";
        String search3 = "Wah";
        
        ArrayList<Customer> custList = service.searchCustomerByNameOrAddress(search);
        ArrayList<Customer> custList2 = service.searchCustomerByNameOrAddress(search2);
        ArrayList<Customer> custList3 = service.searchCustomerByNameOrAddress(search3);
        
        assertEquals(2, custList.size());
        assertEquals(1, custList2.size());
        assertEquals(1, custList3.size());
    }
    
    @Test
    public void testSortCustomersByAttribute() {
        System.out.println("Sort customer by attribute");
        
        ArrayList<Customer> custList = service.sortCustomersBasedOnAttribute(SortOption.TOTAL_COST);
        
        assertEquals("Ridzuone", custList.get(0).getName());
        assertEquals("Wahyu", custList.get(1).getName());
        assertEquals("Dita", custList.get(2).getName());
    }
    
    @Test
    public void testUpdateCustomer() {
        System.out.println("Update customer");
        ArrayList<Customer> custList = service.getAllCustomers();
        
        Customer updatedCust = custList.get(0);
        
        updatedCust.setYardType(YardType.GRAVEL);
        updatedCust.setWidth(350.0);
        updatedCust.setLength(450.0);
        
        Customer customerUpdated = service.editCustomer(updatedCust);
        assertEquals(YardType.GRAVEL, customerUpdated.getYardType());
        assertEquals(350.0, customerUpdated.getWidth(), 0.001);
        assertEquals(450.0, customerUpdated.getLength(), 0.001);
        
        Customer dbCustomer = service.getAllCustomers().get(0);
        assertEquals(YardType.GRAVEL, dbCustomer.getYardType());
        assertEquals(350.0, dbCustomer.getWidth(), 0.001);
        assertEquals(450.0, dbCustomer.getLength(), 0.001); 
    }
    
    @Test
    public void testDeleteCustomerByID() {
        System.out.println("Delete customer by ID");
        service.addCustomer(new Customer(5, "Sinar", "555 Surya karta jl, Indo 99823", YardType.GRAVEL, 200.0, 250.0, 250000.0));
        ArrayList<Customer> custList = service.getAllCustomers();
        assertEquals(4, service.getAllCustomers().size());
        
        boolean custDeleted = service.deleteCustomer(custList.get(custList.size() - 1).getCustomerID());
        assertEquals(3, service.getAllCustomers().size());
        assertEquals(true, custDeleted);
    }
    
    @Test
    public void testCalculateTotalCost() {
        System.out.println("Calculate total yard cost");
        double totalCost = service.calculateTotalCost(YardType.GRAVEL, 100.0, 120.0);
        assertEquals(24000.0, totalCost, 0.001);
    }
    
    @Test
    public void testBusinessRules() {
        System.out.println("Test business rules");
        
        assertThrows(CustomerNotFoundException.class, () -> service.searchCustomerByNameOrAddress("zs"));
    }
    
    // Architectural core
    interface CustomerRepository {
        void add(Customer customer);
        
        ArrayList<Customer> getAll();
        
        ArrayList<Customer> searchCustomer(String search);
        
        ArrayList<Customer> sortCustomers(SortOption option);
        
        Customer updateCustomer(Customer cust);
        
        boolean delete(int id);
    }
    
    interface CustomerService {
        // methods for customer service
        void addCustomer(Customer customer);
        
        ArrayList<Customer> getAllCustomers();
        
        ArrayList<Customer> searchCustomerByNameOrAddress(String search);
        
        ArrayList<Customer> sortCustomersBasedOnAttribute(SortOption option);
        
        Customer editCustomer(Customer cust);
        
        boolean deleteCustomer(int id);
        
        double calculateTotalCost(YardType yardType, double width, double length); 
    }
    
    // Implementation
    static class CustomerServiceImpl implements CustomerService {
        
        public CustomerRepository repo;
        
        public CustomerServiceImpl(CustomerRepository repo) {
            this.repo = repo;
        }
        
        @Override
        public void addCustomer(Customer customer) {
            repo.add(customer);
        }
        
        @Override
        public ArrayList<Customer> getAllCustomers() {
            return repo.getAll();
        }
        
        @Override
        public ArrayList<Customer> sortCustomersBasedOnAttribute(SortOption option) {
            return repo.sortCustomers(option);
        }
        
        @Override
        public ArrayList<Customer> searchCustomerByNameOrAddress(String search) {
            
            ArrayList<Customer> custList = repo.searchCustomer(search);
            
            if (custList.isEmpty()) {
                throw new CustomerNotFoundException("No customer name or address found for search containing: " + search);
            }
            return custList;
        }
        
        @Override
        public Customer editCustomer(Customer cust) {
            return repo.updateCustomer(cust);
        }
        
        @Override
        public boolean deleteCustomer(int id) {
            return repo.delete(id);
        }
        
        @Override
        public double calculateTotalCost(YardType yardType, double width, double length) {
            return yardType.getCostPerSqFt() * width * length;
        }
    }
    
    static class FakeCustomerDB implements CustomerRepository {
        
        ArrayList<Customer> custList = new ArrayList<>();
        
        @Override
        public void add(Customer cust) {
            custList.add(cust);
        }
        
        @Override
        public ArrayList<Customer> getAll() {
            return custList;
        }
        
        @Override
        public ArrayList<Customer> searchCustomer(String search) {
            
            ArrayList<Customer> tempCust = new ArrayList<>();
            
            for (Customer cust : custList) {
                if (like(cust.getName(), search) || like(cust.getAddress(), search)) {
                    tempCust.add(cust);
                }
            }
            return tempCust;
            
        }
        
        @Override
        public ArrayList<Customer> sortCustomers(SortOption option) {
           
            
            switch (option) {
                case NAME:
                    sortCustomerByAttribute(custList, option, "Sort by name");
                    break;
                
                case YARD_TYPE:
                    sortCustomerByAttribute(custList, option, "Sort by yard type");
                    break;
                    
                case TOTAL_COST:
                    sortCustomerByAttribute(custList, option, "Sort by total cost");
                    break;
            }
            return custList; 
        }
        
        @Override
        public Customer updateCustomer(Customer updatedCust) {
            for (int i = 0; i < custList.size(); i++) {
                if (custList.get(i).getCustomerID() == updatedCust.getCustomerID()) {
                    custList.set(i, updatedCust);  
                }
            }
            return updatedCust;
        }
        
        @Override
        public boolean delete(int id) {
            for (int i = 0; i < custList.size(); i++) {
                if (custList.get(i).getCustomerID() == id) {
                    custList.remove(i);
                    return true;
                }
            }
            return false;
        }
    }
    
    // CustomerSort class implementing Comparator
    static class CustomerSort implements Comparator<Customer> {
        
        public final SortOption option;
        
        public CustomerSort(SortOption option) {
            this.option = option;
        }
        
        @Override
        public int compare(Customer cust1, Customer cust2) {
            
            switch (option) {
                case NAME:
                    return cust1.getName().compareTo(cust2.getName());
                    
                case YARD_TYPE:
                    return cust1.getYardType().compareTo(cust2.getYardType());
                    
                case TOTAL_COST:
                    return Double.compare(cust1.getTotalCost(), cust2.getTotalCost());
                    
                default:
                    throw new IllegalArgumentException("Sort method unavailable");
            }
        }
    }
    
    static void sortCustomerByAttribute(ArrayList<Customer> custList, SortOption option, String message) {
        Collections.sort(custList, new CustomerSort(option));
        System.out.println(message);
        printList(custList);
    }
    
    static void printList(ArrayList<Customer> custList) {
        for (Customer cust : custList) {
            System.out.println(cust.getDetails());
        }
    }
    
    public enum SortOption {
        NAME,
        YARD_TYPE,
        TOTAL_COST;
    }
    
    static boolean like(String str, String search) {
        return str.toLowerCase().contains(search.toLowerCase());
    }
}
