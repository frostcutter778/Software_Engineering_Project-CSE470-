/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author adittya
 */
public class CreatingConnection {
    public static Connection con;
    public CreatingConnection() throws SQLException
    {
         con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classch?zeroDateTimeBehavior=convertToNull&serverTimezone=UTC", "root", "");
    }

    Object createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
