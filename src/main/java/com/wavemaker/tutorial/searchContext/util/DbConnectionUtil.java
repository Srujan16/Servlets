package com.wavemaker.tutorial.searchContext.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by srujant on 15/7/16.
 */
public class DbConnectionUtil {
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to register driver",e);
        }
    }


    public static Connection getConnection(){
        try{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql","root","pramati123");
        } catch (SQLException e) {
            throw new RuntimeException("Denied to create new connection",e);
        }
    }

}
