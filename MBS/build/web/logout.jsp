<%-- 
    Document   : logout
    Created on : 15-Nov-2018, 7:06:29 PM
    Author     : DileepKumar
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <%
        session = request.getSession();
        String [] user = new String[10];
 
        if(session.getAttribute("session_user")==null || session.getAttribute("session_user")=="" || session.getAttribute("session_user").equals(""))
            {
            response.sendRedirect("Login.jsp");
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
            <a href="ManagerHome.jsp" > <img src="img/MBSlogo1.png"/> </a>
        </div>
        <nav>
          <ul>
            <li><a href="ManageEmployees.jsp" class="blackbtn" title="ManageEmployeeDescription">Manage Employees</a></li>
            <li><a href="addProducts.jsp" class="blackbtn" title="AddProductsDescription">Add Products</a></li>
            <li><a href="PayslipsManagement.jsp" class="blackbtn" title="PayslipsDescription"> Pay slip's Management </a> </li>
            <li> Hello, <%= user[1] %> <%=user[2]%>  </li>
			
            
          </ul>
        </nav>
      </div>
    </header>

    <section id="showcase">
      <div class="container">
        <h1>Logging out</h1>
        <p>Are you sure to Logout <%= user[1] %> <%=user[2]%> </p>
        <% 
         response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
         response.setHeader("Pragma", "no-cache");
         response.setDateHeader("Expires", 0);
        
        %>
        
        
        <a href="signout.jsp"> <button type="submit" >Logout </button></a> 
		
      </div>
    </section>

   

    

    <footer>
      <p>BMS, Copyright &copy; 2018</p>
    </footer>
  </body>
</html>

