
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
public class UpdateDAO {
    
    public UpdateDAO()
    {
        
    }
    
    public String updateRecord(UpdateBean ub)
    {
        PreparedStatement ps;
        ResultSet rs;
        float prize1= ub.getUp();
        int quant= ub.getUq();
        int id= ub.getId();
       String id1 = String.valueOf(id);
        System.out.println(quant);
        System.out.println(prize1);
        
        
        try{
            String query= "update products set Prize=?, Quantity=? where ID='"+id1+"'";
            System.out.println(query);
            
            ps= JavaConnect.connectDB().prepareStatement(query);
            ps.setFloat(1, prize1);
            ps.setInt(2, quant);
            
            int i=ps.executeUpdate();
            if(i==1)
            {
                return "success";
            }
            
        }
        catch(Exception e)
        {
            System.out.println("Error in Update DAO "+e);
        }
        
        
        
        
        
        return "error";
    }
    
    
    
    public String deleteRecord(UpdateBean ub)
    {
        float prize1= ub.getUp();
        int quant= ub.getUq();
        int id= ub.getId();
       String id1 = String.valueOf(id);
        
        PreparedStatement ps;
        ResultSet rs;
        try{
            
            String query1="delete from products where ID='"+id1+"'"; 
            ps=JavaConnect.connectDB().prepareStatement(query1);
            int i=ps.executeUpdate();
            if(i>0)
            {
                
                return "success";
            }
            
            
        }
        catch(Exception e)
        {
            System.out.println("Error in deleting from update jsp"+e);
        }
        
        
        return "error";
    }
    
}
