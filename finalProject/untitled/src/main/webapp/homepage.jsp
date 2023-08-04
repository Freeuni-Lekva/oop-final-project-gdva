<%@ page import="quizpackage.Account" %>
<%@ page import="quizpackage.Announcement" %>
<%@ page import="java.util.List" %>
<%@ page import="quizpackage.DBHandler" %>
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
            <li> <a style = " "; href="">Messages</a></li>
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
                        out.println("<div><a style = \" \"; href=\"announcement.jsp?id ="+announcements.get(i).getId()+" \">");
                        out.println("<img  src = \""+announcements.get(i).getImgSrc()+"\">");
                        out.println("<p style = \" top : 5%; left:50%;\">"+announcements.get(i).getAuthor().getUsername() + "</p>");
                        out.println("<p style = \" top : 50%; left:50%;\">"+announcements.get(i).getTitle() + "</p>");
                        out.println("</a></div>");
                    }
                %>

            </div>
        </div>
    </div>

</div>

</body>
</html>