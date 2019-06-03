<%-- 
    Document   : updateproducts
    Created on : 28-Nov-2018, 4:21:26 PM
    Author     : DileepKumar
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
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
            
            <li><a href="ManageEmployees.jsp" class="blackbtn" title="ManageEmployeeDescription">Manage Employees</a></li>
            <li class="current"><a href="addProducts.jsp" class="blackbtn" title="AddProductsDescription">Add Products</a></li>
            <li><a href="PayslipsManagement.jsp" class="blackbtn" title="PayslipsDescription"> Pay slip's Management </a> </li>
            <li> Hello, <%= user[1] %> <%=user[2]%>  </li>
			
            
          </ul>
        </nav>
      </div>
    </header>

    <section id="showcase">
      <div class="container">
        
          
          
          
          <h4 align=""center> Update Products</h4>
          
          
          
          <table border="1" align="center">
              <tr>
                  <th> S.No </th>
                  <th> Product Name </th>
                  <th> Prize </th>
                  <th> Quantity </th>
                 
              </tr>
          
          
          <%
            
            PreparedStatement ps= null;
            ResultSet rs=null;
            

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbs", "Dileep", "Dimpu@13");
                String query= "select * from products";
                ps=conn.prepareStatement(query);
                rs=ps.executeQuery();
                int i=1;
               
                while(rs.next())
                {
                   
                   String sno= Integer.toString(i);
                 %>  
                 <tr > 
                     <td> <%= sno %> </td>
                     <td> <%= rs.getString(2) %></td>
                     
             
                     <form id="frm<%= i %>" name="frm<%= i %>" action="updateProductsServlet" method="post">
                         <td>   <input type="text" id="prize" name="productprize" value="<%=rs.getString(3) %>"> </td>
                         <td>    <input type="text" id="quantity" name="productquantity" value="<%=rs.getString(4) %>"> </td>
                      <!--       <td> <input type="button" class="blackbtn" onClick="javascript:document.frm<%= i %>.submit();" value="Update">       -->
                             <input type="hidden" id="pid" value= "<%=rs.getString(1) %>" name="pid">
                             <input type="hidden" id="ruser" name="ruser"  value="Update">
                             <td>         <a href="#" style="text-decoration:none" onClick="javascript:document.frm<%= i %>.submit();"> <input type="button" value="+"></a>  &nbsp;
                                 <a  style="text-decoration:none" href="javascript:rejectUser('<%= i %>');" ><input type="button" value="X" > </a>  </td>
                          </form> 
                    
                 </tr>
                    <%
                  i++;
                }
            }
            catch(Exception e)
            {
               System.out.println("Error in UpdateProducts.jsp "+e) ;
            }

          %>
          </table> 
          
          
          
         
          
          
       
      </div>
          <br>
          <br>
          <a href="addProducts.jsp" class="blackbtnreg" style="align:left; text-decoration:none" align="left" > Back </a>
    </section>

   

    

    <footer>
      <span class="center">   BMS, Copyright &copy; 2018  </span>          <span class="right"> Designed by, Dileep Kumar </span> 
    </footer>
  </body>
</html>
