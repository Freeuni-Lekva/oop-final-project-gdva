/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-08-21 12:15:36 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import quizpackage.model.DBHandler;
import quizpackage.model.Announcement;
import quizpackage.model.Account;

public final class profile_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
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

    String id = request.getParameter("id");
    DBHandler handler = (DBHandler) request.getServletContext().getAttribute("handler");
    Account profileUser = handler.getAccount(Integer.valueOf(id));
    Account currentUser = (Account) session.getAttribute("account");
    session.setAttribute("profile", profileUser);
    Boolean isRequestSent = handler.isRequestSent(currentUser, profileUser);
    Boolean areAlreadyFriends = handler.areFriends(currentUser, profileUser);

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <title>Profile Page</title>\r\n");
      out.write("    <link href=\"/CSS/profile.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<header>\r\n");
      out.write("    <h1>");
      out.print( profileUser.getUsername() );
      out.write("</h1>\r\n");
      out.write("</header>\r\n");
      out.write("<video autoplay muted loop id=\"backgrVid\">\r\n");
      out.write("    <source src=\"/CSS/backgr.mp4\" type=\"video/mp4\">\r\n");
      out.write("</video>\r\n");
      out.write("<div id=\"profile-picture\">\r\n");
      out.write("    <img src=\"");
      out.print( profileUser.getImage() );
      out.write("\">\r\n");
      out.write("</div>\r\n");
      out.write("<div id=\"profile-info\">\r\n");
      out.write("    <h1>");
      out.print( profileUser.getName() + " " + profileUser.getSurname() );
      out.write("</h1>\r\n");
      out.write("    <div class=\"section\">\r\n");
      out.write("        <h2>Age: ");
      out.print( profileUser.getAge() );
      out.write("</h2>\r\n");
      out.write("    </div>\r\n");
      out.write("    <div class=\"section\">\r\n");
      out.write("        <form action=\"SendRequestServlet\" method=\"get\">\r\n");
      out.write("            ");
 if (profileUser.getId() != currentUser.getId() && !areAlreadyFriends) {
                if (!isRequestSent) { 
      out.write("\r\n");
      out.write("            <input class=\"friend-request-button\" type=\"submit\" value=\"Send Request\" id=\"sendRequestButton\"\r\n");
      out.write("                   name=\"sendRequestButton\">\r\n");
      out.write("            ");
 } else { 
      out.write("\r\n");
      out.write("            <h3 style=\"color: gold;\">Request Sent</h3>\r\n");
      out.write("            ");
 }
            } 
      out.write("\r\n");
      out.write("        </form>\r\n");
      out.write("        ");
 if (areAlreadyFriends) { 
      out.write("\r\n");
      out.write("        <h3 style=\"color: gold;\">Friends</h3>\r\n");
      out.write("        <form action=\"RemoveFriendServlet\" method=\"get\">\r\n");
      out.write("            <input class=\"friend-request-button\" type=\"submit\" value=\"Remove From Friends\" id=\"removeFriendButton\"\r\n");
      out.write("                   name=\"removeFriendButton\">\r\n");
      out.write("        </form>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
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
