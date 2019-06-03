<%-- 
    Document   : EmployeeHomeScreen
    Created on : 14-Nov-2018, 1:46:36 PM
    Author     : DileepKumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
         response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
         response.setHeader("Pragma", "no-cache");
         response.setDateHeader("Expires", 0);
        
 %>
<%
 session = request.getSession();
 String [] user = new String[10];
 
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
            <li><a href="EmployeePaylsip.jsp" class="blackbtn" title="Payslip's Description">Payslip's</a></li>
            <li> Hello, <%= user[1] %> <%=user[2]%>  </li>
            <li> <a href="logout.jsp" style="color: red;"> Logout </a> </li>
			
            
          </ul>
        </nav>
      </div>
    </header>

    <section id="showcase">
      <div class="container">
        <h1>Management and Billing System</h1>
        <p>Serious retail <br> made simple</p>
		
		<br>
                <br>
		<p> Welcome,  <%= user[1] %> <%=user[2]%>  </p>
      </div>
    </section>

   

    

    <footer>
      <span class="center">   BMS, Copyright &copy; 2018  </span>          <span class="right"> Designed by, Dileep Kumar </span> 
      
    </footer>
  </body>
</html>
