/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-08-21 12:58:27 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import quizpackage.model.quizzes.Quiz;
import quizpackage.model.DBHandler;
import quizpackage.model.QuizStatistics;
import java.util.*;
import quizpackage.model.Account;
import java.text.SimpleDateFormat;

public final class quizSummary_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <link href=\"/CSS/quizSummary.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("    ");

        DBHandler handler = (DBHandler) application.getAttribute("handler");
        int quizId = Integer.parseInt(request.getParameter("id"));
        Account currentAccount = (Account)request.getSession().getAttribute("account");
        Quiz quiz = handler.getQuiz(quizId);
    
      out.write("\n");
      out.write("    ");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = format.format(new Date());
        handler.debug(currentTime);
    
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("<div id=\"quizHeader\">\n");
      out.write("    <h1>");
      out.print(quiz.getTitle());
      out.write("</h1>\n");
      out.write("</div>\n");
      out.write("<div id=\"bodyDiv\">\n");
      out.write("    <div id=\"leftDiv\">\n");
      out.write("        <div id = \"topPerformersAllTimeDiv\">\n");
      out.write("            <h1>Top Performers All time</h1>\n");
      out.write("            ");

                List<QuizStatistics> quizStatistics = handler.getTopPerformersOfAllTime(quizId);
                if(quizStatistics.size() > 0) {
                    for (int i = 0; i < quizStatistics.size(); i++) {
                        out.println("<div style = \"margin-bottom:5px;border-bottom : 3px dotted black;\">");
                        Account account = handler.getAccount(quizStatistics.get(i).getAccountId());
                        out.println("<p style=\"display:inline; vertical-align:middle;\"> User: </p><a style=\"text-decoration:none; color:inherit\" href = \"profile.jsp?id="+account.getId()+"\"><img src=\""+account.getImage() +"\" style = \"width:30px; height:30px; border-radius:10px; display:inline; vertical-align:middle;margin-right:10px;\"><p style=\"display:inline; vertical-align:middle\">" + account.getUsername() + "</p></a>");
                        out.println("<p> Score: "+ quizStatistics.get(i).getScore() +"</p>");
                        out.println("<p> Time: " + (double) quizStatistics.get(i).getTime() / 1000 + " seconds</p>");

                        out.println("</div>");
                    }
                } else {
                    out.println("<p> No Activity </p>");
                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id = \"topPerformersLastDayDiv\">\n");
      out.write("            <h1>Top Performers Last Day</h1>\n");
      out.write("            ");

                quizStatistics = handler.getTopPerformersOfTheDay(quizId);
                if(quizStatistics.size() > 0) {
                    for (int i = 0; i < quizStatistics.size(); i++) {
                        out.println("<div style = \"margin-bottom:5px;border-bottom : 3px dotted black;\">");
                        Account account = handler.getAccount(quizStatistics.get(i).getAccountId());
                        out.println("<p style=\"display:inline; vertical-align:middle;\"> User: </p><a style=\"text-decoration:none; color:inherit\" href = \"profile.jsp?id="+account.getId()+"\"><img src=\""+account.getImage() +"\" style = \"width:30px; height:30px; border-radius:10px; display:inline; vertical-align:middle;margin-right:10px;\"><p style=\"display:inline; vertical-align:middle\">" + account.getUsername() + "</p></a>");
                        out.println("<p> Score: "+ quizStatistics.get(i).getScore() +"</p>");
                        out.println("<p> Time: " + (double) quizStatistics.get(i).getTime() / 1000 + " seconds</p>");

                        out.println("</div>");
                    }
                } else {
                    out.println("<p> No Activity </p>");
                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("    <div id=\"midDiv\">\n");
      out.write("        <p>Quiz Description: ");
      out.print(quiz.getDescription());
      out.write("</p>\n");
      out.write("        <p>Author: ");
      out.print(handler.getAccount(quiz.getCreatorID()).getUsername());
      out.write("</p>\n");
      out.write("        <p>Questions Number: ");
      out.print(quiz.getQuestions().size());
      out.write("</p>\n");
      out.write("        <p>Quiz type: ");

            if(quiz.areQuestionsOnSinglePage()){
                out.print("Single Page");
            }
            else{
                out.print("Multiple Pages");
            }
        
      out.write("</p>\n");
      out.write("        <p>Total Score: ");
      out.print(quiz.getQuizTotalScore());
      out.write("</p>\n");
      out.write("        <a href=\"quiz.jsp?id=");
      out.print(quizId);
      out.write("&start_time=");
      out.print(currentTime);
      out.write("\">\n");
      out.write("            <input class=\"buttonClass\" type=\"submit\" value=\"start quiz\">\n");
      out.write("        </a>\n");
      out.write("        <form action=\"challengeServlet\" method=\"post\">\n");
      out.write("            <input  type=\"hidden\" value=\"");
      out.print(quizId);
      out.write("\" name=\"quiz_id\">\n");
      out.write("            <input type=\"text\" placeholder=\"enter who you want to challenge\" name=\"challengeField\" id=\"challengeField\">\n");
      out.write("            <input class = \"buttonClass\" type=\"submit\" value=\"challenge\">\n");
      out.write("        </form>\n");
      out.write("    </div>\n");
      out.write("    <div id=\"rightDiv\">\n");
      out.write("        <div id = \"myActivityDiv\">\n");
      out.write("            <h1>My Activity</h1>\n");
      out.write("            ");

                quizStatistics = handler.getQuizStatisticsForUserAndOrder(quizId, currentAccount.getId(), 0);
                if(quizStatistics.size() > 0) {
                    for (int i = 0; i < quizStatistics.size(); i++) {
                        out.println("<div style = \"margin-bottom:5px;border-bottom : 3px dotted black;\">");
                        Account account = handler.getAccount(quizStatistics.get(i).getAccountId());
                        out.println("<p style=\"display:inline; vertical-align:middle;\"> User: </p><a style=\"text-decoration:none; color:inherit\" href = \"profile.jsp?id="+account.getId()+"\"><img src=\""+account.getImage() +"\" style = \"width:30px; height:30px; border-radius:10px; display:inline; vertical-align:middle;margin-right:10px;\"><p style=\"display:inline; vertical-align:middle\">" + account.getUsername() + "</p></a>");
                        out.println("<p> Score: "+ quizStatistics.get(i).getScore() +"</p>");
                        out.println("<p> Time: " + (double) quizStatistics.get(i).getTime() / 1000 + " seconds</p>");

                        out.println("</div>");
                    }
                } else {
                    out.println("<p> No Activity </p>");
                }
            
      out.write("\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("\n");
      out.write("</div>\n");
      out.write("\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
