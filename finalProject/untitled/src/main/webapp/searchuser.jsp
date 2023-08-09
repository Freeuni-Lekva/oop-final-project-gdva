<%@ page import="quizpackage.model.Account" %>
<%@ page import="quizpackage.model.Announcement" %>
<%@ page import="java.util.List" %>
<%@ page import="quizpackage.model.DBHandler" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <link href="/CSS/homepage.css" rel="stylesheet" type="text/css">
</head>
<body>
<video autoplay muted loop id = "backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<%
    DBHandler handler = (DBHandler)application.getAttribute("handler");
    Account currentAccount = (Account)request.getSession().getAttribute("account");
    List<Account> accounts = (List<Account>) request.getSession().getAttribute("accounts");
%>
<div id = "entireDiv">
    <div id = "topDiv">
        <h1>Search Users</h1>
        <ul>
            <li><%=currentAccount.getUsername()%></li>
        </ul>
    </div>
    <div id ="bodyDiv">
        <div id = "midDiv">
            <div id="topMarginDiv">
                <form action = "searchUsersServlet" method = "get">
                    <p style = "color: azure;"> User Name: </p>
                    <input type="text" id="usernameInput" name="usernameInput">
                    <input type="submit" value = "Search" id="searchButton" name="searchButton">
                </form>
            </div>
            <div id="midList"> </div>
            <div id="announcementDiv">
                <%
                    if(accounts != null) {
                        for (int i = 0; i < accounts.size(); i++) {
                            out.println("<a href=\"profile.jsp?id=" + accounts.get(i).getId() + "\"><div>");
                            out.println("<img  src = \"" + accounts.get(i).getImage() + "\">");
                            out.println("<p style = \" top : 5%; left:50%;\">" + accounts.get(i).getUsername() + "</p>");
                            out.println("<p style = \" top : 50%; left:50%;\">"
                                    + accounts.get(i).getName() +" " + accounts.get(i).getSurname() +  "</p>");
                            out.println("</div></a>");
                        }
                    }
                %>

            </div>
        </div>
    </div>

</div>

</body>
</html>