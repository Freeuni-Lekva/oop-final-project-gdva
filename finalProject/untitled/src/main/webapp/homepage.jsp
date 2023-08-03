<%@ page import="quizpackage.Account" %>
<html>
<body>
<%
    Account currentAccount = (Account) request.getSession().getAttribute("account");

%>
<h1>HOME PAGE</h1>
<p><%=currentAccount.getName()%></p>
<p><%=currentAccount.getSurname()%></p>
<p><%=currentAccount.getId()%></p>
</body>
</html>