/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
           
            String role= request.getParameter("roles");
            String email= request.getParameter("email");
            String password= request.getParameter("password");
            
            
            //Login bean
            LoginBean lb = new LoginBean();
            lb.setRole(role);
            lb.setEmail(email);
            lb.setPassword(password);
            
           
            //Login DAO
            //LoginDAO ld = new LoginDAO();
            //String result = ld.checkLoginUser(lb);
            
            if(role.equalsIgnoreCase("manager"))
            {
                LoginDAO ld = new LoginDAO();
                String[] result= new String[10];
                result= ld.checkLoginManager(lb);
                
                if(result[0].equalsIgnoreCase("success"))
                {
//                HoursDAO hd = new HoursDAO();
//                hd.clockInHours(lb);
//                String id1= Integer.toString(id);
//                result[4]=id1;
                HttpSession session = request.getSession(true);
                session.setAttribute("session_user", result);
                RequestDispatcher rd = request.getRequestDispatcher("ManagerHome.jsp");
                rd.forward(request, response);
                }
                
                else
            {
                String res= "error";
                request.setAttribute("res", res);
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);
            }
            }
            
           
            else if(role.equalsIgnoreCase("employee"))
            {
                LoginDAO ld = new LoginDAO();
                String[] result= new String[10];
                result= ld.checkLoginUser(lb);
                
                if(result[0].equalsIgnoreCase("success"))
                {
                HoursDAO hd = new HoursDAO();
                    int idcl = hd.clockInHours(lb);
                    System.out.println(idcl);
                String idc2= Integer.toString(idcl);
                 result[4]=idc2;
                    
                HttpSession session = request.getSession(true);
                session.setAttribute("session_user", result);
                RequestDispatcher rd = request.getRequestDispatcher("EmployeeHomeScreen.jsp");
                rd.forward(request, response); 
                }
                
                else
            {
                String res= "error";
                request.setAttribute("res", res);
                RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request, response);
            }
            }
            
            
        }
        catch(Exception e)
        {
            
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
