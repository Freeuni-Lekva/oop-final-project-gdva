package quizpackage.controller;

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

@WebServlet(name = "addQuestionServlet", urlPatterns = "/addQuestionServlet")
public class AddQuestionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String questionType = req.getParameter("mySelect");
        DBHandler handler = (DBHandler) req.getServletContext().getAttribute("handler");
        handler.debug(questionType == null ? "yes" : "no");
        List<Question> questions = (List<Question>) req.getSession().getAttribute("questions");
        if(questions == null){
            System.out.println("is null");
            questions = new ArrayList<>();
        }
        if(questionType.equals("1")){
            handler.debug("1");
            String questionText = req.getParameter("questionResponseText");
            String questionAnswer = req.getParameter("questionResponseAnswer");
            double grade = Double.parseDouble(req.getParameter("questionResponseGrade"));
            QuestionResponse question = new QuestionResponse(questionText,questionAnswer,grade);
            questions.add(question);
        }
        else if(questionType.equals("2")){
            handler.debug("2");
            String questionText = req.getParameter("fillText");
            String questionAnswer = req.getParameter("fillAnswer");
            double grade = Double.parseDouble(req.getParameter("fillGrade"));
            FillTheBlank question = new FillTheBlank(questionText,grade,questionAnswer);
            questions.add(question);
        }
        else if(questionType.equals("3")){
            handler.debug("3");
            String questionText = req.getParameter("pictureText");
            String questionAnswer = req.getParameter("pictureAnswer");
            String image = req.getParameter("pictureURL");
            double grade = Double.parseDouble(req.getParameter("pictureGrade"));
            PictureResponse question = new PictureResponse(questionText,questionAnswer,image,grade);
            questions.add(question);
        }
        else if(questionType.equals("4")){
            handler.debug("4");
            int startId = Integer.parseInt(req.getParameter("numberOfChoices"));
            handler.debug("startID:"+startId);
            String questionText = req.getParameter("multipleText");
            handler.debug("text: "+questionText);
            String questionAnswer = req.getParameter("multipleAnswer");
            double grade = Double.parseDouble(req.getParameter("multipleGrade"));
            int num = Integer.parseInt(req.getParameter("choicesNumber"));
            handler.debug("choicesNumber: "+num);

            String[] choices = new String[num+1];
            choices[0] = questionText;
            int counter = 0;
            for(int i = startId+1; i<startId+num+1;i++){
                String choice = req.getParameter("choice"+i);
                handler.debug("choice is: "+choice);
                choices[++counter] = choice;
            }
            for(int i = 0; i < choices.length; i++){
                handler.debug("CHOICE " + i + ": " + choices[i]);
            }
            MultipleChoiceSingleAnswer question = new MultipleChoiceSingleAnswer(questionAnswer,grade,num,choices);
            questions.add(question);
        }

        req.getSession().setAttribute("questions",questions);
        RequestDispatcher dispatcher = req.getRequestDispatcher("createQuiz.jsp");
        dispatcher.forward(req,resp);
    }
}
