
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
public class RejectDAO {
    private String email;
    
    public RejectDAO() {
        
    }
    
    public String rejectUser(String mail)
    {
        PreparedStatement ps;
        ResultSet rs;
        email= mail;
        
        Connection conn= JavaConnect.connectDB();
        try{
            String query="select * from account where email='"+email+"' ";
            ps=conn.prepareStatement(query);
            rs=ps.executeQuery();
            if(rs.next())
            {
            SendEmail sde = new SendEmail();
                System.out.println("Sending Reject mail");
                sde.sendRejectMail(rs.getString(2), rs.getString(3));
                System.out.println("Reject email received");
               // return "success";
            }
            
            
            
            String query1="delete from account where email='"+email+"' "; 
            ps=conn.prepareStatement(query1);
            int i=ps.executeUpdate();
            if(i>0)
            {
                System.out.println("Record Deleted");
                return "success";
            }
            
            
        }
        catch(Exception e)
        {
            System.out.println("Exception in AcceptDAo"+e);
        }
        
        
        
        return "error";
    }
}
