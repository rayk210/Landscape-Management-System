/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author rayk2
 */
public enum ErrorCode {
    DATABASE_CONNECTION_FAILED(1, "Cannot connect to the database"),
    INVALID_INPUT(2, "Input is invalid"),
    CUSTOMER_NOT_FOUND(3, "Unable to find customer"),
    CONFIGURATION_NOT_FOUND(4, "Unable to locate configuration file");
    
    private final int errorCode;
    private final String errorMsg;
    
    ErrorCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
    
    public int getErrorCode() {
        return errorCode;
    }
    
    public String getMessage() {
        return errorMsg;
    }
}
