<%@ page import="quizpackage.model.quizzes.Quiz" %>
<%@ page import="quizpackage.model.DBHandler" %>
<%@ page import="quizpackage.model.QuizStatistics" %>
<%@ page import="java.util.*" %>
<%@ page import="quizpackage.model.Account" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 8/18/2023
  Time: 2:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="/CSS/quizSummary.css" rel="stylesheet" type="text/css">
    <%
        DBHandler handler = (DBHandler) application.getAttribute("handler");
        int quizId = Integer.parseInt(request.getParameter("id"));
        Account currentAccount = (Account)request.getSession().getAttribute("account");
        Quiz quiz = handler.getQuiz(quizId);
    %>
</head>
<body>
<div id="quizHeader">
    <h1><%=quiz.getTitle()%></h1>
</div>
<div id="bodyDiv">
    <div id="leftDiv">
        <div id = "topPerformersAllTimeDiv">
            <h1>Top Performers All time</h1>
            <%
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
            %>
        </div>

        <div id = "topPerformersLastDayDiv">
            <h1>Top Performers Last Day</h1>
            <%
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
            %>
        </div>



    </div>
    <div id="midDiv">
        <p>Quiz Description: <%=quiz.getDescription()%></p>
        <p>Author: <%=handler.getAccount(quiz.getCreatorID()).getUsername()%></p>
        <p>Questions Number: <%=quiz.getQuestions().size()%></p>
        <p>Quiz type: <%
            if(quiz.areQuestionsOnSinglePage()){
                out.print("Single Page");
            }
            else{
                out.print("Multiple Pages");
            }
        %></p>
        <p>total score: <%=quiz.getQuizTotalScore()%></p>
        <form action="StartQuizServlet" method ="get">
            <input type="hidden" value="<%=quizId%>" name ="quiz_id">
            <input class="buttonClass" type="submit" value="start quiz">
        </form>
        <form action="challengeServlet" method="post">
            <input  type="hidden" value="<%=quizId%>" name="quiz_id">
            <input type="text" placeholder="enter who you want to challenge" name="challengeField" id="challengeField">
            <input class = "buttonClass" type="submit" value="challenge">
        </form>
    </div>
    <div id="rightDiv">
        <div id = "myActivityDiv">
            <h1>My Activity</h1>
            <%
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
            %>
        </div>
    </div>

</div>

</body>
</html>
