<%@ page import="quizpackage.model.Account" %>
<%@ page import="quizpackage.model.Announcement" %>
<%@ page import="java.util.List" %>
<%@ page import="quizpackage.model.DBHandler" %>
<html>
<head>
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
        <div id = "midDiv">
            <div id="topMarginDiv"></div>
            <div id="midList"></div>
            <div id="announcementDiv">
                <%
                    for(int i = 0; i<announcements.size();i++){
                        out.println("<a href=\"announcement.jsp?id="+announcements.get(i).getId()+"\"><div>");
                        out.println("<img  src = \""+announcements.get(i).getImgSrc()+"\">");
                        out.println("<p style = \" top : 5%; left:50%;\">"+announcements.get(i).getAuthor().getUsername() + "</p>");
                        out.println("<p style = \" top : 50%; left:50%;\">"+announcements.get(i).getTitle() + "</p>");
                        out.println("</div></a>");
                    }
                %>

            </div>
        </div>
    </div>

</div>

</body>
</html>