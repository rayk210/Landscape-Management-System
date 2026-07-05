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
public class DatabaseException extends ApplicationException {
    
    public DatabaseException() {
        super(ErrorCode.DATABASE_CONNECTION_FAILED);
    }
    
    public DatabaseException(String dynamicMsg) {
        super(ErrorCode.DATABASE_CONNECTION_FAILED, dynamicMsg);
    }
    
    public DatabaseException(Throwable cause) {
        super(ErrorCode.DATABASE_CONNECTION_FAILED, cause);
    }
}
