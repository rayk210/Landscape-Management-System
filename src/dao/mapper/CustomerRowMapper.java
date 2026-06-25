/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.mapper;

import model.Customer;
import enums.YardType;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rayk2
 */
public class CustomerRowMapper implements RowMapper<Customer> {
    
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        int id = rs.getInt("customerID");
        String name = rs.getString("name");
        String address = rs.getString("address");
        YardType yardType = YardType.fromString(rs.getString("yardType"));
        double width = rs.getDouble("width");
        double length = rs.getDouble("length");
        double totalCost = rs.getDouble("totalCost");
        
        return new Customer(id, name, address, yardType, width, length, totalCost);
    }
}