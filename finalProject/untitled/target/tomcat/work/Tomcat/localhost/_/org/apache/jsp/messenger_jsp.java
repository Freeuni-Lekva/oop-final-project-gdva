/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-08-10 13:17:37 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import quizpackage.model.Message;
import quizpackage.model.DBHandler;
import quizpackage.model.Account;
import java.util.*;
import quizpackage.model.Chat;

public final class messenger_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <link href=\"/CSS/messenger.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<video autoplay muted loop id = \"backgrVid\">\r\n");
      out.write("    <source src=\"/CSS/backgr.mp4\" type=\"video/mp4\">\r\n");
      out.write("</video>\r\n");

    String obj = request.getParameter("to_account");
    Account to_account;
    DBHandler handler = (DBHandler) application.getAttribute("handler");
    Account currentAccount = (Account) session.getAttribute("account");
    List<Account> friends = (ArrayList<Account>)session.getAttribute("friends");
    List<Message> messageList = handler.getAccountMessages(currentAccount);
    if(obj == null){
      //  handler.debug("is null");
        to_account = handler.getMostRecentMessageAccount(currentAccount);
    }
    else{
      //  handler.debug("is not null");
        to_account = handler.getAccount(Integer.parseInt(obj));
    }
    if(to_account == null){
        handler.debug("is null");
    }
    session.setAttribute("to_account",to_account);
    Set<Integer> chat_ids = new HashSet<Integer>();
    List<Chat> chats = new ArrayList<Chat>();
    List<Message> toAccountMessages = new ArrayList<Message>();
    for(int i = messageList.size()-1; i>=0; i--){
        Account dest_account;

        if(messageList.get(i).getFrom().getId() == currentAccount.getId()){
            dest_account = messageList.get(i).getTo();
        }
        else{
            dest_account = messageList.get(i).getFrom();
        }
        if(!chat_ids.contains(dest_account.getId())){
            List<Message> chatMessages = handler.getDialogue(currentAccount,dest_account);
            Chat newChat = new Chat(currentAccount,dest_account,chatMessages);
            chats.add(newChat);
            if(newChat.getDisplayAccount().getId() == to_account.getId()){
                toAccountMessages = chatMessages;
            }
            chat_ids.add(dest_account.getId());
        }
    }

      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("    <div id = \"entireDiv\">\r\n");
      out.write("        <div id = \"centralDiv\">\r\n");
      out.write("            <div id = \"leftDiv\">\r\n");
      out.write("                <div id=\"searchBarDiv\">\r\n");
      out.write("                    <form action=\"SearchFriendMessagesServlet\" method=\"get\">\r\n");
      out.write("                        <input id=\"searchInput\" name=\"searchInput\" type=\"text\" placeholder=\"Search...\">\r\n");
      out.write("                        <input id=\"searchButton\" name=\"searchButton\" type=\"submit\" value=\"Search\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("                ");

                    for(int i = 0; i<chats.size();i++){
                        if(friends != null && friends.contains(chats.get(i).getDisplayAccount())) {
                            out.println("<a href = \"" + "messenger.jsp?to_account=" + chats.get(i).getDisplayAccount().getId() + "\">");
                            out.println("<div> <img src = \"" + chats.get(i).getDisplayAccount().getImage() + "\">");
                            out.println("<div><h4>" + chats.get(i).getDisplayName() + "</h4>");
                            out.println("<p>" + chats.get(i).getDisplayMessage() + "</p></div></div></a>");
                        }
                    }
                
      out.write("\r\n");
      out.write("            </div>\r\n");
      out.write("            <div id=\"rightDiv\">\r\n");
      out.write("                <div id=\"messageHeaderDiv\">\r\n");
      out.write("                    <img src=\"");
      out.print(to_account.getImage());
      out.write("\">\r\n");
      out.write("                    <div>\r\n");
      out.write("                        <h4>");
      out.print(to_account.getName() + " " + to_account.getSurname());
      out.write("</h4>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div id=\"messageDiv\">\r\n");
      out.write("                    ");

                        for(int i = 0; i<toAccountMessages.size();i++){
                            if(toAccountMessages.get(i).getFrom().equals(currentAccount)){
                                out.println("<div style = \" display:flex; justify-content: flex-end \"><div><p>"+toAccountMessages.get(i).getText()+"</p></div></div>");
                            }
                            else{
                                out.println("<div><img src = \""+toAccountMessages.get(i).getFrom().getImage() + "\">" +
                                        "<div><p>"+toAccountMessages.get(i).getText()+"</p></div></div>");
                            }
                        }
                    
      out.write("\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("                <script>\r\n");
      out.write("                    // Scroll the div with id \"myDiv\" to the bottom\r\n");
      out.write("                    $('#messageDiv').scrollTop($('#messageDiv')[0].scrollHeight);\r\n");
      out.write("                </script>\r\n");
      out.write("                <div id = \"inputDiv\">\r\n");
      out.write("                    <form action = \"messageButtonServlet\" method=\"post\" >\r\n");
      out.write("                        <input id = \"messageInput\" name=\"messageInput\" type=\"text\">\r\n");
      out.write("                        <input id = \"messageButton\" name=\"messageButton\" type=\"submit\" value=\"Send!\" >\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
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