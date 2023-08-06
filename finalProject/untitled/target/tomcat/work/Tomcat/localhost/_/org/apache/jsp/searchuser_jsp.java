/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-08-06 14:20:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import quizpackage.Account;
import quizpackage.Announcement;
import java.util.List;
import quizpackage.DBHandler;
import java.util.ArrayList;

public final class searchuser_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <link href=\"/CSS/homepage.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<video autoplay muted loop id = \"backgrVid\">\r\n");
      out.write("    <source src=\"/CSS/backgr.mp4\" type=\"video/mp4\">\r\n");
      out.write("</video>\r\n");

    DBHandler handler = (DBHandler)application.getAttribute("handler");
    Account currentAccount = (Account)request.getSession().getAttribute("account");
    List<Account> accounts = (List<Account>) request.getSession().getAttribute("accounts");

      out.write("\r\n");
      out.write("<div id = \"entireDiv\">\r\n");
      out.write("    <div id = \"topDiv\">\r\n");
      out.write("        <h1>Search Users</h1>\r\n");
      out.write("        <ul>\r\n");
      out.write("            <li>");
      out.print(currentAccount.getUsername());
      out.write("</li>\r\n");
      out.write("        </ul>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div id =\"bodyDiv\">\r\n");
      out.write("        <div id = \"midDiv\">\r\n");
      out.write("            <div id=\"topMarginDiv\">\r\n");
      out.write("                <form action = \"searchUsersServlet\" method = \"get\">\r\n");
      out.write("                    <p style = \"color: azure;\"> User Name: </p>\r\n");
      out.write("                    <input type=\"text\" id=\"usernameInput\" name=\"usernameInput\">\r\n");
      out.write("                    <input type=\"submit\" value = \"Search\" id=\"searchButton\" name=\"searchButton\">\r\n");
      out.write("                </form>\r\n");
      out.write("            </div>\r\n");
      out.write("            <div id=\"midList\"> </div>\r\n");
      out.write("            <div id=\"announcementDiv\">\r\n");
      out.write("                ");

                    if(accounts != null) {
                        for (int i = 0; i < accounts.size(); i++) {
                            out.println("<a href=\"profile.jsp?id=" + accounts.get(i).getId() + "\"><div>");
                            out.println("<img  src = \"" + accounts.get(i).getImage() + "\">");
                            out.println("<p style = \" top : 5%; left:50%;\">" + accounts.get(i).getUsername() + "</p>");
                            out.println("<p style = \" top : 50%; left:50%;\">"
                                    + accounts.get(i).getName() +" " + accounts.get(i).getSurname() +  "</p>");
                            out.println("</div></a>");
                        }
                    }
                
      out.write("\r\n");
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
