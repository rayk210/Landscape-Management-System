/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rayk2
 * @param <T>
 */
public interface RowMapper<T> {
    
    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}