package quizpackage.controller;

import quizpackage.model.DBHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MultiPageServlet", urlPatterns = "/MultiPageServlet")
public class MultiPageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        DBHandler handler = (DBHandler)req.getServletContext().getAttribute("handler");
        RequestDispatcher dispatcher = req.getRequestDispatcher("quiz.jsp?id="+id);
        dispatcher.forward(req,resp);
    }
}
