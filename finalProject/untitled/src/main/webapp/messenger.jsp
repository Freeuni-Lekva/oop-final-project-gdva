<%@ page import="quizpackage.Message" %>
<%@ page import="java.util.List" %>
<%@ page import="quizpackage.DBHandler" %>
<%@ page import="quizpackage.Account" %><%--
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
    DBHandler handler = (DBHandler) application.getAttribute("handler");
    Account currentAccount = (Account) session.getAttribute("account");
    List<Message> messageList = handler.getAccountMessages(currentAccount);
%>
<body>
    <div id = "entireDiv">
        <div id = "centralDiv">
            <div id = "leftDiv">
                <div>
                    <img src="fxala.jpg">
                    <div>
                        <h4>Nika</h4>
                        <p>nika sent you...</p>
                    </div>
                </div>
                <div>
                    <img src="lasa.jpg">
                    <div>
                        <h4>lasa</h4>
                        <p>lasa sent you...</p>
                    </div>
                </div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
                <div></div>
            </div>
            <div id="rightDiv">
                <div id="messageHeaderDiv">
                    <img src="fxala.jpg">
                    <div>
                        <h4>fxala</h4>
                    </div>
                </div>
                <div id="messageDiv">
                    <%
                        for(int i = 0; i<messageList.size();i++){
                            if(messageList.get(i).getFrom().equals(currentAccount)){
                                out.println("<div style = \" display:flex; justify-content: flex-end \"><div><p>"+messageList.get(i).getText()+"</p></div></div>");
                            }
                            else{
                                out.println("<div><img src = \""+messageList.get(i).getFrom().getImage() + "\">" +
                                        "<div><p>"+messageList.get(i).getText()+"</p></div></div>");
                            }
                        }
                    %>
<%--                    <div>--%>
<%--                        <img src="fxala.jpg">--%>
<%--                        <div><p>nika</p></div>--%>
<%--                    </div>--%>
<%--                    <div>--%>
<%--                        <img src="fxala.jpg">--%>
<%--                        <div>--%>
<%--                        <p>asdfsdfsdfsdff</p>--%>
<%--                    </div></div>--%>
<%--                    <div><div>--%>
<%--                        <p>nisdfgdfgsdfgsdfgdfgdfggsdfgdska</p>--%>
<%--                    </div></div>--%>
<%--                    <div><div>--%>
<%--                        <p>niksdfgsdfgssdfgdgssdfsgdfgdfgdfgdfgdfgdfgdfsgdfgsdfgsdfa</p>--%>
<%--                    </div></div>--%>
<%--                    <div><div>--%>
<%--                        <p>nisdgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfgfka</p>--%>
<%--                    </div></div>--%>
                </div>
                <script>
                    // Scroll the div with id "myDiv" to the bottom
                    $('#messageDiv').scrollTop($('#messageDiv')[0].scrollHeight);
                </script>
                <div id = "inputDiv">
                    <input id = "messageInput" type="text">
                    <input id = "messageButton" type="submit" value="Send!">
                </div>
            </div>
        </div>
    </div>
</body>
</html>
