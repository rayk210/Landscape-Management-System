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
public abstract class ApplicationException extends RuntimeException {
    
    private final ErrorCode code;
    
    protected ApplicationException(ErrorCode code) {
        super(code.getMessage());
        this.code = code;
    }
    
    protected ApplicationException(ErrorCode code, String dynamicMsg) {
        super(dynamicMsg);
        this.code = code;
    }
    
    protected ApplicationException(ErrorCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code;
    }
    
    public ErrorCode getCode() {
        return code;
    }
}
