package com.mycompany.grocery_store;

/**
 *
 * @author Luka
 */
import java.sql.*;

public class DatabaseHelper {

    private String MySQLURL = "jdbc:mysql://localhost:3306/Grocery_Store";
    private String databseUserName = "root";
    private String databasePassword = "My$qlR00t";
    private Connection con = null;

    public DatabaseHelper(){}

    public void ConnectDB()
    {
        try {
            con = DriverManager.getConnection(MySQLURL, databseUserName, databasePassword);
            if (con != null) 
                System.out.println("Database connection is successful !!!!");     

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
    }
    
    public void View_all_Table(String Table)
    {
        
    }
    
}
