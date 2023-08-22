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
    Double result = (Double) request.getSession().getAttribute("Result");
    session.setAttribute("Result",0.0);
    session.setAttribute("result",null);
    String quizID = (String)request.getSession().getAttribute("quizID");
    Quiz quiz = handler.getQuiz(Integer.parseInt(quizID));
    List<Question> questions = (List<Question>)session.getAttribute("Questions");
%>
<head>
    <link href="/CSS/quiz.css" rel="stylesheet" type="text/css">
</head>
<body>
<video autoplay muted loop id="backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<div id="entireDiv">
    <h1>Result: <%=result%></h1>
    <div id = "questionsAndButtonsDiv">
        <div id="questionsDiv">
            <div class="questions-container">
                <%
                    int count = 0;
                %>

                <% for (Question question : questions) {
                    String answer = (String)session.getAttribute("q"+count);
                    handler.debug("Count: "+count);
                    handler.debug("ANSWER"+answer);
                    count++;
                    if(question.getQuestionClass().equals("QuestionResponse")) {%>
                <div class="question">
                    <p class="question-text"><%=count + "." +question.getQuestionText() %></p>
                    <p class="question-text">Your answer: <%=answer%></p>
                    <p class="question-text">Correct Answer:<%=question.getAnswer()%></p>
                    <p class="question-text"><%=question.gradeQuestion(answer)%> out of <%=question.getQuestionGrade()%></p>
                </div>
                <%}else if(question.getQuestionClass().equals("FillTheBlank")){ %>
                <div class="question">
                    <p class="question-text"><%=count + "." + question.getQuestionText() %></p>
                    <p class="question-text">Your answer: <%=answer%></p>
                    <p class="question-text">Correct Answer:<%=question.getAnswer()%></p>
                    <p class="question-text"><%=question.gradeQuestion(answer)%> out of <%=question.getQuestionGrade()%></p>
                </div>
                <%} else if (question.getQuestionClass().equals("PictureResponse")) { %>
                <%
                    PictureResponse pictureQuestion = (PictureResponse) question;
                %>
                <div class="question">
                    <p class="question-text"><%=count + "." + question.getQuestionText() %></p>
                    <div class="image-question-container">
                        <img src="<%= pictureQuestion.getImage() %>" alt="Question Image">
                    </div>
                    <p class="question-text">Your answer:<%=answer%></p>
                    <p class="question-text">Correct Answer:<%=question.getAnswer()%></p>
                    <p class="question-text"><%=question.gradeQuestion(answer)%> out of <%=question.getQuestionGrade()%></p>
                </div>
                <% } else if (question.getQuestionClass().equals("MultipleChoiceSingleAnswer")) { %>
                <div class="question">
                    <p class="question-text"><%=count + "." + question.getQuestionText() %></p>
                    <p class="question-text">Your answer:<%=answer%></p>
                    <p class="question-text">Correct Answer:<%=question.getAnswer()%></p>
                    <p class="question-text"><%=question.gradeQuestion(answer)%> out of <%=question.getQuestionGrade()%></p>
                </div>
                <% } } %>
            </div>
                <a href="homepage.jsp">
                    <input type="submit" value="Back To Home Page">
                </a>
        </div>
    </div>
</div>
