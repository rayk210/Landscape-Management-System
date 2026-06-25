/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.logging.Logger;

/**
 *
 * @author rayk2
 */
public final class AppLogger {
    
    private static final Logger LOGGER = Logger.getLogger(AppLogger.class.getName());
    
    private AppLogger() {}
    
    public static Logger getLogger() {
        return LOGGER;
    }
}
