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
        DBHandler handler = (DBHandler) request.getServletContext().getAttribute("handler");
        int accountsNum = handler.numberOfAccounts();
        int adminsNum = handler.numberOfAdmins();
        int quizzesTaken = handler.numberOfQuizzesTaken();

        RequestDispatcher dispatcher = request
                .getRequestDispatcher("statistics.jsp?accounts="+accountsNum+"&admins="+adminsNum+"&quizzes="+quizzesTaken);
        dispatcher.forward(request, response);
    }
}
