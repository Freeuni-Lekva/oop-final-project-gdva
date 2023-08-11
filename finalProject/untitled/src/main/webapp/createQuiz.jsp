<%--
  Created by IntelliJ IDEA.
  User: giorgi kobakhia
  Date: 8/10/2023
  Time: 5:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/CSS/createQuiz.css" rel="stylesheet" type="text/css">
</head>
<body>
<video autoplay muted loop id = "backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>

<div id = "entireDiv">
    <div id = "topDiv">
        <h1>Create Quiz</h1>
    </div>
    <div id ="bodyDiv">
        <form action = "addQuestion" method="get">
            <input class="addQuestionButton" type="submit" value="Add Question" id="addQuestionButton"
                   name="addQuestionButton">
        </form>
        <form action = "submitQuiz" method="get">
            <input class="submitQuizButton" type="submit" value="Submit Quiz" id="submitQuizButton"
                   name="submitQuizButton">
        </form>
    </div>

</div>

</body>
</html>
