
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DileepKumar
 */
public class HoursDAO {
    
    
    public HoursDAO()
    {    
    }
    
    private static java.sql.Timestamp getCurrentTimeStamp()
    {

	java.util.Date today = new java.util.Date();
	return new java.sql.Timestamp(today.getTime());

    }
    
    public int clockInHours(LoginBean lb)
    {
        String email=lb.getEmail();
        
        PreparedStatement ps;
        ResultSet rs;
        
        Connection conn= JavaConnect.connectDB();
        try{
            String query = "insert into workinghours(Userid,clockin) values (?,?)";
//            ps= conn.prepareStatement(query);
//            
//            
//            ps.setString(1,email);
//            ps.setTimestamp(2,getCurrentTimeStamp());
//            
//            int i=ps.executeUpdate();
//            
//            if(i>0)
//            {
//                System.out.println("clock in inserted");
//                
//            }

              ps = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
              
              ps.setString(1,email);
              ps.setTimestamp(2,getCurrentTimeStamp());
              
              int numero = ps.executeUpdate();
              if(numero>0)
              {
                  System.out.println("clockin Inserted");
              }

              rs = ps.getGeneratedKeys();
              if (rs.next()){
               
                  int hid=rs.getInt(1);
                  System.out.println("Returning hid is "+hid);
                  return hid;
                }
           
            
              ps.close();
        
        }
        catch(Exception e)
        {
            System.out.println("error in HoursDAO :"+e);
        } return 0;
    } 
}
