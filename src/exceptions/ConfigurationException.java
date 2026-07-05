/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exceptions;

import enums.ErrorCode;

/**
 *
 * @author rayk2
 */
public class ConfigurationException extends ApplicationException {
    
    public ConfigurationException() {
        super(ErrorCode.CONFIGURATION_NOT_FOUND);
    }
    
    public ConfigurationException(String dynamic) {
        super(ErrorCode.CONFIGURATION_NOT_FOUND, dynamic);
    }
    
    public ConfigurationException(Throwable cause) {
        super(ErrorCode.CONFIGURATION_NOT_FOUND, cause);
    }
}
