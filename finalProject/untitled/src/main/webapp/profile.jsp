<%@ page import="quizpackage.DBHandler" %>
<%@ page import="quizpackage.Announcement" %>
<%@ page import="quizpackage.Account" %><%--
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
    Account profileUser = handler.getAccount(Integer.valueOf(id));
%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile Page</title>
    <link href="/CSS/profile.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <h1><%=profileUser.getUsername()%></h1>
</header>
<video autoplay muted loop id = "backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<div id="profile-picture">
    <img src=<%=profileUser.getImage()%>>
</div>
<div id="profile-info">
    <h1><%=profileUser.getName() + " " + profileUser.getSurname()%></h1>
    <div class="section">
        <h2>Age: <%=profileUser.getAge()%></h2>
    </div>
</div>
</body>
</html>
