/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DileepKumar
 */
@WebServlet(urlPatterns = {"/PayslipServlet"})
public class PayslipServlet extends HttpServlet {

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
        try  {
            PrintWriter out = response.getWriter();
            String fname= null;
            String email=request.getParameter("dropdfname");
            request.setAttribute("email", email);
            String fromday= request.getParameter("fromday");
            String today= request.getParameter("today");
            
            request.setAttribute("fromdate", fromday);
            request.setAttribute("todate", today);
            
//            java.util.Date fromday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fromday"));
//            java.util.Date today = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("today"));

    if(request.getParameter("submit")!=null)
    {
           PreparedStatement ps1;
           ResultSet rs1;
           String query2= "Select * from account where email='"+email +"'";
           ps1=JavaConnect.connectDB().prepareStatement(query2);
           rs1=ps1.executeQuery();
           String pay=null;
           while(rs1.next())
           {
               fname= rs1.getString(3);
               pay= rs1.getString(10);
               request.setAttribute("pay", pay);
               request.setAttribute("fname", fname);
           }
           
           PrintWriter pfile= new PrintWriter("C:\\Users\\dilee\\Documents\\NetBeansProjects\\MBS\\web\\Payslips\\"+fname+".txt");
           pfile.println();
           pfile.println();
           pfile.println();
           //pfile.println("----------------------------------------------------------------------------------------------------------------------------------");        
           pfile.println("Management and Billing System : Paylslip "+ "for the period "+ fromday + " to "+ today);
           pfile.println("----------------------------------------------------------------------------------------------------------------------------------");
           pfile.println();
           pfile.println();
           
           pfile.println("Name: "+ fname);
           pfile.println();
           pfile.println("Paylsip generated for the period, From Date: "+ fromday + "  ,  "+ "To Date : " + today);
           
           pfile.println("");
           pfile.println("----------------------------------------------------------------------------------------------------------------------------------");
           pfile.println("");
           
           pfile.println("S.no "+"          ClockIn Time         "+ "             ClockOut Time           "+ "Total Time Worked             ");
           

            PreparedStatement ps;
            ResultSet rs;
            String query= "select * from workinghours where Userid='"+email+"' and (DATE(clockin) between '"+fromday+"' and '"+today+"') and (DATE(clockout) between '"+fromday+"' and '"+today+"')";
            System.out.println("Query for printing the table \n" +query);
            ps=JavaConnect.connectDB().prepareStatement(query);
            rs=ps.executeQuery();
            int i=1;
            
            ArrayList<WorkHoursBean> workh = new ArrayList<>();
            
            while(rs.next())
            {
                 WorkHoursBean wh= new WorkHoursBean();
                 wh.setID(i);
                
                 wh.setClockin(rs.getTimestamp(3));
                 wh.setClockout(rs.getTimestamp(4));
                 wh.setHrsworked(rs.getTime(5));
                 
                 pfile.println(" "+i +"          "+rs.getTimestamp(3)+ "              " +rs.getTimestamp(4)+ "          "+wh.getHrsworked()+"  ");
                 
                 System.out.println(rs.getTime(5));
                 
                 workh.add(wh);
                 
//                 request.setAttribute("rows", workh);
//                 RequestDispatcher rd= request.getRequestDispatcher("PayslipsManagement.jsp");
//                 rd.forward(request, response);
                 i++;
            }
            request.setAttribute("rows", workh);
            
            
            ps.close();
            rs.close();
            
            pfile.println("");
            pfile.println("******************************************************************************************************************************");
            
            
            PreparedStatement pst;
            ResultSet rst;
            
            
            String query1= "Select SEC_TO_TIME(SUM(TIME_TO_SEC(Hrsworked))) AS total_time, SUM(TIME_TO_SEC(Hrsworked)) As total_sec FROM workinghours \n" +
                            "where Userid='"+email+"'  and (DATE(clockin) between '"+fromday+"' and '"+today+"') and (DATE(clockout) between '"+fromday+"' and '"+today+"') \n" ;
            
            System.out.println("Query printing the total Hours \n" +query1);
            pst=JavaConnect.connectDB().prepareStatement(query1);
            rst=pst.executeQuery();
          //  ArrayList<String> totalhrs = new ArrayList<String>();

            String totalhours= null;
            String seconds= null;
            while(rst.next())
            {
                     
                totalhours= rst.getString(1);
                seconds= rst.getString(2);
                request.setAttribute("totalhours", totalhours);
                request.setAttribute("seconds", seconds);
                     
                   
           }
            
           
                  int sec= Integer.parseInt(seconds);
                  float pay1= Float.parseFloat(pay);
                  
                   
                   
                   float min = (float) sec/60 ;
                   float payh= pay1/60;
                   float tpay= payh*min; 
            
                   
                pfile.println("******************************************************************************************************************************");
                pfile.println("Total Hours: "+totalhours);
                pfile.println("Pay Rate: $"+ pay1);
                pfile.println("----------------------------------------------------------------------------------------------------------------------------------");
                pfile.println("Payment during the period "+fromday+" to "+ today +" is $"+tpay);
                pfile.println("----------------------------------------------------------------------------------------------------------------------------------");
                
                   
           pst.close();
           rst.close();
           
          pfile.close();
           String success= "success";
           request.setAttribute("success", success);
           RequestDispatcher rd= request.getRequestDispatcher("PayslipsManagement.jsp");
           rd.include(request, response);   
                 
    }  // Trigger if ends            
    
    else if(request.getParameter("sendmail")!=null)
    {
       PreparedStatement ps1;
       ResultSet rs1;
       String query2= "Select * from account where email='"+email +"'";
       ps1=JavaConnect.connectDB().prepareStatement(query2);
       rs1=ps1.executeQuery();
       String pay=null;
           while(rs1.next())
           {
               fname= rs1.getString(3);
               
               SendEmail se= new SendEmail();
               String result=se.sendPaySlip(email, fname);
               
               if(result=="success")
               {
                    String mailsent="mailsent";
                    request.setAttribute("sentmail",mailsent);
                    RequestDispatcher rd= request.getRequestDispatcher("PayslipsManagement.jsp");
                    rd.include(request, response);
                  // response.sendRedirect("PayslipsManagement.jsp");
               }
               
           } 
    }


        }
        catch(Exception e)
        {
            System.out.println("Error in PayslipServlet "+e);
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
