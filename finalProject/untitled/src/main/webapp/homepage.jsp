<%@ page import="quizpackage.model.Account" %>
<%@ page import="quizpackage.model.Announcement" %>
<%@ page import="java.util.List" %>
<%@ page import="quizpackage.model.DBHandler" %>
<%@ page import="quizpackage.model.quizzes.Quiz" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="quizpackage.model.QuizStatistics" %>


<html>
<head>
    <meta charset="UTF-8">
    <link href="/CSS/homepage.css" rel="stylesheet" type="text/css">
</head>

<body>

<video autoplay muted loop id = "backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<%
    DBHandler handler = (DBHandler)application.getAttribute("handler");
    Account currentAccount = (Account)request.getSession().getAttribute("account");
    List<Announcement> announcements = handler.getAnnouncements();
    List<Quiz> userQuizzes = handler.getQuizzesByAuthor(currentAccount.getId());
    List<Quiz>allQuizzes = handler.getRecentQuizzes();
    List<Quiz> popularQuizzes = handler.getPopularQuizzes();
    List<QuizStatistics> recentActivities = handler.getRecentActivities(currentAccount.getId());
%>
<div id = "entireDiv">
    <div id = "topDiv">
        <h1>Quiz Website</h1>
        <ul>
            <li> <a style = " "; href="friendrequests.jsp">Friend Requests</a></li>
            <li> <a style = " "; href="searchuser.jsp">Search Users</a></li>
            <li> <a style = " "; href="messenger.jsp">Messages</a></li>
            <li> <a style = " "; href="createQuiz.jsp">Create Quiz</a></li>
            <li><%=currentAccount.getUsername()%></li>
        </ul>
    </div>
    <div id ="bodyDiv">
        <div id="leftDiv"></div>

        <div id = "midDiv">
            <div id="topMarginDiv"></div>
            <div id="midList">
                <span class="label" onclick="showDiv(0)">Announcements</span>
                <span class="label" onclick="showDiv(1)">Recent Quizzes</span>
                <span class="label" onclick="showDiv(2)">My Quizzes</span>
                <span class="label" onclick="showDiv(3)">Popular Quizzes</span>
                <span class="label" onclick="showDiv(4)">Recent Activities</span>
            </div>
            <script>
                function showDiv(n) {
                    const labels = document.querySelectorAll('.label');
                    for(let i = 0; i<labels.length;i++){
                        labels[i].classList.remove('active');
                    }
                    labels[n].classList.add('active');
                    const divs = ["announcementDiv","recentQuizzesDiv","myQuizzesDiv","popularQuizzesDiv","recentActivitiesDiv"];
                    for(let i = 0; i<5;i++){
                        document.getElementById(divs[i]).classList.add('hidden');
                    }
                    document.getElementById(divs[n]).classList.remove('hidden');
                }
            </script>
            <div id="announcementDiv" class="hidden">
                <h1>Announcements</h1>
                <%
                    for(int i = 0; i<announcements.size();i++){
                        out.println("<a href=\"announcement.jsp?id="+announcements.get(i).getId()+"\"><div>");
                        out.println("<img  src = \""+announcements.get(i).getImgSrc()+"\">");
                        out.println("<p style = \" top: 5%; left: 50%;\">"+announcements.get(i).getAuthor().getUsername() + "</p>");
                        out.println("<p style = \" top: 50%; left: 50%;\">"+announcements.get(i).getTitle() + "</p>");
                        out.println("</div></a>");
                    }
                %>

            </div>
            <div id="recentQuizzesDiv" class="hidden">
                <div class="quizTitlesDiv">
                    <%
                        if(allQuizzes != null){
                            for(int i = 0; i<allQuizzes.size();i++){
                                out.println("<a href=\"quizSummary.jsp?id=" + allQuizzes.get(i).getId() + "\"><div>");
                                out.println("<p style = \" \" class=\"label\">" + allQuizzes.get(i).getTitle() + "</p>");
                                out.println("</div></a>");
                            }
                        }
                    %>
                </div>
            </div>
            <div id = "myQuizzesDiv" class="hidden">

                <div class = "quizTitlesDiv">
                    <%
                        if(userQuizzes != null) {
                            for (int i = 0; i < userQuizzes.size(); i++) {
                                out.println("<a href=\"quizSummary.jsp?id=" + userQuizzes.get(i).getId() + "\"><div>");
                                out.println("<p style = \" \" class=\"label\">" + userQuizzes.get(i).getTitle() + "</p>");
                                out.println("</div></a>");
                            }
                        }
                    %>
                </div>
            </div>
            <div id="popularQuizzesDiv" class="hidden">
                <div class = "quizTitlesDiv">
                <%
                    if(popularQuizzes != null){
                        for (int i = 0; i < popularQuizzes.size(); i++) {
                            out.println("<a href=\"quizSummary.jsp?id=" + popularQuizzes.get(i).getId() + "\"><div>");
                            out.println("<p style = \" \" class=\"label\">" + popularQuizzes.get(i).getTitle() + "</p>");
                            out.println("</div></a>");
                        }
                    }
                %>
                </div>
            </div>
            <div id="recentActivitiesDiv" class="hidden">
                <div class = "quizTitlesDiv">
                <%
                    if(recentActivities != null){
                        for (int i = 0; i < recentActivities.size(); i++) {
                            Quiz quiz = handler.getQuiz(recentActivities.get(i).getQuizId());
                            out.println("<a href=\"quizSummary.jsp?id=" + recentActivities.get(i).getQuizId() + "\"><div>");
                            out.println("<p style = \" \" class=\"label\">Quiz title: "+ quiz.getTitle()+"</p>");
                            out.println("<p style = \" \" >Score: "+ recentActivities.get(i).getScore()+"</p>");
                            out.println("<p style = \" \" >Time: "+ (double) recentActivities.get(i).getTime() / 1000 +" seconds</p>");
                            out.println("</div></a>");
                        }
                    }
                %>
                </div>
            </div>
        </div>


    </div>

</div>

</body>
</html>