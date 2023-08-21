<%--
  Created by IntelliJ IDEA.
  User: giorgi kobakhia
  Date: 8/8/2023
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/CSS/statistics.css" rel="stylesheet" type="text/css">
</head>
<body>
    <video autoplay muted loop id = "backgrVid">
        <source src="/CSS/backgr.mp4" type="video/mp4">
    </video>

    <div id = "central">
        <%
            Integer accounts = (Integer) session.getAttribute("accounts");
            Integer admins = (Integer) session.getAttribute("admins");
        %>
        <h1>Statistics: </h1>
        <ul>
            <li> <a style = " ";> Accounts: <%=accounts%></a></li>
            <li> <a style = " ";> Admins: <%=admins%></a></li>
        </ul>
    </div>
</body>
</html>
