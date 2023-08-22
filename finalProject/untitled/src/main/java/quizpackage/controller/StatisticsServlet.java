package quizpackage.controller;

import quizpackage.model.DBHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "statisticsServlet", urlPatterns = "/statistics")
public class StatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("statistics.jsp");
        DBHandler handler = (DBHandler) request.getServletContext().getAttribute("handler");
        request.getSession().setAttribute("accounts", handler.numberOfAccounts());
        request.getSession().setAttribute("admins", handler.numberOfAdmins());
        dispatcher.forward(request, response);
    }
}
