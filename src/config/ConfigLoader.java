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
        
        Properties properties = new Properties();
        
        try (InputStream stream = ConfigLoader.class.getClassLoader().getResourceAsStream("config/database.properties"))
        {
            if (stream == null) {
                throw new IOException("Unable to find class path");
            }
            
            // load properties from stream
            properties.load(stream);
            
            // extract properties from file
            String dbUrl = properties.getProperty("db.url");
            String dbName = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            
            // assign properties to temp database config object
            config = new DatabaseConfig(dbUrl, dbName, dbPassword);
            
        }
        catch (IOException ex) {
            AppLogger.getLogger().log(Level.SEVERE, "Unable to read database configuration file", ex);
            throw new RuntimeException("Unable to read database configuration file");
        }
    }
    
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
