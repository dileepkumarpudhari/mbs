/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author DileepKumar
 */
@WebServlet(urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {

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
        try {
            String role = request.getParameter("roles");
            String email= request.getParameter("emailid");
            String fname= request.getParameter("firstname");
            String lname= request.getParameter("lastname");
            String gender= request.getParameter("gender");
            
//            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
//            String dob= dateformat.format(request.getParameter("bday"));
            java.util.Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bday")); 


            //Generating Password
            String password;
            Random rand= new Random();
            int r=rand.nextInt(999999);
            password= Integer.toString(r);
            
            
            
            
            //Generating Hash
            String hash;
            Random rand1= new Random();
            rand1.nextInt(999999);
            hash= DigestUtils.md5Hex(""+rand1);
            
            
            // Invoking RegsiterBean class
            RegisterBean rb = new RegisterBean();
            rb.setRole(role);
            rb.setMail(email);
            rb.setFname(fname);
            rb.setLname(lname);
            rb.setGender(gender);
            rb.setDob(dob);
            rb.setPassword(password);
            rb.setHash(hash);
            
            //Creating a DAO file
            
            RegisterDAO rd = new RegisterDAO();
            String result=rd.RegisterUser(rb);
            if(result=="success")
            {
                response.sendRedirect("RegisterSuccess.jsp");
            }
            else if(result=="already")
            {
                String res= "already";
                request.setAttribute("resalr", res);
                RequestDispatcher rdis = request.getRequestDispatcher("Register.jsp");
                rdis.forward(request, response);
            }
            else
            {
                response.sendRedirect("Register.jsp");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error in Register Servlet:" +e);
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
