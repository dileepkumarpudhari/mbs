<%-- 
    Document   : Register
    Created on : 1-Dec-2018, 4:57:24 PM
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
	<link rel="stylesheet" href="./css/reegistercss.css">
	
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
            <li><a href="Login.html" class="blackbtn" title="LoginDescription"> SignIn </a> </li>
			<li class="current"><a href="Register.jsp" class="blackbtnreg" title="RegisterDescription"> Not a Member ? </a></li>
			
            
          </ul>
        </nav>
      </div>
    </header>

    <section id="showcaseregister">
     <div class="container">
	     <div class="box">
                 <% String re = (String) request.getAttribute("resalr"); 
                if(re =="already"){
                    %><br> <span color="red"> Username already exists </span> <% } %>
		    <h2> Register </h2>
			<form action="RegisterServlet" autocomplete="off" method="post">
			
			        <div class= "dropdownbox">
					
					  <select id="roles" name="roles" required="">
							<option value="Employee">Employee</option>
							<option value="Manager">Manager</option>
							
					  </select>
					 <br>
					 <br>
					</div>
					<div class= "inputBox">
					  <input type="email" name="emailid" required="" >
					  <label> Email ID </label>
					</div>
					<div class= "inputBox">
					  <input type="text" name="firstname" required="" autocomplete="off" >
					  <label> First Name </label>
					</div>
					<div class= "inputBox">
					  <input type="text" name="lastname" required="" >
					  <label> Last Name </label>
					</div>
					<div class= "radioGender" >
					<label>Gender: </label> &nbsp;&nbsp;
					<label class="radioContainer">Female
						<input type="radio" name="gender" value="Female">
						<span class="circle"> </span> &nbsp; &nbsp;
					</label>
					<label class="radioContainer">Male
						<input type="radio" name="gender" value="Male">
						<span class="circle"> </span>
					</label> <br>
					</div>
                            
					<div class= "inputBox">
					<label>Date of Birth:  </label> <br>
					  <input type="date" name="bday" required="" >
					  <label> </label>
					</div>
					
					
					<input type="submit" name="submit" value="Register">
			</form>
                    
		</div>  
    </div>  
    </section>
    <footer>
      <span class="center">   BMS, Copyright &copy; 2018  </span>          <span class="right"> Designed by, Dileep Kumar </span> 
    </footer>
  </body>
</html>

