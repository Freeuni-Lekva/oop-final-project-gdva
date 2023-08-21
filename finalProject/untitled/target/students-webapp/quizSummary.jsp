<%@ page import="quizpackage.model.quizzes.Quiz" %>
<%@ page import="quizpackage.model.DBHandler" %>
<%@ page import="quizpackage.model.QuizStatistics" %>
<%@ page import="java.util.List" %>
<%@ page import="quizpackage.model.Account" %><%--
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
        Account currentAccount = (Account)request.getSession().getAttribute("account");
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
        <form action="challengeServlet" method="post">
            <input type="hidden" value="<%=quizId%>" name="quiz_id">
            <input type="text" placeholder="enter who you want to challenge" name="challengeField" id="challengeField">
            <input type="submit" value="challenge">
        </form>
    </div>
</head>
<body>

    <div id = "topPerformersAllTimeDiv">
        <h1>Top Performers All time</h1>
        <%
            List<QuizStatistics> quizStatistics = handler.getTopPerformersOfAllTime();
            if(quizStatistics.size() > 0) {
                for (int i = 0; i < quizStatistics.size(); i++) {
                    out.println("<div>");
                    Account account = handler.getAccount(quizStatistics.get(i).getAccountId());
                    out.println("<p> Name: " + account.getName() +
                            " Score: " + quizStatistics.get(i).getScore() +
                            " Time: " + quizStatistics.get(i).getTime() + "</p>");
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
            quizStatistics = handler.getTopPerformersOfTheDay();
            if(quizStatistics.size() > 0) {
                for (int i = 0; i < quizStatistics.size(); i++) {
                    out.println("<div>");
                    Account account = handler.getAccount(quizStatistics.get(i).getAccountId());
                    out.println("<p> Name: " + account.getName() +
                            " Score: " + quizStatistics.get(i).getScore() +
                            " Time: " + quizStatistics.get(i).getTime() + "</p>");
                    out.println("</div>");
                }
            } else {
                out.println("<p> No Activity </p>");
            }
        %>
    </div>

    <div id = "myActivityDiv">
        <h1>My Activity</h1>
        <%
            quizStatistics = handler.getQuizStatisticsForUserAndOrder(quizId, currentAccount.getId(), 0);
            if(quizStatistics.size() > 0) {
                for (int i = 0; i < quizStatistics.size(); i++) {
                    out.println("<div>");
                    Account account = handler.getAccount(quizStatistics.get(i).getAccountId());
                    out.println("<p> Name: " + account.getName() +
                            " Score: " + quizStatistics.get(i).getScore() +
                            " Time: " + quizStatistics.get(i).getTime() + "</p>");
                    out.println("</div>");
                }
            } else {
                out.println("<p> No Activity </p>");
            }
        %>
    </div>

</body>
</html>
