<%@ page import="quizpackage.model.DBHandler" %><%--
  Created by IntelliJ IDEA.
  User: giorgi kobakhia
  Date: 8/13/2023
  Time: 6:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/CSS/quiz.css" rel="stylesheet" type="text/css">
</head>
<body>

    <video autoplay muted loop id = "backgrVid">
        <source src="/CSS/backgr.mp4" type="video/mp4">
    </video>

    <%
        DBHandler handler = (DBHandler)application.getAttribute("handler");
        String title = request.getParameter("id");
    %>

    <div id = "entireDiv">
        <%
            out.println("<h1>" + title + "</h1>");
        %>

    </div>

</body>
</html>