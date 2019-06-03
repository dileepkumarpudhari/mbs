/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DileepKumar
 */
@WebServlet(urlPatterns = {"/updateProductsServlet"})
public class updateProductsServlet extends HttpServlet {

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
            String up = request.getParameter("productprize");
            String uq = request.getParameter("productquantity");
            String res = request.getParameter("ruser");
            String idr = request.getParameter("pid");
            
            int uquantity= Integer.parseInt(uq);
            float uprize= Float.parseFloat(up);
            int id = Integer.parseInt(idr);
            
            System.out.println(" Updated prize "+ uprize);
            System.out.println(" Updated Quantity "+uquantity);
            System.out.println(" result is "+res);
            System.out.println("Id is "+id);
            
            if(res.equals("Update"))
            {
                UpdateBean ub = new UpdateBean();
                ub.setId(id);
                ub.setUp(uprize);
                ub.setUq(uquantity);
                
                
                UpdateDAO ud = new UpdateDAO();
                String outp;
                outp=ud.updateRecord(ub);
                
                if(outp.equalsIgnoreCase("success"))
                {
                    response.sendRedirect("updateproducts.jsp");
                }
                
                if(outp.equalsIgnoreCase("error"))
                {
                    System.out.println("Issue in update DAO");
                }
                
                
                
            }
            
            else if(res.equals("reject"))
            {
               UpdateBean ub = new UpdateBean();
                ub.setId(id);
                ub.setUp(uprize);
                ub.setUq(uquantity);
                
                UpdateDAO ud = new UpdateDAO();
                String outp;
                outp=ud.deleteRecord(ub);
                
                if(outp.equalsIgnoreCase("success"))
                {
                    response.sendRedirect("updateproducts.jsp");
                }
                
                if(outp.equalsIgnoreCase("error"))
                {
                    System.out.println("Issue in update Delete record DAO");
                }
                
                
            }
            
            
        }
        catch(Exception e)
        {
            System.out.println("Error in Update Products Servlet "+e);
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
