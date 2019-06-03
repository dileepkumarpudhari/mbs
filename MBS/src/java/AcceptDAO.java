
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
public class AcceptDAO {
    private String email;
    
    public AcceptDAO(){
        
    }
    
    public String retriveAndSendMail(String mail)
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
                System.out.println("Sending mail");
                sde.sendMail(rs.getString(2), rs.getString(9) , rs.getString(8), rs.getString(3));
                System.out.println("Sending mail received");
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
