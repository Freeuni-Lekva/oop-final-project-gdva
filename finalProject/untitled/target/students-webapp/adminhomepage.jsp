<%@ page import="quizpackage.Account" %><%--
  Created by IntelliJ IDEA.
  User: Giorgi
  Date: 03.08.2023
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/CSS/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<video autoplay muted loop id = "backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<div id = "central">
    <div id = "loginDiv">
        <div id = "loginElements">
            <form action = "removeUserServlet" method = "get">
                <p>Username: </p>
                <input type="text" id="usernameInput" name="usernameInput">
                <div id = "buttonsDiv">
                    <input type="submit" value = "Remove" id="removeButton" name="removeButton">
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
