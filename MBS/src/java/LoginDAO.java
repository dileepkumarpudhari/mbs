
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DileepKumar
 */
public class LoginDAO {
    
    
    
   
    
   
   public String[] checkLoginUser(LoginBean lb)
   {
       Connection conn= JavaConnect.connectDB();
       
     
       String email= lb.getEmail();
       String password= lb.getPassword();
       
       try
       {
           String query= "select * from account where email=? and password=? and active=1";
           PreparedStatement ps= conn.prepareStatement(query);
           
           
           ps.setString(1,email);
           ps.setString(2,password);
           
           ResultSet rs = ps.executeQuery();
           
           while(rs.next())
           {
               String dbemail= rs.getString("email");
               String dbpassword= rs.getString("password");
               
               String firstname = rs.getString("fname");
               String lastname= rs.getString("lname");
               
               String[] success= new String[10];
               success[0]="success";
               success[1]=firstname;
               success[2]=lastname;
               success[3]=email;
               
               String[] error= {"error"};
               
               if(dbemail.equalsIgnoreCase(email) && dbpassword.equalsIgnoreCase(dbpassword))
               {
                   return success;
               }
               return error;
           }
           
          
       }
       catch(Exception e)
       {
           System.out.println("From Login DAO Login User:" +e);
       }
       String[] error= {"error"};
       return error;
   }
   
   
   public String[] checkLoginManager(LoginBean lb)
   {
       Connection conn= JavaConnect.connectDB();
       
       String role= lb.getRole();
       String email= lb.getEmail();
       String password= lb.getPassword();
       
       try
       {
           String query= "select * from account where email=? and password=? and active=1 and role='"+role+"'";
           PreparedStatement ps= conn.prepareStatement(query);
           
           
           ps.setString(1,email);
           ps.setString(2,password);
           //ps.setString(3,role);
           
           ResultSet rs = ps.executeQuery();
           
           while(rs.next())
           {
               //String dbrole= rs.getString("role");
               String dbemail= rs.getString("email");
               String dbpassword= rs.getString("password");
               String firstname = rs.getString("fname");
               String lastname= rs.getString("lname");
               
               String[] success= new String[10];
               success[0]="success";
               success[1]=firstname;
               success[2]=lastname;
               success[3]=email;
               String[] error= {"error"};
               
               if(dbemail.equalsIgnoreCase(email) && dbpassword.equalsIgnoreCase(dbpassword))
               {
                   return success;
               }
               return error;
           }
           
          
       }
       catch(Exception e)
       {
           System.out.println("From Login DAO manager:" +e);
       }
       String[] error= {"error"};
       return error;
   }
}
