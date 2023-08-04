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
            <form action = "AddAnnouncementServlet" method = "get">
                <p>Title: </p>
                <input type="text" id="titleInput" name="titleInput">
                <p>Announcement: </p>
                <textarea rows = "8" cols ="80" id="textInput" name="textInput"></textarea>
                <p>Image: </p>
                <input type="text" id="imageInput" name="imageInput">
                <div id = "buttonsDiv">
                    <input type="submit" value = "AddPost" id="addPostButton" name="addPostButton">
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>
