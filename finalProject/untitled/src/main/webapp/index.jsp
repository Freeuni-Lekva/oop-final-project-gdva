<%@ page import="java.util.Random" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="quizpackage.model.DBHandler" %>
<%@ page import="quizpackage.model.Account" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.PrintWriter" %>
<html>
<head>
    <link href="/CSS/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<video autoplay muted loop id = "backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>

<%--<script>--%>
<%--    setInterval(function(){--%>
<%--        var r = Math.floor(Math.random() * 256);--%>
<%--        var g = Math.floor(Math.random() * 256);--%>
<%--        var b = Math.floor(Math.random() * 256);--%>
<%--        var rgb = "rgb(" + r + "," + g + "," + b + ")";--%>
<%--        document.getElementById("loginDiv").style.backgroundColor = rgb;--%>
<%--    },100);--%>
<%--</script>--%>

<div id="central">
    <div id="loginDiv">
        <div id="loginElements">
            <form id="loginForm" action="loginButtonServlet">
                <p>Login: </p>
                <input type="text" id="loginInput" name="loginInput">
                <p>Password: </p>
                <input type="password" id="passwordInput" name="passwordInput">
                <div id="buttonsDiv">
                    <div>
                        <input type="submit" value="Log In" id="loginButton" name="loginButton">
                    </div>
                    <div>
                        <button type="button" onclick="location.href='registerButtonServlet'" id="registerButton">Register</button>
                    </div>
                </div>
                <%
                    Integer inp = (Integer)session.getAttribute("validInput");
                    if(inp == null){
                        inp = 1;
                    }
                    String style = inp == 1 ? "\"display : none;\"" : "\"display : block;\"";
                    session.setAttribute("validInput",null);
                %>
                <div id = "errorMessage">
                    <p  style = <%=style%>>Incorrect Username or Password</p>
                </div>
            </form>
        </div>
    </div>
</div>


</body>
</html>
