/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
/**
 *
 * @author DileepKumar
 */
@WebServlet(urlPatterns = {"/BillingServlet"})
public class BillingServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try{
            
            
            HttpSession session = request.getSession(true);
            String [] user = new String[10];
 
            if(session.getAttribute("session_user")==null || session.getAttribute("session_user")=="" || session.getAttribute("session_user").equals(""))
            {
            response.sendRedirect("Login.jsp");
             }
            else{
         user = (String[])session.getAttribute("session_user"); 
            }
            
            String jsondata= request.getParameter("samplecart1");
            //String json = "{ram,rahul,rani}";
//            String[] cartArray = jsondata.split(",");
////              /* print substrings */
//             String[] itemArray;
//             for (int i = 0; i < cartArray.length; i++){
//              itemArray= cartArray[i].split(",");
//                 for(int j=0; j<itemArray.length; j++)
//                {
//                  System.out.println("Value is "+itemArray[j]);                    }
//            }

            String fname = user[1];

            System.out.println(jsondata);
            
            FileWriter fwriter= new FileWriter("C:\\Users\\dilee\\Documents\\NetBeansProjects\\MBS\\web\\Invoice\\"+fname+"Billing.txt", true);
            PrintWriter ofile = new PrintWriter(fwriter);
                
                
                ofile.println("--------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println(" After print writer");
                java.util.Date today = new java.util.Date();
                ofile.println("Billing Date: "+today);
                ofile.println();
                ofile.println("Billing done by: "+fname);
                
                
                ofile.println();
                ofile.println("--------------------------------------------------------------------------------------------------------------------------------------");
                ofile.println("**************************************************************************************************************************************");
            
                ofile.println(String.format("%20s %20s %20s %20s %20s %20s \r\n", "S.no","Product ID", "ProductName", "Count", "Prize","Total"));
                ofile.println("--------------------------------------------------------------------------------------------------------------------------------------");
                Float carttotal=0f;
                int i=1;
            ProductCart[] respone = new Gson().fromJson(jsondata, ProductCart[].class);
            
            for (ProductCart s : respone) {
                System.out.println("File name: " + s.getName() + "Count:"+ s.getCount()+ " Id is "+s.getId());
                String count1 = s.getCount();
                
                int count2 = Integer.parseInt(count1);
                String carttotals= s.getT();
                carttotal = carttotal+ Float.parseFloat(carttotals);
                
                System.out.println("cart total is "+carttotal);
                
                
                
                ofile.println(String.format("%20s %20s %20s %20s %20s %20s \r\n", i, s.getId(), s.getName(), s.getCount(), s.getPrize(), s.getT()));
                i++;
                ofile.println();
                
                
                PreparedStatement pst;
                ResultSet rst;
                
                String query1= "Select * from products where ID='"+s.getId()+"'";
                pst= JavaConnect.connectDB().prepareStatement(query1);
                rst=pst.executeQuery();
               
                int rquant=0;
                while(rst.next())
                {
                   
                 rquant= rst.getInt(4);
                 
                }
                
                pst.close();
                rst.close();
                int fcount = rquant-count2;
          
//          if(fcount >= 0)
//          {
                PreparedStatement ps;
                ResultSet rs;
                
              
            String query= "update products set Quantity=? where ID='"+s.getId()+"'";
            System.out.println(query);
            
            
            
            ps= JavaConnect.connectDB().prepareStatement(query);
            ps.setInt(1, fcount);
            
            
            ps.executeUpdate();
              
  //        }
          
//          else
//          {
//              request.setAttribute("pname", "s.getName()");
//              request.setAttribute("finish", "finish");
//              request.setAttribute("pquant", rquant);
//              RequestDispatcher rd = request.getRequestDispatcher("Billing.jsp");
//              rd.forward(request, response);
//          }
          
            }
            ofile.println("**************************************************************************************************************************************");
            ofile.println("--------------------------------------------------------------------------------------------------------------------------------------");
            ofile.println("Total is "+carttotal);
            ofile.println("--------------------------------------------------------------------------------------------------------------------------------------");
            ofile.println("**************************************************************************************************************************************");
            ofile.println();
            ofile.println();
            
            ofile.close();
            
            response.sendRedirect("Billing.jsp");
             
          
            
            
            
            
           
        }
        catch(Exception e)
        {
            System.out.println("error in Billing Servlet "+e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
