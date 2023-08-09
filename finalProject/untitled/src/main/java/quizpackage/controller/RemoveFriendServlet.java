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

@WebServlet(name = "RemoveFriendServlet", urlPatterns = "/RemoveFriendServlet")
public class RemoveFriendServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBHandler handler = (DBHandler)req.getServletContext().getAttribute("handler");
        handler.debug("movida");
        Account profile = (Account)req.getSession().getAttribute("profile");
        Account currentUser = (Account)req.getSession().getAttribute("account");
        handler.removeFriend(currentUser,profile);
        RequestDispatcher dispatcher = req.getRequestDispatcher("profile.jsp?id="+profile.getId());
        dispatcher.forward(req,resp);
    }
}
