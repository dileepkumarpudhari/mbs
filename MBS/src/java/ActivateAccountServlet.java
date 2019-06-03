/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DileepKumar
 */
@WebServlet(urlPatterns = {"/ActivateAccountServlet"})
public class ActivateAccountServlet extends HttpServlet {

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
        /*try{
            
            
            
            
        }
        catch(Exception e)
        {
            System.out.println("Error in ActivateAccountServlet"+e);
        } */
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
        String email= request.getParameter("key1");
        String hash= request.getParameter("key2");
        
        Connection conn= JavaConnect.connectDB();
        try{
            PreparedStatement ps;
            ResultSet rs;
            String query= "Select email, hash, active from account where email=? and hash=? and active='0'";
            ps= conn.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, hash);
            
            rs = ps.executeQuery();
            if(rs.next()){
                String query1="update account set active='1' where email=? and hash=?";
                PreparedStatement pst= conn.prepareStatement(query1);
                pst.setString(1, email);
                pst.setString(2, hash);
                int i= pst.executeUpdate();
                if(i==1)
                {
                    response.sendRedirect("Login.jsp");
                }
                else
                {
                    response.sendRedirect("Register.jsp");
                }
            }
           
            
        }
        catch(Exception e)
        {
            System.out.println("Error in Doget Activate Account "+ e);
        }
        
        
        
        
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
