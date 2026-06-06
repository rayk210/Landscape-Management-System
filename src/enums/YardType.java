/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author rayk2
 */
public enum YardType {
    
    // yard types
    GRASS(5.0),
    GRAVEL(2.0);
    
    private double costPerSqFt;
    
    // constructor
    YardType(double costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }
    
    // getter
    public double getCostPerSqFt() {
        return costPerSqFt;
    }
    
    // converts string to corresponding enum value
    public static YardType fromString(String value) {
        
        for (YardType yardType : YardType.values()) {
            
            if (yardType.name().equalsIgnoreCase(value)) {
                return yardType;
            }
        }
        throw new IllegalArgumentException("No enum constant for: " + value);
    }
    
    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
