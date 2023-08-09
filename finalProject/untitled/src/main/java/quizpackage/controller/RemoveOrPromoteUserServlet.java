package quizpackage.controller;

import quizpackage.model.DBHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "removeOrPromoteUserServlet", urlPatterns = "/removeOrPromoteUserServlet")
public class RemoveOrPromoteUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("usernameInput");
        DBHandler handler =(DBHandler)request.getServletContext().getAttribute("handler");
        String value = request.getParameter("buttonClicked");

        if(!handler.containsUsername(userName)){
            request.getSession().setAttribute("validUsername", 0);
            RequestDispatcher dispatcher = request.getRequestDispatcher("adminhomepage.jsp");
            dispatcher.forward(request,response);
            return ;
        }

        request.getSession().setAttribute("validUsername", 1);
        if(value.equals("Promote")){
            handler.promoteUser(userName);
        } else if(value.equals("Remove")) {
            handler.removeUser(userName);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminhomepage.jsp");
        dispatcher.forward(request,response);
    }
}
