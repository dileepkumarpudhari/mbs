
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DileepKumar
 */

import java.sql.*;
import javax.swing.JOptionPane;


public class JavaConnect {
    
    Connection conn=null;
    
    public static Connection connectDB()
    {
        try
        {
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbs", "Dileep", "Dimpu@13");
            return conn;
          
        }
        
        catch(Exception e)
        {
            System.out.println("From JavaConnect"+ e);
            return null;
        }
    }
    
    
}


