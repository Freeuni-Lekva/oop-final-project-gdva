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

<%--<video autoplay muted loop id = "backgrVid">--%>
<%--    <source src="/CSS/backgr.mp4" type="video/mp4">--%>
<%--</video>--%>
<%
    DBHandler handler = (DBHandler)application.getAttribute("handler");
    Account currentAccount = (Account)request.getSession().getAttribute("account");
    List<Announcement> announcements = handler.getAnnouncements();
    List<Quiz> userQuizzes = handler.getQuizzesByAuthor(currentAccount.getId());
    List<Quiz>allQuizzes = handler.getRecentQuizzes();
    List<Quiz> popularQuizzes = handler.getPopularQuizzes();
    List<QuizStatistics> recentActivities = handler.getRecentActivities(currentAccount.getId());
    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
    List<Account> requests = handler.getReceivedFriendRequests(currentAccount);
    List<Quiz> friendsQuizzes = handler.friendsQuizzes(currentAccount.getId());
    List<QuizStatistics> friendsStatistics = handler.friendsStatistics(currentAccount.getId());
%>
<div id = "entireDiv">
    <div id = "topDiv">
        <h1>Quiz Website</h1>
        <ul>
            <li> <a style = " "; href="messenger.jsp"><img style="vertical-align: middle;width:40px;height:40px;"src="text-bubble.png"></a></li>
            <li> <a style = " "; href="createQuiz.jsp"><img style="vertical-align: middle;width:50px;height:50px;"src="createQuizIcon.png"></a></li>
            <li><a style=" "; href="profile.jsp?id=<%=currentAccount.getId()%>"><img style="vertical-align:middle;border-radius:50%;width:40px;height:40px;" src="<%=currentAccount.getImage()%>"> </a></li>
            <li><a style=" "; href="profile.jsp?id=<%=currentAccount.getId()%>"> <h4 style="vertical-align: middle;"><%=currentAccount.getUsername()%></h4></a></li>
        </ul>
    </div>
    <div id ="bodyDiv">
        <div id="leftDiv">
            <form style="margin-top:40px;" action = "searchUsersServlet" method = "get">
                <img style="display:inline;vertical-align: middle;width:40px;height:40px;"src="searchIcon.png">
                <p style="display:inline;"> Search Users by Username: </p>
                <input type="text" class="inputClass" id="usernameInput" name="usernameInput">
                <input type="submit" class="buttonClass inputClass" value = "Search" id="searchButton" name="searchButton">
            </form>
            <div id="usersDiv" class="announcementDiv">
                <%
                    if(accounts != null) {
                        for (int i = 0; i < accounts.size(); i++) {
                            out.println("<a href=\"profile.jsp?id=" + accounts.get(i).getId() + "\"><div>");
                            out.println("<img  src = \"" + accounts.get(i).getImage() + "\">");
                            out.println("<p style = \" top : 5%; left:50%;\">" + accounts.get(i).getUsername() + "</p>");
                            out.println("<p style = \" top : 50%; left:50%;\">"
                                    + accounts.get(i).getName() +" " + accounts.get(i).getSurname() +  "</p>");
                            out.println("</div></a>");
                        }
                    }
                %>

            </div>
        </div>

        <div id = "midDiv">
            <div id="topMarginDiv"></div>
            <div id="midList">
                <span class="label" onclick="showDiv(0)">Announcements</span>
                <span class="label" onclick="showDiv(1)">Recent Quizzes</span>
                <span class="label" onclick="showDiv(2)">My Quizzes</span>
                <span class="label" onclick="showDiv(3)">Popular Quizzes</span>
                <span class="label" onclick="showDiv(4)">My Activities</span>
                <span class="label" onclick="showDiv(5)">Friends' Activities</span>
            </div>
            <script>
                function showDiv(n) {
                    const labels = document.querySelectorAll('.label');
                    for(let i = 0; i<labels.length;i++){
                        labels[i].classList.remove('active');
                    }
                    labels[n].classList.add('active');
                    const divs = ["announcementDiv","recentQuizzesDiv","myQuizzesDiv","popularQuizzesDiv","recentActivitiesDiv","friendsActivity"];
                    for(let i = 0; i<divs.length;i++){
                        document.getElementById(divs[i]).classList.add('hidden');
                    }
                    document.getElementById(divs[n]).classList.remove('hidden');
                }
            </script>
            <div id="announcementDiv" class="hidden announcementDiv">
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
                            out.println("<div class=\"dividerBlue\">");
                            out.println("<a href=\"quizSummary.jsp?id=" + recentActivities.get(i).getQuizId() + "\"><p style = \" \" class=\"label\">Quiz title: "+ quiz.getTitle()+"</p></a>");
                            out.println("<p style = \"color:#124559\" >Score: "+ recentActivities.get(i).getScore()+"</p>");
                            out.println("<p style = \" color:#124559\" >Time: "+ (double) recentActivities.get(i).getTime() / 1000 +" seconds</p>");
                            out.println("</div>");
                        }
                    }
                %>
                </div>
            </div>
            <div id="friendsActivity" class="hidden">
                <div id="friendsActivityDiv" class="quizTitlesDiv">
                    <div id = "quizCreateActivity">
                        <%
                            if(friendsQuizzes != null){
                                for (int i = 0; i < friendsQuizzes.size(); i++) {
                                    Account author = handler.getAccount(friendsQuizzes.get(i).getCreatorID());
                                    out.println("<div class=\"bordered\">");
                                    out.println("<a href=\"profile.jsp?id=" + author.getId() + "\"><img src = \""+author.getImage()+"\"><p class= \" label \">"+ author.getUsername() +"</p></a>");
                                    out.println("<p style = \" \" >Created Quiz: </p><a href=\"quizSummary.jsp?id=" + friendsQuizzes.get(i).getId() + "\"><p class=\"label\">"+ friendsQuizzes.get(i).getTitle()+"</p></a>");
                                    out.println("<p style = \"color:#124559\" >"+ friendsQuizzes.get(i).getCreateDate()+"</p>");
                                    out.println("</div>");
                                }
                            }
                        %>
                    </div>
                    <div id="quizSubmitActivity">
                        <%
                            if(friendsStatistics != null){
                                for (int i = 0; i < friendsStatistics.size(); i++) {
                                    Account author = handler.getAccount(friendsStatistics.get(i).getAccountId());
                                    Quiz quiz = handler.getQuiz(friendsStatistics.get(i).getQuizId());
                                    out.println("<div class = \"bordered\">");
                                    out.println("<p style = \"display:inline; color:#124559;\">Quiz title: </p><a style=\"display:inline;\" href=\"quizSummary.jsp?id=" + quiz.getId() + "\"><p style=\"display:inline;\">"+ quiz.getTitle()+"</p></a>");
                                    out.println("<a style = \"display:block;\" href=\"profile.jsp?id=" + author.getId() + "\"><img src = \""+author.getImage()+"\"><p>"+ author.getUsername() +"</p></a>");
                                    out.println("<p style = \"color:#124559\" >Score: "+ friendsStatistics.get(i).getScore()+"</p>");
                                    out.println("<p style = \" color:#124559\" >Time: "+ (double) friendsStatistics.get(i).getTime() / 1000 +" seconds</p>");
                                    out.println("</div>");
                                }
                            }
                        %>
                    </div>

                </div>
            </div>
        </div>
        <div id="rightDiv">
            <div class="titles">
                <%
                    if(requests.size() == 0){
                        out.println("<h1>No Friend Requests</h1>");
                    } else {
                        out.println("<h1>Friend Requests</h1>");
                    }
                %>
            </div>
            <div class="grid-container">
                <%
                    for(int i = 0; i < requests.size(); i++) {
                %>
                <div class="grid-item dividerWhite">
                    <div>
                        <img src="<%= requests.get(i).getImage() %>">
                        <a href="profile.jsp?id=<%=requests.get(i).getId()%>"><p> <%= requests.get(i).getUsername() %></p></a>
                    </div>
                    <div>
                        <form action="FriendRequestResponseServlet" method="get">
                            <input type="hidden" name="value2" value="<%= requests.get(i).getId() %>">
                            <input class="button inputClass" type="submit" value="Accept" id="answerButton" name="value">
                        </form>
                        <form action="FriendRequestResponseServlet" method="get">
                            <input type="hidden" name="value2" value="<%= requests.get(i).getId() %>">
                            <input class="button inputClass" type="submit" value="Reject" id="answerButton2" name="value">
                        </form>
                    </div>

                </div>
                <%
                    }
                %>
            </div>
        </div>

    </div>

</div>

</body>
</html>