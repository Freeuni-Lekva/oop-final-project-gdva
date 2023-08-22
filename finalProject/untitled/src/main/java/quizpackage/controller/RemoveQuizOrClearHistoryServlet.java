package quizpackage.controller;

import quizpackage.model.DBHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveQuizOrClearHistoryServlet", urlPatterns = "/RemoveQuizOrClearHistoryServlet")
public class RemoveQuizOrClearHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String quizTitle = request.getParameter("quizTitleInput");
        DBHandler handler =(DBHandler)request.getServletContext().getAttribute("handler");
        String value = request.getParameter("buttonClicked");

        if(!handler.containsQuizTitle(quizTitle)){
            request.getSession().setAttribute("validQuizTitle", 0);
            RequestDispatcher dispatcher = request.getRequestDispatcher("deletequiz.jsp");
            dispatcher.forward(request,response);
            return ;
        }

        request.getSession().setAttribute("validQuizTitle", 1);
        if(value.equals("Remove")){
            handler.removeQuiz(quizTitle);
        } else if(value.equals("Clear History")) {
            int quizId = handler.getQuizID(quizTitle);
            handler.clearQuizHistory(quizId);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminhomepage.jsp");
        dispatcher.forward(request,response);
    }
}
