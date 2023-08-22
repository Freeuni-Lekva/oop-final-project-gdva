package quizpackage.controller;


import quizpackage.model.Account;
import quizpackage.model.DBHandler;
import quizpackage.model.quizzes.Quiz;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "challengeServlet",urlPatterns = "/challengeServlet")
public class ChallengeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBHandler handler = (DBHandler) req.getServletContext().getAttribute("handler");
        String friendUsername = req.getParameter("challengeField");
        Account friendAccount = handler.getAccount(friendUsername);
        Account userAccount = (Account) req.getSession().getAttribute("account");
        boolean areFriends = handler.areFriends(friendAccount,userAccount);
        //handler.debug("debug id is: "+ req.getParameter("id"));
        Quiz currentQuiz = handler.getQuiz(Integer.parseInt(req.getParameter("quiz_id")));
        if(areFriends){
            handler.addMessage(userAccount,friendAccount,"I challenge you to do " + currentQuiz.getTitle(),"challenge " + currentQuiz.getId());
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("quizSummary.jsp?id="+currentQuiz.getId());
        dispatcher.forward(req,resp);
    }
}
