<%-- 
    Document   : EmployeePaylsip
    Created on : 25-Nov-2018, 1:11:49 PM
    Author     : DileepKumar
--%>

<%@page import="java.sql.Time"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <% 
         response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
         response.setHeader("Pragma", "no-cache");
         response.setDateHeader("Expires", 0);
        
 %>
<%
 session = request.getSession();
 String[] user = new String[10];
 
 if(session.getAttribute("session_user")==null || session.getAttribute("session_user")=="" || session.getAttribute("session_user").equals(""))
 {
     response.sendRedirect("login.html");
 }
 else{
 user = (String[])session.getAttribute("session_user");  
 }
 %>
<!DOCTYPE html>
<html>
    <head>
    <meta http-equiv="refresh" content="3600;url=signout.jsp">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width">
    <meta name="description" content="Affordable and professional Management and Billing System">
	  <meta name="keywords" content="Affordable and professional Management Billing System">
  	<meta name="author" content="Dileep Kumar">
    <title>Management and Billing System</title>
    <link rel="stylesheet" href="./css/style.css">
  </head>
  <body>
    <header>
      <div class="container">
        <div id="branding"> 
            <a href="EmployeeHomeScreen.jsp" > <img src="img/MBSlogo1.png"/> </a>
        </div>
        <nav>
          <ul>
            
            
            <li><a href="Billing.jsp" class="blackbtn" title="BillingDescription">Billing</a></li>
            <li class="current"><a href="EmployeePaylsip.jsp" class="blackbtn" title="Payslips"> Pay slip's </a> </li>
            <li> Hello, <%= user[1] %> <%=user[2]%> </li>
            <li> <a href="logout.jsp" style="color: red;"> Logout </a> </li>
			
            
          </ul>
        </nav>
      </div>
    </header>

    <section id="showcase">
      <div class="container">
          <h5> In Payslip Management </h5>
          <form action="EmployeePaySlipServlet" method="post">
           
           From Date: <input type="date" name="fromday" value="<%= (String)request.getAttribute("fday") %>" required=""> &nbsp;  &nbsp;
           To Date: <input type="date" name="today" value="<%= (String)request.getAttribute("tday") %>" required="">
           &nbsp; &nbsp; &nbsp; &nbsp; 
           <input type="submit" name="submit" style="background-color: #9999ff; width: 100px; border: 0; color: white; border-radius: 4px; padding: 4px 5px;" value="Trigger">
           
           </form>
          <br>
            <br>
    <% String trig = (String)request.getAttribute("succ");
    System.out.println("Printing Success in EmployeePayslip"+ trig);

        if(trig=="succ")
            {
            %>
        <div id="tablediv1" align="center">
            
            <table border="1" >  
            <tr>  
            <th>S.no</th>  
            <th>Clockin</th>  
            <th>Clockout</th> 
            <th>Hrs Worked</th> 
            </tr> 

            <c:forEach var="row" items="${rows1}">  
            <tr>
            <td><c:out value="${row.ID}"/></td>  
            <td><c:out value="${row.clockin}"/></td>  
            <td><c:out value="${row.clockout}"/></td>  
            <td><c:out value="${row.hrsworked}"/></td> 
            </tr>  
            </c:forEach>  

            </table>              
                 
                 
                
            <% String totalhours= (String)request.getAttribute("totalhours");   %>  
            <% String seconds= (String)request.getAttribute("seconds"); 
               String payment= (String)request.getAttribute("pay");
               int sec=0; 
               float pay=0;
               try{
                    sec= Integer.parseInt(seconds);
                    pay= Float.parseFloat(payment);
                 }
               catch(NumberFormatException e)
                 {
                       System.out.println("error in employee number format "+ e);
                 }
                   
                float min = (float) sec/60 ;
                float payh= pay/60;
                float tpay= payh*min;
                

             %>
             <br><br>
             <span class='blackbtnreg' style="font-weight: bold;"> Total Hours:   <%= totalhours %> </span> &nbsp; <span class='blackbtnreg' style="font-weight: bold;">Total Pay: $<%= tpay %> </span> 
                  
        </div>
                   <% } %>
      </div>
    </section>

   

    

    <footer>
      <span class="center">   BMS, Copyright &copy; 2018  </span>          <span class="right"> Designed by, Dileep Kumar </span> 
      
    </footer>
           
  </body>
</html>

