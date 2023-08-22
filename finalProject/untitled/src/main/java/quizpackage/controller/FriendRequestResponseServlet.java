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

@WebServlet(name = "FriendRequestResponseServlet", urlPatterns = "/FriendRequestResponseServlet")
public class FriendRequestResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String response = req.getParameter("value");
        String id = req.getParameter("value2");
        Account curAcc = (Account)req.getSession().getAttribute("account");
        DBHandler handler = (DBHandler)req.getServletContext().getAttribute("handler");
        handler.generateResponseToFriendRequest(response.equals("Accept"),Integer.parseInt(id),curAcc.getId());
        RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
        dispatcher.forward(req,resp);
    }
}
