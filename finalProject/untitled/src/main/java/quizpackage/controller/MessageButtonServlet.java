package quizpackage.controller;

import quizpackage.model.Account;
import quizpackage.model.DBHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "messageButtonServlet",urlPatterns = "/messageButtonServlet")
public class MessageButtonServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String text = req.getParameter("messageInput");
        if(text.length() != 0){
            DBHandler handler = (DBHandler)req.getServletContext().getAttribute("handler");
            handler.addMessage((Account)req.getSession().getAttribute("account"),(Account)req.getSession().getAttribute("to_account"),text,"text");
        }
        if(req.getParameter("to_account_id") == null){
            RequestDispatcher dispatcher = req.getRequestDispatcher("messenger.jsp");
            dispatcher.forward(req,resp);
        }
        else{
            RequestDispatcher dispatcher = req.getRequestDispatcher("messenger.jsp?id="+req.getParameter("to_account_id"));
            dispatcher.forward(req,resp);
        }
    }
}
