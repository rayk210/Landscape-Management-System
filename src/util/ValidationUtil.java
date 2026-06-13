/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author rayk2
 */
public class ValidationUtil {
   
    // validation methods
    // required text
    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
    
    // character requirements
    public static boolean isValidName(String text) {
        return text.matches("[A-Za-z ]+");
    }
    
    public static boolean isValidAddress(String text) {
        return text.length() > 5;
    }
    
    // numeric requirements
    public static boolean isGreaterThanZero(double text) {
        return text > 0;
    }
    
    public static boolean isValidNumberOrDecimal(String text) {
        return text.matches("\\d+(\\.\\d+)?");
    }
}
