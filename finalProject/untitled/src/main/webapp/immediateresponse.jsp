<%@ page import="quizpackage.model.quizzes.Quiz" %>
<%@ page import="java.util.Collections" %>
<%@ page import="quizpackage.model.quizzes.Question" %>
<%@ page import="java.util.List" %>
<%@ page import="quizpackage.model.quizzes.PictureResponse" %>
<%@ page import="quizpackage.model.quizzes.MultipleChoiceSingleAnswer" %>
<%@ page import="quizpackage.model.DBHandler" %><%--
  Created by IntelliJ IDEA.
  User: Giorgi
  Date: 15.08.2023
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <%
    DBHandler handler = (DBHandler)request.getServletContext().getAttribute("handler");
    Question question = (Question)request.getSession().getAttribute("ImmediateResponseQuestion");
    String answer = request.getParameter("answer");
    String startTime = request.getParameter("start_time");
    int id = Integer.parseInt(request.getParameter("questionId"))+1;
%>
<head>
  <link href="/CSS/immediateresponse.css" rel="stylesheet" type="text/css">
</head>
<body>
<video autoplay muted loop id="backgrVid">
  <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<div id="entireDiv">
  <h1></h1>
  <div id = "questionsAndButtonsDiv">
        <%if(question.getQuestionClass().equals("QuestionResponse")) {%>
        <div class="question">
          <p class="question-text"><%=id+ "." +question.getQuestionText() %></p>
          <p class="question-text">Your answer: <%=answer%></p>
          <p class="question-text">Correct Answer:<%=question.getAnswer()%></p>
          <p class="question-text"><%=question.gradeQuestion(answer)%> out of <%=question.getQuestionGrade()%></p>
        </div>
        <%}else if(question.getQuestionClass().equals("FillTheBlank")){ %>
        <div class="question">
          <p class="question-text"><%=id + "." + question.getQuestionText() %></p>
          <p class="question-text">Your answer: <%=answer%></p>
          <p class="question-text">Correct Answer:<%=question.getAnswer()%></p>
          <p class="question-text"><%=question.gradeQuestion(answer)%> out of <%=question.getQuestionGrade()%></p>
        </div>
        <%} else if (question.getQuestionClass().equals("PictureResponse")) { %>
        <%
          PictureResponse pictureQuestion = (PictureResponse) question;
        %>
        <div class="question">
          <p class="question-text"><%=id + "." + question.getQuestionText() %></p>
          <div class="image-question-container">
            <img src="<%= pictureQuestion.getImage() %>" alt="Question Image">
          </div>
          <p class="question-text">Your answer:<%=answer%></p>
          <p class="question-text">Correct Answer:<%=question.getAnswer()%></p>
          <p class="question-text"><%=question.gradeQuestion(answer)%> out of <%=question.getQuestionGrade()%></p>
        </div>
        <% } else if (question.getQuestionClass().equals("MultipleChoiceSingleAnswer")) { %>
        <div class="question">
          <p class="question-text"><%=id + "." + question.getQuestionText() %></p>
          <p class="question-text">Your answer:<%=answer%></p>
          <p class="question-text">Correct Answer:<%=question.getAnswer()%></p>
          <p class="question-text"><%=question.gradeQuestion(answer)%> out of <%=question.getQuestionGrade()%></p>
        </div>
        <% } %>
    <div class = "centered-button">
      <div id="buttonsDiv">
        <form action="FinishOnePageQuizServlet" method = "get">
          <input type="hidden" name="buttonValue" value="Next">
          <input type="hidden" name="start_time" value="<%=startTime%>">
          <input type="hidden" name="<%=id%>" value="<%=answer%>">
          <button class="custom-button">Next</button>
        </form>
      </div>
    </div>
  </div>
</div>
</body>
</html>
