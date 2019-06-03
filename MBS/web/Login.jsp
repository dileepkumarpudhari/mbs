<%-- 
    Document   : Login
    Created on : 1-Dec-2018, 4:51:27 PM
    Author     : DileepKumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
        <meta name="description" content="Affordable and professional Management and Billing System">
        <meta name="keywords" content="Affordable and professional Management Billing System">
  	<meta name="author" content="Dileep Kumar">
    <title>Management and Billing System</title>
    <link rel="stylesheet" href="./css/style.css">
	<link rel="stylesheet" href="./css/Logincss.css">
	
  </head>
  <body>
    <header>
      <div class="container">
        <div id="branding"> 
            <a href="HomeScreen.html" > <img src="img/MBSlogo1.png"/> </a>
        </div>
        <nav>
          <ul>
            <li><a href="HomeScreen.html" class="blackbtn" title="HomeDescription">Home</a></li>
            <li><a href="about.html" class="blackbtn" title="aboutDescription">About</a></li>
            <li><a href="Services.html" class="blackbtn" title="ServicesDescription">Services</a></li>
            <li class="current"><a href="Login.jsp" class="blackbtn" title="LoginDescription"> SignIn </a> </li>
	    <li><a href="Register.jsp" class="blackbtnreg" title="RegisterDescription"> Not a Member ? </a></li>
			
            
          </ul>
        </nav>
      </div>
    </header>

    <section id="showcase">
     <div class="container">
	     <div class="box">
		    <h2> Login </h2>
			<form autocomplete="off" action="LoginServlet" method="post">
			
			        <div class= "dropdownbox">
					
					  <select id="roles" name="roles" required="">
							<option value="Employee">Employee</option>
							<option value="Manager">Manager</option>
							
					  </select>
					 <br>
					 <br>
					</div>
					<div class= "inputBox">
					  <input type="text" name="email" required="" >
					  <label> Email ID </label>
					</div>
					<div class= "inputBox">
					  <input type="password" name="password" required="">
					  <label> Password </label>
					</div>
					
					<input type="submit" name="submit" value="Submit">
			</form>
                    <% String re = (String) request.getAttribute("res"); 
                if(re =="error"){
                    %><br><span color="red"> Invalid Username and Password </span>  <% } %>
		</div>
             
         
    </div>  
    </section>
    <footer>
      <span class="center">   BMS, Copyright &copy; 2018  </span>          <span class="right"> Designed by, Dileep Kumar </span> 
    </footer>
  </body>
</html>

