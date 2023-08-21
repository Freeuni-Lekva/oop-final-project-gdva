<%--
  Created by IntelliJ IDEA.
  User: Giorgi
  Date: 21.08.2023
  Time: 16:23
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
<form action = "RemoveQuizOrClearHistoryServlet" method = "get">
  <p>Quiz Title: </p>
  <input type="text" id="quizTitleInput" name="quizTitleInput">
  <div id = "buttonsDiv">
    <input type="submit" value = "Remove" id="removeButton" name="buttonClicked">
    <input type="submit" value = "Clear History" id="clearHistoryButton" name="buttonClicked">
  </div>

  <%
    Integer inp = (Integer)session.getAttribute("validQuizTitle");
    if(inp == null){
      inp = 1;
    }
    String style = inp == 1 ? "\"display : none;\"" : "\"display : block;\"";
    session.setAttribute("validQuizTitle",null);
  %>
  <div id = "errorMessage">
    <p  style = <%=style%>>Incorrect Quiz Title</p>
  </div>
</form>
    </div>
    </div>
    </div>
</body>
</html>
