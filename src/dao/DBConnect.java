/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import config.ConfigLoader;
import config.DatabaseConfig;

/**
 *
 * @author rayk2
 */
public final class DBConnect {
    
    private DBConnect() {}
    
    public static Connection getConnection() throws SQLException {
        DatabaseConfig config = ConfigLoader.getInstance().getProperty();
        return DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword());
    }
}
