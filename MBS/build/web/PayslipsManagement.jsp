<%-- 
    Document   : PayslipsManagement
    Created on : 14-Nov-2018, 1:36:06 PM
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
    <script lang="javascript">
   function ShowDiv(stat) {
       alert(stat);
    var x = document.getElementById("tablediv");
    if (stat=="success") 
        x.style.display = "block";
    else
        x.style.display = "none";
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
            <li><a href="addProducts.jsp" class="blackbtn" title="AddProductsDescription">Add Products</a></li>
            <li class="current"><a href="PayslipsManagement.jsp" class="blackbtn" title="PayslipsDescription"> Pay slip's Management </a> </li>
            <li> Hello, <%= user[1] %> <%=user[2]%> <br>
            <a href="logout.jsp" style="color: red;"> Logout </a>  </li>
			
            
          </ul>
        </nav>
      </div>
    </header>

    <section id="showcase">
        <div class="container">
      <div>
          <br>
          <h4> Payslip's Management </h4>
          <form action="PayslipServlet" method="post">
              <br>
           <%
            
            PreparedStatement ps= null;
            ResultSet rs=null;
            

            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbs", "Dileep", "Dimpu@13");
                String query= "select * from account where active=1";
                ps=conn.prepareStatement(query);
                rs=ps.executeQuery();
                int i=1;
               %>
              Employee Name: <select id="fnamed" name="dropdfname"> <%
                while(rs.next())
                { %>
                <option value="<%=rs.getString(2) %>" <% if(((String)request.getAttribute("email"))!=null) if(((String)request.getAttribute("email")).equals(rs.getString(2))) {  %>selected="selected" <% } %>><%=rs.getString(2) %> </option>
                <%  i++;
                    }  %>
		</select>  &nbsp; &nbsp;
                
                <%               
            }    
                catch (Exception e)
                    {
                     System.out.println("Error in Payslip Dropdown Management"+e);
                    }
                 %>
                 
                 
                 
           From Date: <input type="date" name="fromday" value="<%= (String)request.getAttribute("fromdate") %>" required=""> &nbsp;  &nbsp;
           To Date: <input type="date" name="today" value="<%= (String)request.getAttribute("todate") %>" required="">
           &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
           <input type="submit" class="blackbtnman" style="width:135px;" name="submit" value="Trigger">
           <input type="submit" class="blackbtnupd" style="width:135px;" name="sendmail" value="Send Mail">
           
         
           </form>
    
           <br>
           <br>
           <br>
           
      <!--           <table class="tborder">
              <tr style="background-color: orange; color:black; font-weight: bold;">
                  <td> S.No </td>
                  <td> Clock in </td>
                  <td> Clock Out </td>
                  <td> Hours Worked </td>
                  
              </tr>   
          
          
          <%
//            String username= request.getParameter("dropdfname"); 
//            PreparedStatement pst= null;
//            ResultSet rst=null;
//            
//
//            try
//            {
//                Class.forName("com.mysql.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbs", "Dileep", "Dimpu@13");
//                System.out.println("Before");
//                String fromday= request.getParameter("fromday");
//                System.out.println("fftime "+fromday);
//               // SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//                //Date parsedDate = dateFormat.parse(fromday);
//                //Timestamp frotimestamp = new java.sql.Timestamp(parsedDate.getTime());
//                System.out.println("After");
//                String today= request.getParameter("today");
//                //SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//                //Date parsedDate1 = dateFormat1.parse(fromday);/
//                //Timestamp totimestamp = new java.sql.Timestamp(parsedDate1.getTime());
//                
//                String query= "select * from workinghours where Userid='"+username+"' and (DATE(clockin) between '"+fromday+"' and '"+today+"') and (DATE(clockout) between '"+fromday+"' and '"+today+"')";
//                System.out.println(query);
//                ps=conn.prepareStatement(query);
//                rs=ps.executeQuery();
//                int i=1;
//                
//                
//                
//                while(rs.next())
//                {
//                   
//                   String sno= Integer.toString(i);
//                 
//                  i++;
//                }
//            }
//            catch(Exception e)
//            {
//               System.out.println("Error in Printing Table "+e) ;
//            }

          %>
          </table>  -->
                 
                     
                     
          <%--<c:if test="${list not empty }">
    <table>
        <c:forEach items="${list}" var="record">
            <tr>
                <td>${record.id }</td>
                <td>${record.firstName }</td>
                <td>${record.lastName }</td>
                <td>${record.subject }</td>
                <td>${record.year }</td>
            </tr>
        </c:forEach>
    </table>
</c:if>      --%>
 
       </div> 
          
<% String trigger = (String)request.getAttribute("success");
String smail = (String)request.getAttribute("sentmail");
String eemail = (String)request.getAttribute("email");
System.out.println("smail priting value"+smail);
if(trigger=="success")
{
%>

 <div id="tablediv" align="center">
 <table border="1">  
 <tr>  
 <th>S.no</th>  
 <th>Clockin</th>  
 <th>Clockout</th> 
 <th>Hrs Worked</th> 
 </tr> 


 <c:forEach var="row" items="${rows}">  
 <tr>
 <td><c:out value="${row.ID}"/></td>  
 <td><c:out value="${row.clockin}"/></td>  
 <td><c:out value="${row.clockout}"/></td>  
 <td><c:out value="${row.hrsworked}"/></td> 
 </tr>  
 </c:forEach>  
             
                
   
          
 <%--        
     <c:forEach items="${requestScope.rows}" var="element">
      <tr>
    
        <td>${element.getID}</td>
        <% System.out.println("${element.getID}"); %>
        <td>${element.getClockin}</td>
        <td>${element.getClockout}</td>
        <td>${element.getHrsworked}</td>
    
  </tr>
     </c:forEach>  
                   --%>

   </table>              
                 
                 
                
                   <% String totalhours= (String)request.getAttribute("totalhours");   %>  
                   <% String seconds= (String)request.getAttribute("seconds"); 
                        String payment= (String)request.getAttribute("pay");
                        String email = (String)request.getAttribute("email");
                   String fname= (String)request.getAttribute("fname");
                   
                   System.out.println("fff"+fname);
                   System.out.println("mmm"+email);
                   int sec=0; 
                   float pay=0;
                   try{
                       sec= Integer.parseInt(seconds);
                       pay= Float.parseFloat(payment);
                   }
                   catch(NumberFormatException e)
                   {
                       System.out.println("error in number format "+ e);
                   }
                   
                   float min = (float) sec/60 ;
                   float payh= pay/60;
                   float tpay= payh*min;
                   System.out.println(" In Jsp "+totalhours);

                   %>
                 
                   
                  
                   
                 
                   <br>
                   <span class='blackbtnreg' style="font-weight: bold;"> Total Hours:   <%= totalhours %> </span> &nbsp; <span class='blackbtnreg' style="font-weight: bold;">Total Pay: $<%= tpay %> </span> 
                   
                   
                  
     <% } 
      else if(smail=="mailsent")
 { %> Payslips are sent to <%=((String)request.getAttribute("email")) %>

      <%}
     
     %>              
     
  
     
     
 </div>        
                 
                
      
    </section>

   

  </div>

    <footer>
      <span class="center">   BMS, Copyright &copy; 2018  </span>          <span class="right"> Designed by, Dileep Kumar </span> 
    </footer>
           
  </body>
</html>
