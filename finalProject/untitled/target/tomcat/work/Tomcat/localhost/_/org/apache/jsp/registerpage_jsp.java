/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-08-06 09:39:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Random;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import quizpackage.DBHandler;
import quizpackage.Account;
import java.util.List;
import java.io.PrintWriter;

public final class registerpage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <link href=\"/CSS/index.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<video autoplay muted loop id = \"backgrVid\">\r\n");
      out.write("    <source src=\"/CSS/backgr.mp4\" type=\"video/mp4\">\r\n");
      out.write("</video>\r\n");
      out.write("\r\n");
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div id = \"central\">\r\n");
      out.write("    <div id = \"loginDiv\">\r\n");
      out.write("        <div id = \"loginElements\">\r\n");
      out.write("            <form action = \"registerUserServlet\" method = \"get\">\r\n");
      out.write("            <p>Name: </p>\r\n");
      out.write("            <input type=\"text\" id=\"nameInput\" name=\"nameInput\">\r\n");
      out.write("            <p>Surname: </p>\r\n");
      out.write("            <input type=\"text\" id=\"surnameInput\" name=\"surnameInput\">\r\n");
      out.write("            <p>Username: </p>\r\n");
      out.write("            <input type=\"text\" id=\"usernameInput\" name=\"usernameInput\">\r\n");
      out.write("            <p>Password: </p>\r\n");
      out.write("            <input type=\"password\" id=\"passwordInput\" name=\"passwordInput\">\r\n");
      out.write("            <p>Age: </p>\r\n");
      out.write("            <input type=\"text\" id=\"ageInput\" name=\"ageInput\">\r\n");
      out.write("                <p>Image: </p>\r\n");
      out.write("                <input type=\"text\" id=\"imageInput\" name=\"imageInput\">\r\n");
      out.write("            <div id = \"buttonsDiv\">\r\n");
      out.write("                    <input type=\"submit\" value = \"Register\" id=\"registerButton\" name=\"registerButton\">\r\n");
      out.write("            </div>\r\n");
      out.write("            </form>\r\n");
      out.write("            <div id = \"errorMessage\">\r\n");
      out.write("                ");

                    Integer inp = (Integer)session.getAttribute("validRegister");
                    if(inp == null){
                        inp = 1;
                    }
                    String style = inp == 1 ? "\"display : none;\"" : "\"display : block;\"";
                
      out.write("\r\n");
      out.write("                <p  style = ");
      out.print(style);
      out.write(">Username already exists</p>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
