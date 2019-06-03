
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DileepKumar
 */
public class RegisterDAO {
    
    public RegisterDAO()
    {
        
    }
    
    public boolean checkUsername(String Username)
    {
        PreparedStatement ps;
        ResultSet rs;
        boolean checkuser=false;
        String Query = "Select * from account where email = ?";
        try {
        ps = JavaConnect.connectDB().prepareStatement(Query);
        ps.setString(1, Username);
        
        
        rs=ps.executeQuery();
        
        if(rs.next())
        {
            checkuser=true;
        }
        
        } catch (SQLException ex) {
           System.out.println("error in Register DAO, Check User"+ ex);
        }
        
        return checkuser;
        
    }
    
    
    public String RegisterUser(RegisterBean rb)
    {
        String role= rb.getRole();
        String mail= rb.getMail();
        String fname= rb.getFname();
        String lname= rb.getLname();
        java.util.Date dob= rb.getDob();
        String gender= rb.getGender();
        String password= rb.getPassword();
        String hash= rb.getHash();
        
        Connection conn= JavaConnect.connectDB();
        
        
        if(checkUsername(mail))
        {
            return "already";   
        }
        
        else
        {
        try{
            String query = "insert into account(role,email, fname, lname,gender, dob, hash, password) values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps= conn.prepareStatement(query);
            java.sql.Date sqldate= new java.sql.Date(dob.getTime());
            
            
            ps.setString(1, role);
            ps.setString(2, mail);
            ps.setString(3, fname);
            ps.setString(4, lname);
            ps.setString(5, gender);
            ps.setDate(6, sqldate);
            
            ps.setString(7, hash);
            ps.setString(8, password);
            
            int i= ps.executeUpdate();
            
            if(i!=0)
            {
                return "success";
            }
            
        }
        catch(Exception e)
        {
            System.out.println("Exception in Register DAo"+e);
        }
        }
        
        
        return "error";
    }
    
    
}
