/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package util.template;

import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author rayk2
 */
public interface TransactionOperation<T> {
    T execute(Connection conn) throws SQLException;
}
