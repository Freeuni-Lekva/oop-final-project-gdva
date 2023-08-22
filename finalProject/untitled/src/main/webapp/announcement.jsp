<%@ page import="quizpackage.model.DBHandler" %>
<%@ page import="quizpackage.model.Announcement" %><%--
  Created by IntelliJ IDEA.
  User: Giorgi
  Date: 03.08.2023
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
    DBHandler handler = (DBHandler) request.getServletContext().getAttribute("handler");
    Announcement post = handler.getAnnouncement(id);
%>
<html>
<head>
    <link href="/CSS/announcement.css" rel="stylesheet" type="text/css">
    <title><%= post.getTitle()%> </title>
</head>
<body>
<video autoplay muted loop id = "backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<div id = "entireDiv">
    <div id = "topDiv">
        <h1>Announcement</h1>
        <ul>
            <li> <a style = " "; href="homepage.jsp"><img style="vertical-align: middle;width:40px;height:40px;"src="homepage.png"></a></li>
            <li><a style=" "; href="profile.jsp?id=<%=post.getAuthor().getId()%>"><img style="vertical-align:middle;border-radius:50%;width:40px;height:40px;" src="<%=post.getAuthor().getImage()%>"> </a></li>
            <li><a style=" "; href="profile.jsp?id=<%=post.getAuthor().getId()%>"> <h4 style="vertical-align: middle;"><%=post.getAuthor().getUsername()%></h4></a></li>
            <%--            <li><%=post.getAuthor().getUsername() %></li>--%>
        </ul>
    </div>
    <div id ="bodyDiv">

        <div id = "midDiv">
            <div id="topMarginDiv"></div>
            <p style="font-size:40px; color:white;"><%=post.getTitle()%></p>
            <div id="announcementDiv">
                <img src = "<%=post.getImgSrc()%>">
                <h4>
                    <%=post.getText()%>
                </h4>
            </div>
            <div id = "bottomDiv">
                <h3> <%=post.getUploadDate()%> </h3>
            </div>
            <a href="homepage.jsp">
                <input type="submit" value="Back To Home Page">
            </a>
        </div>
    </div>

</div>

</body>
</html>
