<%-- 
    Document   : Billing
    Created on : 25-Nov-2018, 1:28:03 PM
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
    <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
    <link rel="stylesheet" href="./css/stylebilling.css">
  </head>
  <body>
    <header>
      <div class="container">
        <div id="branding"> 
            <a href="EmployeeHomeScreen.jsp" > <img src="img/MBSlogo1.png"/> </a>
        </div>
        <nav>
          <ul>
            <li class="current"><a href="Billing.jsp" class="blackbtn" title="BillingDescription">Billing</a></li>
            <li><a href="EmployeePaylsip.jsp" class="blackbtn" title="Payslip's Description">Payslip's</a></li>
            <li> Hello, <%= user[1] %> <%=user[2]%>  </li>
            <li> <a href="logout.jsp" style="color: red;"> Logout </a> </li>
            
            
			
            
          </ul>
        </nav>
      </div>
    </header>

    <section id="showcase">
      <div class="container">
          
          
      <!--    <div style="height:100%; width:60%; position: fixed; z-index: 1; left:20px; background-color: red; overflow-x: hidden; top:0; padding-top:20px;"  > -->
      <article id="main-col">
          <h4 align="center" class="page-title" style="color:white;">Products</h4>
          <ul id="services" style="height:450px;">
              <li style="height:460px;">
          <% 

          PreparedStatement ps;
          ResultSet rs;
          
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
                   if(i%7==0)
                   { %> <br> <br> <% }
                 %>  
                 <input type="button" class="add-to-cart" name="product<%=rs.getInt(1) %>" data-id="<%=rs.getInt(1)%>" data-name="<%=rs.getString(2)%>" data-prize="<%=rs.getFloat(3)%>" data-quantity="<%=rs.getInt(4)%>" value="<%= rs.getString(2) %>" style="<% if(rs.getInt(4)<= 0) {%> color:red; <% }%>" > &nbsp; &nbsp; &nbsp;                         
                    <% 
                  i++;
                }
            }
            catch(Exception e)
            {
               System.out.println("Error in Billing.jsp "+e) ;
            }
          
          
          
          
          
          %>
              </li>
          </ul>
          </article>
              
              

        <aside id="sidebar">
            
          <div class="dark">
              <% java.util.Date today = new java.util.Date(); %>
              Billing Date : <%=today%>  <br>
                 -------------------------------------------------------------------------
                 MBS, Billed by: <%= user[1] %> <%=user[2]%> <br>
                 -------------------------------------------------------------------------<br>
                 <table> 
                    
                     <tr><td colspan="6" id="show-cart"><table width="100%" >
                
                  
                             </table> </td></tr>
                     <tr><td colspan="6">------------------------------------------------------------------------</td></tr>
                         
            </table> 
                 <div align='center' style="font-weight:bold;"> Total Cost: $<span id="total-cost"> </span></div>  
          </div>
            
            <form action="BillingServlet" method="post" >
               <button id="clearCart"  style="background-color: tomato; border: 0; color: white; border-radius: 4px; padding: 4px 5px;" class="clrbutton" > Clear Cart </button>
               <input type="submit" style="background-color: #9999ff; border: 0; color: white; border-radius: 4px; padding: 4px 5px;" name="payment" value="PAY" >
            <input type="hidden" name="samplecart1" ID="samplecart" >
            </form>
            <br> <br>
           
            
            
        </aside>
                
      </div>
                 
       
                 
    </section>

   

    

    <footer>
      <span class="center">   BMS, Copyright &copy; 2018  </span>          <span class="right"> Designed by, Dileep Kumar </span> 
      
    </footer>
              
              
        <script> 
            
            
            $(".add-to-cart").click(function(event) {
                event.preventDefault();
                var name= $(this).attr("data-name");
                var prize= Number ($(this).attr("data-prize"));
                var id = Number($(this).attr("data-id"));
                var quantity = Number($(this).attr("data-quantity"));
                if(quantity > 0){
                addItemToCart(id, name, prize, 1)
                 }
                displayCart();   
            });
            
            $("#clearCart").click(function(event){
               clearCart(); 
               displayCart();
            });
                  
             function displayCart(){
                 var cartArray = listCart();
                 var output="";j=1;
                 output += "<tr><td>S.no</td><td>Name</td><td>Qty</td><td>Price</td><td>Total</td><td></td></tr><tr><td colspan='6'>-----------------------------------------------------------------------</td></tr>";
                 for(var i in cartArray)
                 {
                     output +="<tr><td>" + (j++) +"</td> <td>"+cartArray[i].name 
                                     +"</td> <td>"+cartArray[i].count 
                                     +" </td> <td> "+ cartArray[i].prize 
                                     + " </td> <td> "+ cartArray[i].t + " </td> <td> "
                                     + "<button class='add-item' data-name='"+cartArray[i].name+"'> + </button>"
                                     + "<button class='delete-item' data-name='"+cartArray[i].name+"'> - </button>"
                                     + "<button class='delete-item-all' data-name='"+cartArray[i].name+"'> X </button>"
                                     + "</td></tr>";
                 }
                 $("#show-cart").html(output);
                 $("#total-cost").html( totalCart());
             }
             
             $("#show-cart").on("click", ".delete-item", function(event){
                 var name = $(this).attr("data-name");
                 removeItemFromCart(name);
                 displayCart();
             });
             
             $("#show-cart").on("click", ".delete-item-all", function(event){
                 var name = $(this).attr("data-name");
                 removeItemFromCartAll(name);
                 displayCart();
             }); 
             
             $("#show-cart").on("click", ".add-item", function(event){
                 var name = $(this).attr("data-name");
                 addItemToCart(0, name, 0, 1);
                 displayCart();
             }); 
              
              var cart=[];
              
              var Item= function(id, name, prize, count)
              {
                  this.id= id;
                  this.name= name;
                  this.prize= prize;
                  this.count= count;
                  
              };
              
              function addItemToCart(id, name, prize, count)
              {
                  for(var i in cart)
                  {
                      if(cart[i].name === name)
                      {
                          cart[i].count += count;
                          return;
                      }
                  }
                  var item = new Item(id,name,prize,count);
                  
                  cart.push(item);
              }
              
              function listCart(){
                  var cartCopy=[];
                  var sCart = [];
                  for(var i in cart){
                      var item= cart[i];
                      var itemCopy={};
                      for(var p in item)
                      {
                          itemCopy[p]=item[p];
                      }
                      itemCopy.t = (item.count * item.prize).toFixed(2) ;
                      
                      cartCopy.push(itemCopy);
                      sCart.push(itemCopy);
                  }
                  //document.getElementsByName['samplecart'].value = cartCopy;
                  //console.log(document.getElementsByName['samplecart'].value);
                  //alert(document.getElementById('samplecart'));
                  //document.getElementById('samplecart').value= sCart.join('$');
                  
                  //alert(JSON.stringify(cartCopy, '$'));
                  
                  document.getElementById('samplecart').value= JSON.stringify(cartCopy);
                  //alert("Cart Copy"+cartCopy);
                  //alert(document.getElementById('samplecart').value);
                 
                  console.log(document.getElementById('samplecart').value);
                  return cartCopy;
              }
              
              function totalCart()
              {
                  var totalCost=0;
                  for(var i in cart)
                  {
                      totalCost += cart[i].prize * cart[i].count;
                  }
                  return totalCost.toFixed(2);
              }
              
              
              function clearCart()
              {
                  cart=[];
              }
              
              function removeItemFromCart(name)
              {
                  for(var i in cart){
                      if(cart[i].name === name){
                          cart[i].count--;
                          if(cart[i].count===0)
                          {
                              cart.splice(i,1);
                          }
                          break;
                      }
                  }
              }
              
              function removeItemFromCartAll(name)
              {
                  for(var i in cart)
                  {
                      if(cart[i].name === name)
                      {
                          cart.splice(i,1);
                          break;
                      }
                  }
                  
               } 
    
    
                  
              
    
    
                  
                  
        </script>
              
  </body>
</html>

