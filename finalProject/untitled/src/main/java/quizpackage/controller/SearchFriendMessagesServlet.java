package quizpackage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchFriendMessagesServlet",urlPatterns = "/SearchFriendMessagesServlet")
public class SearchFriendMessagesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("searchInput");
        DBHandler handler = (DBHandler)req.getServletContext().getAttribute("handler");
        List<Account> accounts =handler.filterUsersByPrefix(username);
        Account curAccount = (Account)req.getSession().getAttribute("account");
        List<Account> friends = new ArrayList<>();
        for(int i = 0; i < accounts.size(); i++){
            if(handler.areFriends(curAccount,accounts.get(i))){
                friends.add(accounts.get(i));
            }
        }
        req.getSession().setAttribute("friends",friends);
        RequestDispatcher dispatcher = req.getRequestDispatcher("messenger.jsp");
        dispatcher.forward(req,resp);
    }
}
