/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.Properties;

import util.AppLogger;

/**
 *
 * @author rayk2
 */
public final class ConfigLoader {
    
    private static ConfigLoader instance;
    private final DatabaseConfig config;
    
    private ConfigLoader() {
        // object representing a presistant set of properties
        Properties properties = new Properties();
        
        // create a file object and open an input stream for reading
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("config/database.properties"))
        {
            // check if classpath is null
            if (input == null) {
                throw new IOException("Cannot find database configuration classpath");
            }
            
            // load properties of the file from inputstream
            properties.load(input);
            
            // extract properties from file
            String dbUrl = properties.getProperty("db.url");
            String dbName = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            
            // create new Database config object with extracted properties
            config = new DatabaseConfig(dbUrl, dbName, dbPassword);
        }
        catch (IOException ex) {
            AppLogger.getLogger().log(Level.SEVERE, "Unable to read database configuration file", ex);
            throw new RuntimeException("Error. Unable to read database configuration file");
        }
    }
    
    // ensure one instance is created throughout the application
    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }
    
    public DatabaseConfig getProperty() {
        return config;
    }
}
