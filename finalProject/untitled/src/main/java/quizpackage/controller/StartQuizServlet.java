package quizpackage.controller;

import quizpackage.model.DBHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "StartQuizServlet", urlPatterns = "/StartQuizServlet")
public class StartQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quiz_id = req.getParameter("quiz_id");
        DBHandler handler = (DBHandler)req.getServletContext().getAttribute("handler");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = format.format(new Date());
        handler.debug(currentTime);
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("quiz.jsp?id="+quiz_id+"&start_time="+currentTime);
        dispatcher.forward(req,resp);
    }
}
