<%@ page import="quizpackage.model.Message" %>
<%@ page import="quizpackage.model.DBHandler" %>
<%@ page import="quizpackage.model.Account" %>
<%@ page import="java.util.*" %>
<%@ page import="quizpackage.model.Chat" %><%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 8/4/2023
  Time: 5:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/CSS/messenger.css" rel="stylesheet" type="text/css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<video autoplay muted loop id = "backgrVid">
    <source src="/CSS/backgr.mp4" type="video/mp4">
</video>
<%
    String obj = request.getParameter("to_account");
    Account to_account;
    DBHandler handler = (DBHandler) application.getAttribute("handler");
    Account currentAccount = (Account) session.getAttribute("account");
    List<Account> friends = (ArrayList<Account>)session.getAttribute("friends");
    List<Message> messageList = handler.getAccountMessages(currentAccount);
    if(obj == null){
      //  handler.debug("is null");
        to_account = handler.getMostRecentMessageAccount(currentAccount);
    }
    else{
      //  handler.debug("is not null");
        to_account = handler.getAccount(Integer.parseInt(obj));
    }
    if(to_account == null){
        handler.debug("is null");
    }
    session.setAttribute("to_account",to_account);
    Set<Integer> chat_ids = new HashSet<Integer>();
    List<Chat> chats = new ArrayList<Chat>();
    List<Message> toAccountMessages = new ArrayList<Message>();
    for(int i = messageList.size()-1; i>=0; i--){
        Account dest_account;

        if(messageList.get(i).getFrom().getId() == currentAccount.getId()){
            dest_account = messageList.get(i).getTo();
        }
        else{
            dest_account = messageList.get(i).getFrom();
        }
        if(!chat_ids.contains(dest_account.getId())){
            List<Message> chatMessages = handler.getDialogue(currentAccount,dest_account);
            Chat newChat = new Chat(currentAccount,dest_account,chatMessages);
            chats.add(newChat);
            if(newChat.getDisplayAccount().getId() == to_account.getId()){
                toAccountMessages = chatMessages;
            }
            chat_ids.add(dest_account.getId());
        }
    }
%>
<body>
    <div id = "entireDiv">
        <div id = "centralDiv">
            <div id = "leftDiv">
                <div id="searchBarDiv">
                    <form action="SearchFriendMessagesServlet" method="get">
                        <input id="searchInput" name="searchInput" type="text" placeholder="Search...">
                        <input id="searchButton" name="searchButton" type="submit" value="Search">
                    </form>
                </div>
                <%
                    for(int i = 0; i<chats.size();i++){
                        if(friends != null && friends.contains(chats.get(i).getDisplayAccount())) {
                            out.println("<a href = \"" + "messenger.jsp?to_account=" + chats.get(i).getDisplayAccount().getId() + "\">");
                            out.println("<div> <img src = \"" + chats.get(i).getDisplayAccount().getImage() + "\">");
                            out.println("<div><h4>" + chats.get(i).getDisplayName() + "</h4>");
                            out.println("<p>" + chats.get(i).getDisplayMessage() + "</p></div></div></a>");
                        }
                    }
                %>
            </div>
            <div id="rightDiv">
                <div id="messageHeaderDiv">
                    <img src="<%=to_account.getImage()%>">
                    <div>
                        <h4><%=to_account.getName() + " " + to_account.getSurname()%></h4>
                    </div>
                </div>
                <div id="messageDiv">
                    <%
                        for(int i = 0; i<toAccountMessages.size();i++){
                            if(toAccountMessages.get(i).getFrom().equals(currentAccount)){
                                if(toAccountMessages.get(i).getType().startsWith("challenge")){

                                    out.println("<div style = \" display:flex; justify-content: flex-end \"><div><a href = \"quizSummary.jsp?id="+toAccountMessages.get(i).getType().split(" ")[1]+"\"><p>"+toAccountMessages.get(i).getText()+"</a></p></div></div>");
                                }
                                else if(toAccountMessages.get(i).getType().equals("text")){
                                    out.println("<div style = \" display:flex; justify-content: flex-end \"><div><p>"+toAccountMessages.get(i).getText()+"</p></div></div>");
                                }
                            }
                            else{
                                if(toAccountMessages.get(i).getType().startsWith("challenge")){
                                    out.println("<div><img src = \""+toAccountMessages.get(i).getFrom().getImage() + "\">" +
                                            "<div><a href = \"quizSummary.jsp?id=\""+toAccountMessages.get(i).getType().split(" ")[1]+"\"><p>"+toAccountMessages.get(i).getText()+"</a></p></div></div>");
                                }
                                else if(toAccountMessages.get(i).getType().equals("text")){
                                    out.println("<div><img src = \""+toAccountMessages.get(i).getFrom().getImage() + "\">" +
                                            "<div><p>"+toAccountMessages.get(i).getText()+"</p></div></div>");
                                }

                            }
                        }
                    %>

                </div>
                <script>
                    // Scroll the div with id "myDiv" to the bottom
                    $('#messageDiv').scrollTop($('#messageDiv')[0].scrollHeight);
                </script>
                <div id = "inputDiv">
                    <form action = "messageButtonServlet" method="post" >
                        <input id = "messageInput" name="messageInput" type="text">
                        <input id = "messageButton" name="messageButton" type="submit" value="Send!" >
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
