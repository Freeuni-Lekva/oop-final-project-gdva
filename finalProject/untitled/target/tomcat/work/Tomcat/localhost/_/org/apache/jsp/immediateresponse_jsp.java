/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2023-08-20 11:57:39 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import quizpackage.model.quizzes.Quiz;
import java.util.Collections;
import quizpackage.model.quizzes.Question;
import java.util.List;
import quizpackage.model.quizzes.PictureResponse;
import quizpackage.model.quizzes.MultipleChoiceSingleAnswer;
import quizpackage.model.DBHandler;

public final class immediateresponse_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("  ");

    DBHandler handler = (DBHandler)request.getServletContext().getAttribute("handler");
    Question question = (Question)request.getSession().getAttribute("ImmediateResponseQuestion");
    String answer = request.getParameter("answer");
    int id = Integer.parseInt(request.getParameter("questionId"))+1;

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("  <link href=\"/CSS/immediateresponse.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<video autoplay muted loop id=\"backgrVid\">\r\n");
      out.write("  <source src=\"/CSS/backgr.mp4\" type=\"video/mp4\">\r\n");
      out.write("</video>\r\n");
      out.write("<div id=\"entireDiv\">\r\n");
      out.write("  <h1></h1>\r\n");
      out.write("  <div id = \"questionsAndButtonsDiv\">\r\n");
      out.write("        ");
if(question.getQuestionClass().equals("QuestionResponse")) {
      out.write("\r\n");
      out.write("        <div class=\"question\">\r\n");
      out.write("          <p class=\"question-text\">");
      out.print(id+ "." +question.getQuestionText() );
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">Your answer: ");
      out.print(answer);
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">Correct Answer:");
      out.print(question.getAnswer());
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">");
      out.print(question.gradeQuestion(answer));
      out.write(" out of ");
      out.print(question.getQuestionGrade());
      out.write("</p>\r\n");
      out.write("        </div>\r\n");
      out.write("        ");
}else if(question.getQuestionClass().equals("FillTheBlank")){ 
      out.write("\r\n");
      out.write("        <div class=\"question\">\r\n");
      out.write("          <p class=\"question-text\">");
      out.print(id + "." + question.getQuestionText() );
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">Your answer: ");
      out.print(answer);
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">Correct Answer:");
      out.print(question.getAnswer());
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">");
      out.print(question.gradeQuestion(answer));
      out.write(" out of ");
      out.print(question.getQuestionGrade());
      out.write("</p>\r\n");
      out.write("        </div>\r\n");
      out.write("        ");
} else if (question.getQuestionClass().equals("PictureResponse")) { 
      out.write("\r\n");
      out.write("        ");

          PictureResponse pictureQuestion = (PictureResponse) question;
        
      out.write("\r\n");
      out.write("        <div class=\"question\">\r\n");
      out.write("          <p class=\"question-text\">");
      out.print(id + "." + question.getQuestionText() );
      out.write("</p>\r\n");
      out.write("          <div class=\"image-question-container\">\r\n");
      out.write("            <img src=\"");
      out.print( pictureQuestion.getImage() );
      out.write("\" alt=\"Question Image\">\r\n");
      out.write("          </div>\r\n");
      out.write("          <p class=\"question-text\">Your answer:");
      out.print(answer);
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">Correct Answer:");
      out.print(question.getAnswer());
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">");
      out.print(question.gradeQuestion(answer));
      out.write(" out of ");
      out.print(question.getQuestionGrade());
      out.write("</p>\r\n");
      out.write("        </div>\r\n");
      out.write("        ");
 } else if (question.getQuestionClass().equals("MultipleChoiceSingleAnswer")) { 
      out.write("\r\n");
      out.write("        <div class=\"question\">\r\n");
      out.write("          <p class=\"question-text\">");
      out.print(id + "." + question.getQuestionText() );
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">Your answer:");
      out.print(answer);
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">Correct Answer:");
      out.print(question.getAnswer());
      out.write("</p>\r\n");
      out.write("          <p class=\"question-text\">");
      out.print(question.gradeQuestion(answer));
      out.write(" out of ");
      out.print(question.getQuestionGrade());
      out.write("</p>\r\n");
      out.write("        </div>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("    <div class = \"centered-button\">\r\n");
      out.write("      <div id=\"buttonsDiv\">\r\n");
      out.write("        <form action=\"FinishOnePageQuizServlet\" method = \"get\">\r\n");
      out.write("          <input type=\"hidden\" name=\"buttonValue\" value=\"Next\">\r\n");
      out.write("          <input type=\"hidden\" name=\"");
      out.print(id);
      out.write("\" value=\"");
      out.print(answer);
      out.write("\">\r\n");
      out.write("          <button class=\"custom-button\">Next</button>\r\n");
      out.write("        </form>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
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
