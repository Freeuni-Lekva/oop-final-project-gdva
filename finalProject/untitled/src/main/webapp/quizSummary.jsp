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
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <%
        DBHandler handler = (DBHandler) application.getAttribute("handler");
        int quizId = Integer.parseInt(request.getParameter("id"));
        Account currentAccount = (Account)request.getSession().getAttribute("account");
        Quiz quiz = handler.getQuiz(quizId);
    %>
    <%
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = format.format(new Date());
        handler.debug(currentTime);
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
        <%
            Account creator = handler.getAccount(quiz.getCreatorID());
            List<QuizStatistics> statistics = handler.getAllPerformances(quizId);
            Map<Integer,Integer> mp = new HashMap<Integer,Integer>();
            for(int i = 0; i<10;i++){
                mp.put(i,0);
            }
            for(int i = 0; i<statistics.size();i++){
               // System.out.println(statistics.get(i).getScore());
                double range = statistics.get(i).getScore() * 100 / quiz.getQuizTotalScore();
                int mpInd = (int) range;
                if(mpInd == 100) mpInd--;
                System.out.println(mpInd);
                mp.put(mpInd/10,mp.get(mpInd/10) + 1);
            }

        %>
        <p>Quiz Description: <%=quiz.getDescription()%></p>
        <p style="display:inline; vertical-align: middle;">Author:</p><a href="profile.jsp?id=<%=creator.getId()%>"><img style="display:inline;" src="<%=creator.getImage()%>"><p style="display:inline;vertical-align:middle;"><%=handler.getAccount(quiz.getCreatorID()).getUsername()%></p></a>
        <p>Questions Number: <%=quiz.getQuestions().size()%></p>
        <p>Quiz type: <%
            if(quiz.areQuestionsOnSinglePage()){
                out.print("Single Page");
            }
            else{
                out.print("Multiple Pages");
            }
        %></p>
        <p>Total Score: <%=quiz.getQuizTotalScore()%></p>
        <a href="quiz.jsp?id=<%=quizId%>&start_time=<%=currentTime%>">
            <input class="buttonClass" type="submit" value="start quiz">
        </a>
        <form action="challengeServlet" method="post">
            <input  type="hidden" value="<%=quizId%>" name="quiz_id">
            <input type="text" placeholder="enter who you want to challenge" name="challengeField" id="challengeField">
            <input class = "buttonClass" type="submit" value="challenge">
        </form>
        <canvas id="chart" width="200" height="200"></canvas>
        <script>
            let data = [];
            <% for(int i : mp.keySet()){%>
                data.push(<%=mp.get(i)%>);
            <%}%>
            let labels = ["0-10%", "11-20%", "21-30%", "31-40%", "41-50%", "51-60%","61-70%","71-80%","81-80%","91-100%"];
            let ctx = document.getElementById('chart').getContext('2d');
            let myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: labels,
                    datasets: [{
                        label: 'Number of People',
                        data: data,
                        backgroundColor: 'rgba(54, 162, 235, 0.2)',
                        borderColor: 'rgba(54, 162, 235, 1)',
                        borderWidth: 1
                    }]
                },
                options: {
                    scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
            });
        </script>
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
                        out.println("<p style=\"display:inline; vertical-align:middle;\"> User: </p><a style=\"text-decoration:none; color:inherit\" href = \"profile.jsp?id="+account.getId()+"\"><img src=\""+account.getImage() +"\" style = \"\"><p style=\"display:inline; vertical-align:middle\">" + account.getUsername() + "</p></a>");
                        out.println("<p> Score: "+ quizStatistics.get(i).getScore() +"</p>");
                        out.println("<p> Time: " + (double) quizStatistics.get(i).getTime() / 1000 + " seconds</p>");

                        out.println("</div>");
                    }
                } else {
                    out.println("<p> No Activity </p>");
                }
            %>
        </div>
        <div id = "lastPerformers">
            <h1>Last Performers</h1>
            <%
                quizStatistics = handler.getLastPerformers(quizId);
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
                    out.println("<p> No Performers </p>");
                }
            %>
        </div>
    </div>

</div>

</body>
</html>
