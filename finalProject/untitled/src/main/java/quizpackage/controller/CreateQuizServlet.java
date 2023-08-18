package quizpackage.controller;


import quizpackage.model.Account;
import quizpackage.model.DBHandler;
import quizpackage.model.quizzes.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "createQuizServlet",urlPatterns = "/createQuizServlet")
public class CreateQuizServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBHandler handler =(DBHandler) req.getServletContext().getAttribute("handler");
        String quizTitle = req.getParameter("quizTitle");
        String order = req.getParameter("questionOrder");
        String alignment = req.getParameter("questionAlignment");
        String answerType = req.getParameter("answerType");
        Account account = (Account)req.getSession().getAttribute("account");
        String description = req.getParameter("quizDescription");
        handler.addQuiz(quizTitle, order, alignment, answerType, account.getId(),description);
        List<Question> questions = (List<Question>) req.getSession().getAttribute("questions");
        for(int i = 0; i<questions.size();i++){
            String text = questions.get(i).getQuestionText();
            Double grade = questions.get(i).getQuestionGrade();
            String questionType = "";
            String image = "";
            String answer = "";
            int choicesNumber = 0;
            if(questions.get(i).getQuestionClass().equals("QuestionResponse")){
                QuestionResponse qr = (QuestionResponse) questions.get(i);
                questionType = "QuestionResponse";
                answer = qr.getQuestionAnswer();
            }
            else if(questions.get(i).getQuestionClass().equals("FillTheBlank")){
                FillTheBlank fb = (FillTheBlank) questions.get(i);
                questionType = "FillTheBlank";
                answer = fb.getQuestionAnswer();
            }
            else if(questions.get(i).getQuestionClass().equals("PictureResponse")){
                PictureResponse pr = (PictureResponse) questions.get(i);
                questionType = "PictureResponse";
                image = pr.getImage();
                answer = pr.getQuestionAnswer();
            }
            else if(questions.get(i).getQuestionClass().equals("MultipleChoiceSingleAnswer")){
                MultipleChoiceSingleAnswer mc = (MultipleChoiceSingleAnswer) questions.get(i);
                questionType = "MultipleChoiceSingleAnswer";
                choicesNumber = mc.getChoiceNumber();
                answer = mc.getQuestionAnswer();
            }
            handler.addQuestion(text, grade, questionType, choicesNumber, image, answer, handler.getQuizID(quizTitle));
            //handler.addLastQuestionToQuiz(quizTitle);
        }
        List<Question> newList = new ArrayList<>();
        req.getSession().setAttribute("questions",newList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("homepage.jsp");
        dispatcher.forward(req,resp);
    }
}
