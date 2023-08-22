<%@ page import="quizpackage.Account" %>
<%@ page import="quizpackage.DBHandler" %>
<%@ page import="java.util.List" %>
<html>

<%
  Account currentAcc = (Account)session.getAttribute("account");
  DBHandler handler = (DBHandler)application.getAttribute("handler");
  List<Account> requests = handler.getReceivedFriendRequests(currentAcc);
%>
<head>
  <link href="/CSS/friendrequests.css" rel="stylesheet" type="text/css">
</head>
<body>
<video autoplay muted loop id="backgrVid">
  <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<div class="container">
  <div class="titles">
    <%
      if(requests.size() == 0){
        out.println("<h1>No Friend Requests</h1>");
      } else {
        out.println("<h1>Friend Requests</h1>");
      }
    %>
  </div>
  <div class="grid-container">
    <%
      for(int i = 0; i < requests.size(); i++) {
    %>
    <div class="grid-item">
      <img src="<%= requests.get(i).getImage() %>">
    </div>
    <div class="grid-item">
      <%= requests.get(i).getUsername() %>
    </div>
    <div class="grid-item">
      <form action="FriendRequestResponseServlet" method="get">
        <input type="hidden" name="value2" value="<%= requests.get(i).getId() %>">
        <input class="button" type="submit" value="Accept" id="answerButton" name="value">
      </form>
    </div>
    <div class="grid-item">
      <form action="FriendRequestResponseServlet" method="get">
        <input type="hidden" name="value2" value="<%= requests.get(i).getId() %>">
        <input class="button" type="submit" value="Reject" id="answerButton2" name="value">
      </form>
    </div>
    <%
      }
    %>
  </div>
</div>
</body>
</html>
