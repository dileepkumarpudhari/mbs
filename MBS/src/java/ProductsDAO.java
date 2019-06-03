
import java.sql.Connection;
import java.sql.PreparedStatement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DileepKumar
 */
public class ProductsDAO {
    
    public ProductsDAO()
    {
        
    }
    
    public String addProducts(ProductsBean pb)
    {
        String pname = pb.getProductName();
        float prize= pb.getPrize();
        int quantity = pb.getQuantity();
        
        Connection conn= JavaConnect.connectDB();
        try{
            String query = "insert into products(Product, Prize, Quantity) values (?,?,?)";
            PreparedStatement ps= conn.prepareStatement(query);
            ps.setString(1,pname);
            ps.setFloat(2,prize);
            ps.setInt(3, quantity);
            int i= ps.executeUpdate();
            if(i!=0)
            {
                return "success";
            } 
        }
        catch(Exception e)
        {
            System.out.println("Exception in Products DAO"+e);
        }
        
        
        return "error";
    }
    
}
