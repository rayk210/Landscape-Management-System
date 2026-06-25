/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;

/**
 *
 * @author rayk2
 */
public final class DBConnect {
    
    private static final String URL = "jdbc:mysql://localhost:3306/landscaping";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "devry123";
    
    private DBConnect() {}
    
    public static Connection getConnection()throws SQLException {
        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
