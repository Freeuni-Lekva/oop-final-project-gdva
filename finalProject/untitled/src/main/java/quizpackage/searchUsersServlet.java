package quizpackage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "searchUsersServlet",urlPatterns = "/searchUsersServlet")
public class searchUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("usernameInput");
        DBHandler handler = (DBHandler) req.getServletContext().getAttribute("handler");
        List<Account> accounts = handler.filterUsersByPrefix(username);
        req.getSession().setAttribute("accounts",accounts);
        RequestDispatcher dispatcher  = req.getRequestDispatcher("searchuser.jsp");
        dispatcher.forward(req,resp);
    }
}
