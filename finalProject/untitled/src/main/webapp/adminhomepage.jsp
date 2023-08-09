<%@ page import="quizpackage.model.Account" %><%--
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
            <form action = "removeOrPromoteUserServlet" method = "get">
                <p>Username: </p>
                <input type="text" id="usernameInput" name="usernameInput">
                <div id = "buttonsDiv">
                    <input type="submit" value = "Remove" id="removeButton" name="buttonClicked">
                    <input type="submit" value = "Promote" id="promoteButton" name="buttonClicked">
                </div>

                <%
                    Integer inp = (Integer)session.getAttribute("validUsername");
                    if(inp == null){
                        inp = 1;
                    }
                    String style = inp == 1 ? "\"display : none;\"" : "\"display : block;\"";
                    session.setAttribute("validUsername",null);
                %>
                <div id = "errorMessage">
                    <p  style = <%=style%>>Incorrect Username</p>
                </div>

            </form>

            <div id = "buttonsDiv2">
                <div>
                    <button type="button" onclick="location.href='addPostButtonServlet'" id="addPostButton">Add Post</button>
                </div>
            </div>

            <div id = "buttonsDiv3">
                <div>
                    <button type="button" onclick="location.href='statistics'" id="statisticsButton">Statistics</button>
                </div>
            </div>



        </div>
    </div>
</div>

</body>
</html>
