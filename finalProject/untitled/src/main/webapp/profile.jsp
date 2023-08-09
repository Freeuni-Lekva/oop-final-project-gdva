<%@ page import="quizpackage.model.DBHandler" %>
<%@ page import="quizpackage.model.Announcement" %>
<%@ page import="quizpackage.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String id = request.getParameter("id");
    DBHandler handler = (DBHandler) request.getServletContext().getAttribute("handler");
    Account profileUser = handler.getAccount(Integer.valueOf(id));
    Account currentUser = (Account) session.getAttribute("account");
    session.setAttribute("profile", profileUser);
    Boolean isRequestSent = handler.isRequestSent(currentUser, profileUser);
    Boolean areAlreadyFriends = handler.areFriends(currentUser, profileUser);
%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile Page</title>
    <link href="/CSS/profile.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <h1><%= profileUser.getUsername() %></h1>
</header>
<video autoplay muted loop id="backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<div id="profile-picture">
    <img src="<%= profileUser.getImage() %>">
</div>
<div id="profile-info">
    <h1><%= profileUser.getName() + " " + profileUser.getSurname() %></h1>
    <div class="section">
        <h2>Age: <%= profileUser.getAge() %></h2>
    </div>
    <div class="section">
        <form action="SendRequestServlet" method="get">
            <% if (profileUser.getId() != currentUser.getId() && !areAlreadyFriends) {
                if (!isRequestSent) { %>
            <input class="friend-request-button" type="submit" value="Send Request" id="sendRequestButton"
                   name="sendRequestButton">
            <% } else { %>
            <h3 style="color: gold;">Request Sent</h3>
            <% }
            } %>
        </form>
        <% if (areAlreadyFriends) { %>
        <h3 style="color: gold;">Friends</h3>
        <form action="RemoveFriendServlet" method="get">
            <input class="friend-request-button" type="submit" value="Remove From Friends" id="removeFriendButton"
                   name="removeFriendButton">
        </form>
        <% } %>
    </div>
</div>
</body>
</html>
