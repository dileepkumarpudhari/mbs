package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width\">\n");
      out.write("        <meta name=\"description\" content=\"Affordable and professional Management and Billing System\">\n");
      out.write("        <meta name=\"keywords\" content=\"Affordable and professional Management Billing System\">\n");
      out.write("  \t<meta name=\"author\" content=\"Dileep Kumar\">\n");
      out.write("    <title>Management and Billing System</title>\n");
      out.write("    <link rel=\"stylesheet\" href=\"./css/style.css\">\n");
      out.write("\t<link rel=\"stylesheet\" href=\"./css/Logincss.css\">\n");
      out.write("\t\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <header>\n");
      out.write("      <div class=\"container\">\n");
      out.write("        <div id=\"branding\"> \n");
      out.write("            <a href=\"HomeScreen.html\" > <img src=\"img/MBSlogo1.png\"/> </a>\n");
      out.write("        </div>\n");
      out.write("        <nav>\n");
      out.write("          <ul>\n");
      out.write("            <li><a href=\"HomeScreen.html\" class=\"blackbtn\" title=\"HomeDescription\">Home</a></li>\n");
      out.write("            <li><a href=\"about.html\" class=\"blackbtn\" title=\"aboutDescription\">About</a></li>\n");
      out.write("            <li><a href=\"Services.html\" class=\"blackbtn\" title=\"ServicesDescription\">Services</a></li>\n");
      out.write("            <li class=\"current\"><a href=\"Login.jsp\" class=\"blackbtn\" title=\"LoginDescription\"> SignIn </a> </li>\n");
      out.write("\t    <li><a href=\"Register.jsp\" class=\"blackbtnreg\" title=\"RegisterDescription\"> Not a Member ? </a></li>\n");
      out.write("\t\t\t\n");
      out.write("            \n");
      out.write("          </ul>\n");
      out.write("        </nav>\n");
      out.write("      </div>\n");
      out.write("    </header>\n");
      out.write("\n");
      out.write("    <section id=\"showcase\">\n");
      out.write("     <div class=\"container\">\n");
      out.write("\t     <div class=\"box\">\n");
      out.write("\t\t    <h2> Login </h2>\n");
      out.write("\t\t\t<form autocomplete=\"off\" action=\"LoginServlet\" method=\"post\">\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t        <div class= \"dropdownbox\">\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t  <select id=\"roles\" name=\"roles\" required=\"\">\n");
      out.write("\t\t\t\t\t\t\t<option value=\"Employee\">Employee</option>\n");
      out.write("\t\t\t\t\t\t\t<option value=\"Manager\">Manager</option>\n");
      out.write("\t\t\t\t\t\t\t\n");
      out.write("\t\t\t\t\t  </select>\n");
      out.write("\t\t\t\t\t <br>\n");
      out.write("\t\t\t\t\t <br>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class= \"inputBox\">\n");
      out.write("\t\t\t\t\t  <input type=\"text\" name=\"email\" required=\"\" >\n");
      out.write("\t\t\t\t\t  <label> Email ID </label>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t<div class= \"inputBox\">\n");
      out.write("\t\t\t\t\t  <input type=\"password\" name=\"password\" required=\"\">\n");
      out.write("\t\t\t\t\t  <label> Password </label>\n");
      out.write("\t\t\t\t\t</div>\n");
      out.write("\t\t\t\t\t\n");
      out.write("\t\t\t\t\t<input type=\"submit\" name=\"submit\" value=\"Submit\">\n");
      out.write("\t\t\t</form>\n");
      out.write("                    ");
 String re = (String) request.getAttribute("res"); 
                if(re =="error"){
                    
      out.write("<br><span color=\"red\"> Invalid Username and Password </span>  ");
 } 
      out.write("\n");
      out.write("\t\t</div>\n");
      out.write("             \n");
      out.write("         \n");
      out.write("    </div>  \n");
      out.write("    </section>\n");
      out.write("    <footer>\n");
      out.write("      <span class=\"center\">   BMS, Copyright &copy; 2018  </span>          <span class=\"right\"> Designed by, Dileep Kumar </span> \n");
      out.write("    </footer>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
