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
public class ValidationException extends ApplicationException {
    
    public ValidationException() {
        super(ErrorCode.INVALID_INPUT);
    }
    
    public ValidationException(String dynamicMsg) {
        super(ErrorCode.INVALID_INPUT, dynamicMsg);
    }
    
    public ValidationException(Throwable cause) {
        super(ErrorCode.INVALID_INPUT, cause);
    }
}
