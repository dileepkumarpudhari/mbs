<%-- 
    Document   : signout
    Created on : 15-Nov-2018, 7:17:22 PM
    Author     : DileepKumar
--%>

<%@page import="java.sql.Time"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Connection"%>
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sign out page</h1>
        
        <%
            try{
            String id = user[4];
            System.out.println("printing "+id);
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mbs", "Dileep", "Dimpu@13");
            
            PreparedStatement ps;
            String query = "UPDATE workinghours SET clockout=? where ID='"+id+"' ";   
            
            
            ps=conn.prepareStatement(query);
            java.util.Date today1 = new java.util.Date();
            java.sql.Timestamp clockoutt= new java.sql.Timestamp(today1.getTime());
            
            ps.setTimestamp(1, clockoutt);
            System.out.println(clockoutt);
            System.out.println(query);
            int i=ps.executeUpdate();
            
            
            if(i>0)
            {
                System.out.println("clock out inserted");
            }
            
            ps.close();
            
            
            PreparedStatement pst;
            ResultSet rs;
            String query1 = "Select TIMEDIFF(clockout, clockin) from workinghours where ID='"+id+"' " ;
               
            pst=conn.prepareStatement(query1);
            rs=pst.executeQuery();
            
            while(rs.next())
            {
                Timestamp diff= rs.getTimestamp(1);
                
                PreparedStatement pst1;
                String query2 = "UPDATE workinghours SET Hrsworked=? where ID='"+id+"' ";
                  
                pst1=conn.prepareStatement(query2);
                
                pst1.setTimestamp(1, diff);
                
                int j=pst1.executeUpdate();
                if(j>0)
                {
                    System.out.println("Hrs difference is inserted");
                }
                
                
                
            }
            pst.close();
           
            }
            catch(Exception e)
            {
                System.out.println("error in Difference Hours:"+ e);
            }
            
            if(session.getAttribute("session_user")!=null)
            {
       
                session.removeAttribute("session_user");
                request.getSession(false);
                session.setAttribute("session_user", null);
                session.invalidate();
                response.sendRedirect("Login.jsp");
            }
        
        %>
        
    </body>
</html>
