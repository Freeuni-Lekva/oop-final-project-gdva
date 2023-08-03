<%@ page import="java.util.Random" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="quizpackage.DBHandler" %>
<%@ page import="quizpackage.Account" %>
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

<div id = "central">
    <div id = "loginDiv">
        <div id = "loginElements">
            <form action = "registerUserServlet" method = "get">
            <p>Name: </p>
            <input type="text" id="nameInput" name="nameInput">
            <p>Surname: </p>
            <input type="text" id="surnameInput" name="surnameInput">
            <p>Username: </p>
            <input type="text" id="usernameInput" name="usernameInput">
            <p>Password: </p>
            <input type="password" id="passwordInput" name="passwordInput">
            <p>Age: </p>
            <input type="text" id="ageInput" name="ageInput">
            <div id = "buttonsDiv">
                    <input type="submit" value = "Register" id="registerButton" name="registerButton">
            </div>
            </form>
            <div id = "errorMessage">
                <%
                    Integer inp = (Integer)session.getAttribute("validRegister");
                    if(inp == null){
                        inp = 1;
                    }
                    String style = inp == 1 ? "\"display : none;\"" : "\"display : block;\"";
                %>
                <p  style = <%=style%>>Username already exists</p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
