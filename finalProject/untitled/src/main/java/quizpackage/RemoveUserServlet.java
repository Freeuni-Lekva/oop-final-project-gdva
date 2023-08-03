package quizpackage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeUserServlet", urlPatterns = "/removeUserServlet")
public class RemoveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("usernameInput");
        DBHandler handler =(DBHandler)request.getServletContext().getAttribute("handler");
        handler.removeUser(userName);
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminhomepage.jsp");
        dispatcher.forward(request,response);
    }
}
