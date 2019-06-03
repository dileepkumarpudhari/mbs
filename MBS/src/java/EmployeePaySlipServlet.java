/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author DileepKumar
 */
@WebServlet(urlPatterns = {"/EmployeePaySlipServlet"})
public class EmployeePaySlipServlet extends HttpServlet {

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
            
            String[] user = new String[10];
            
            user = (String[])session.getAttribute("session_user");  
                
            
            String fname= null;
            String email= user[3];
            String fromday= request.getParameter("fromday");
            String today= request.getParameter("today");
            request.setAttribute("fday", fromday);
            request.setAttribute("tday", today);
            
            System.out.println("Email in employee Payslip esrvlet is "+ email);
//            java.util.Date fromday = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fromday"));
//            java.util.Date today = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("today"));


            PreparedStatement ps;
            ResultSet rs;
            String query= "select * from workinghours where Userid='"+email+"' and (DATE(clockin) between '"+fromday+"' and '"+today+"') and (DATE(clockout) between '"+fromday+"' and '"+today+"')";
            System.out.println("Query for printing the table \n" +query);
            ps=JavaConnect.connectDB().prepareStatement(query);
            rs=ps.executeQuery();
            int i=1;
            
            ArrayList<WorkHoursBean> workh1 = new ArrayList<>();
            
            while(rs.next())
            {
                 WorkHoursBean wh1= new WorkHoursBean();
                 wh1.setID(i);
                
                 wh1.setClockin(rs.getTimestamp(3));
                 wh1.setClockout(rs.getTimestamp(4));
                 wh1.setHrsworked(rs.getTime(5));                 
                                
                 workh1.add(wh1);
                 i++;
            }
            request.setAttribute("rows1", workh1);
            
            
            ps.close();
            rs.close();
  
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
            
            pst.close();
            rst.close();
            
                 
                PreparedStatement ps1;
                ResultSet rs1;
                String query2= "Select * from account where email='"+email +"'";
                ps1=JavaConnect.connectDB().prepareStatement(query2);
                System.out.println(query2);
                rs1=ps1.executeQuery();
                String pay=null;
                while(rs1.next())
                    {
                fname= rs1.getString(3);
                pay= rs1.getString(10);
                request.setAttribute("pay", pay);
                
                    }
           
                
                ps1.close();
                rs1.close();
                   
          
           
           
           request.setAttribute("succ", "succ"); 
           RequestDispatcher rd1= request.getRequestDispatcher("EmployeePaylsip.jsp");
           rd1.include(request, response);  
        }
        catch(Exception e)
        {
            System.out.println("Error in Employee Pay Servlet"+e);
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
