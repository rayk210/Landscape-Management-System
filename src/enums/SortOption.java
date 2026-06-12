/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author rayk2
 */
public enum SortOption {
    NAME("name"),
    TOTAL_COST("totalCost"),
    YARD_TYPE("yardType");
    
    // attributes
    private final String column;
    
    // constructor
    SortOption(String column) {
        this.column = column;
    }
    
    // get method
    public String getColumn() {
        return column;
    }
}
