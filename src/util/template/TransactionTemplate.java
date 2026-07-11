/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util.template;

import java.sql.Connection;
import java.sql.SQLException;
import dao.*;
import util.AppLogger;
import java.util.logging.Level;
import exceptions.DatabaseException;
/**
 *
 * @author rayk2
 */
public class TransactionTemplate {
    
    public static <T> T run(TransactionOperation<T> operation) {
        Connection conn = null;
        
        try {
            conn = DBConnect.getConnection();
            
            // change default autocommit behavior to false to treat transactions as a single unit
            conn.setAutoCommit(false);
            
            // call execute() to carry out a specific task
            T result = operation.execute(conn);
            
            // commit changes if successful
            conn.commit();
            
            // return results
            return result;
        }
        catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                }
                catch (SQLException exRollBack) {
                    AppLogger.getLogger().log(Level.SEVERE, "Cannot rollback database");
                }
            }
            throw new DatabaseException(e);
        }
        finally {
            if (conn != null) {
                try {
                    // return auto commit true
                    conn.setAutoCommit(true);
                    
                    // close connection
                    conn.close();
                }
                catch (SQLException exClose) {
                    throw new DatabaseException(exClose);
                }
            }
        }
    }
}
