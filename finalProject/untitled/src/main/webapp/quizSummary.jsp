<%@ page import="quizpackage.model.quizzes.Quiz" %>
<%@ page import="quizpackage.model.DBHandler" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 8/18/2023
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        DBHandler handler = (DBHandler) application.getAttribute("handler");
        int quizId = Integer.parseInt(request.getParameter("id"));
        Quiz quiz = handler.getQuiz(quizId);

    %>
    <div id="quizDiv">
        <h1><%=quiz.getTitle()%></h1>
        <p>questions number: <%=quiz.getQuestions().size()%></p>
        <p>quiz type: <%
            if(quiz.areQuestionsOnSinglePage()){
                out.print("single page");
            }
            else{
                out.print("multiple pages");
            }
        %></p>
        <p>total score: <%=quiz.getQuizTotalScore()%></p>
        <a href="quiz.jsp?id=<%=quizId%>&start_time=<%=System.currentTimeMillis()%>">
            <input type="submit" value="start quiz">
        </a>
    </div>
</head>
<body>

</body>
</html>
