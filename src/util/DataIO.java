package util;


import model.Customer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rayk2
 */
public class DataIO {
    
    // attributes
    private String fileName;
    
    // constructors
    public DataIO() {
        this.fileName = "customers.txt";
    }
    
    public DataIO(String fileName) {
        setFileName(fileName);
    }
    
    // methods
    public void setFileName(String fileName) {
        if (fileName.length() > 0) {
            this.fileName = fileName;
        }
        else {
            this.fileName = "customers.txt";
        }
    }
    
    // write from an array list to a file
    public boolean add(ArrayList<Customer> custList) {
        
        try {
            // create file object and open pipe
            BufferedWriter outLine = new BufferedWriter(new FileWriter(fileName));
            
            // write data to file
            for (int i = 0; i < custList.size(); i++) {
                Customer cust = custList.get(i);
                
                outLine.write(String.valueOf(cust.getCustomerID()));
                outLine.write("#" + cust.getName());
                outLine.write("#" + cust.getAddress());
                outLine.write("#" + cust.getYardType());
                outLine.write("#" + cust.getWidth());
                outLine.write("#" + cust.getLength());
                outLine.write("#" + cust.getTotalCost());
                
                // insert new line for next customer
                outLine.newLine(); 
            }
            // close buffer
            outLine.close();
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Cannot write to file!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    // read data from a file to an arraylist
    public ArrayList<Customer> getList() {
        // create array list to hold data from file
        ArrayList<Customer> custList = new ArrayList<>();
        
        try {
            // create file object and open file for reading
            BufferedReader inBuffer = new BufferedReader(new FileReader(fileName));
            
            // create line reader
            String inLine = ""; 
            inLine = inBuffer.readLine();
            
            // declare StringTokenizer
            StringTokenizer tokens;
            
            // read file until EOF
            while(inLine != null) {
                
                // break lines into fields based on delimiter
                tokens = new StringTokenizer(inLine, "#");
                
                // extract data from file
                int id = Integer.parseInt(tokens.nextToken());
                String name = tokens.nextToken();
                String address = tokens.nextToken();
                String yardType = tokens.nextToken();
                double width = Double.parseDouble(tokens.nextToken());
                double length = Double.parseDouble(tokens.nextToken());
                double totalCost = Double.parseDouble(tokens.nextToken());
                
                // add fields to array list
                custList.add(new Customer(id, name, address, yardType, width, length, totalCost));
                
                // if inLine is null, stop reading
                inLine = inBuffer.readLine();
            }
            // close pipe
            inBuffer.close();
            
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Cannot read from file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // return customer list
        return custList;
    }
   
    /*
    // delete file
    public void delete(String name) throws IOException {
        
        // array list to get customers from file
        ArrayList<Customer> custList = this.getList();
        
        File oldFile = new File("Customer.txt");
        
        if (oldFile.exists() == false) {
            throw new IOException("File does not exist!");
        }
        oldFile.delete();
        
        for (Customer cust : custList) {
            
            if (!name.equalsIgnoreCase(cust.getName())) {
                this.add(cust);
            }
        }
        
    }
    */
}
