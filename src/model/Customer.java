package model;


import java.text.DecimalFormat;
import enums.YardType;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author rayk2
 */
public class Customer {
    
    // attributes
    private int customerID;
    private String name;
    private String address;
    private YardType yardType;
    private double width;
    private double length;
    private double totalCost;
    
    // constructors
    public Customer() {
        this.customerID = 0;
        this.name = "Unknown";
        this.address = "N/A";
        this.yardType = yardType.GRASS;
        this.width = 0.0;
        this.length = 0.0;
        this.totalCost = 0.0;
    }
    
    public Customer(int customerID, String name, String address, YardType yardType, double width,
                    double length, double totalCost) {
        
        setCustomerID(customerID);
        setName(name);
        setAddress(address);
        setYardType(yardType);
        setWidth(width);
        setLength(length);
        setTotalCost(totalCost);
    }
    // methods
    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() > 0) {
            this.name = name;
        }
        else
            this.name = "Unknown";
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length() > 0) {
            this.address = address;
        }
        else
            this.address = "N/A";
    }

    public YardType getYardType() {
        return yardType;
    }

    public void setYardType(YardType yardType) {
        this.yardType = yardType;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        if (width > 0) {
            this.width = width;
        }
        else
            this.width = 0.0;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        if (length > 0) {
            this.length = length;
        }
        else
            this.length = 0.0;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    public String getDetails() {
        DecimalFormat fmt = new DecimalFormat("$#,##0.00");
        
        String output = name + "\n";
        output += address + "\n";
        output += "Type: " + yardType.name().toLowerCase() + "\n";
        output += "Width: " + width + "\n";
        output += "Length: " + length + "\n";
        output += "Total Cost: " + fmt.format(totalCost) + "\n";
        output += "\n";
        
        return output;
    }
}
