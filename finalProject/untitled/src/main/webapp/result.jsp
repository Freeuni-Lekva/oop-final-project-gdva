<%--
  Created by IntelliJ IDEA.
  User: Giorgi
  Date: 15.08.2023
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    Double result = (Double) request.getSession().getAttribute("result");
%>
<head>
    <link href="/CSS/result.css" rel="stylesheet" type="text/css">
</head>
<body>
<video autoplay muted loop id="backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<h1>Your Grade:  <%=result%></h1>

</body>
</html>
