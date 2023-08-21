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
            <li><%=post.getAuthor().getUsername() %></li>
        </ul>
    </div>
    <div id ="bodyDiv">

        <div id = "midDiv">
            <div id="topMarginDiv"></div>
            <h2><%=post.getTitle()%></h2>
            <div id="announcementDiv">
                <img src = "<%=post.getImgSrc()%>">
                <h4>
                    <%=post.getText()%>
                </h4>
            </div>
            <div id = "bottomDiv">
                <h3> <%=post.getUploadDate()%> </h3>
            </div>
        </div>
    </div>

</div>

</body>
</html>
