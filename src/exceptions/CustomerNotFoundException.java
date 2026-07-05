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
public class CustomerNotFoundException extends ApplicationException {
    
    public CustomerNotFoundException() {
        super(ErrorCode.CUSTOMER_NOT_FOUND);
    }
    
    public CustomerNotFoundException(String dynamicMsg) {
        super(ErrorCode.CUSTOMER_NOT_FOUND, dynamicMsg);
    }
    
    public CustomerNotFoundException(Throwable cause) {
        super(ErrorCode.CUSTOMER_NOT_FOUND, cause);
    }
}
