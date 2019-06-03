<%-- 
    Document   : nremployees
    Created on : 18-Nov-2018, 8:11:54 PM
    Author     : DileepKumar
--%>

<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
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
    <script type="text/javascript">
        function rejectUser(fno) {
            
           document.getElementById('frm'+fno).ruser.value='reject';
           document.getElementById('frm'+fno).submit();
        }
    </script>   
        
  </head>
  <body>
    <header>
      <div class="container">
        <div id="branding"> 
            <a href="ManagerHome.jsp" > <img src="img/MBSlogo1.png"/> </a>
        </div>
        <nav>
          <ul>
            
            <li class="current"><a href="ManageEmployees.jsp" class="blackbtn" title="ManageEmployeeDescription">Manage Employees</a></li>
            <li><a href="addProducts.jsp" class="blackbtn" title="AddProductsDescription">Add Products</a></li>
            <li><a href="PayslipsManagement.jsp" class="blackbtn" title="PayslipsDescription"> Pay slip's Management </a> </li>
            <li> Hello, <%= user[1] %> <%=user[2]%> <br>
            <a href="logout.jsp" style="color: red;"> Logout </a>  </li>
			
            
          </ul>
        </nav>
      </div>
    </header>

    <section id="showcase">
      <div class="container">
          <br>
          <a href="ManageEmployees.jsp" class="blackbtnreg" style="align:left; text-decoration:none" align="left" > Back </a>
          <h4> Newly Registered Employees </h4>
          <br>
          
          <table border="1" align="center">
              <tr>
                  <td> S.No </td>
                  <td> Email ID </td>
                  <td> First Name </td>
                  <td> Last Name </td>
                  <td> Gender </td>
                  <td> Status </td>
              </tr>
          
          
          <%
            
            PreparedStatement ps= null;
            ResultSet rs=null;
            

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbs", "Dileep", "Dimpu@13");
                String query= "select * from account where active=0";
                ps=conn.prepareStatement(query);
                rs=ps.executeQuery();
                int i=1;
               
                while(rs.next())
                {
                   
                   String sno= Integer.toString(i);
                 %>  
                 <tr> 
                     <td> <%= sno %> </td>
                     <td> <%= rs.getString(2) %> </td>
                     <td> <%= rs.getString(3) %> </td>
                     <td> <%= rs.getString(4) %> </td>
                     <td> <%= rs.getString(5) %> </td>
                     <td> <form id="frm<%= i %>" name="frm<%= i %>" action="SendMailServlet" method="post">
                             <input type="hidden" id="email" value= "<%=rs.getString(2) %>" name="email">
                             <input type="hidden" id="ruser" name="ruser" value="accept">
                              <a href="#" class="blackbtn" onClick="javascript:document.frm<%= i %>.submit();"> Accept </a> &nbsp;
                             <a href="javascript:rejectUser('<%= i %>');" class="blackbtnreg"> Reject </a>
                          </form> 
                    </td>
                 </tr>
                    <%
                  i++;
                }
            }
            catch(Exception e)
            {
               System.out.println("Error in  ") ;
            }

          %>
          </table>   
          
      </div>
          
    </section>

   

    

    <footer>
      <span class="center">   BMS, Copyright &copy; 2018  </span>          <span class="right"> Designed by, Dileep Kumar </span>
    </footer>
  </body>
</html>
