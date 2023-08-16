package quizpackage.controller;

import quizpackage.model.quizzes.Question;
import quizpackage.model.quizzes.SingleAnswer;
import quizpackage.model.quizzes.SingleAnswerQuestion;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "FinishOnePageQuizServlet", urlPatterns = "/FinishOnePageQuizServlet")
public class FinishOnePageQuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<Question> questions = (ArrayList<Question>)req.getSession().getAttribute("Questions");
        Double result = 0.;
        for(int i = 0; i < questions.size(); i++){
            String parameterName = String.valueOf(i + 1); // Change this line
            String answer = req.getParameter(parameterName);
            System.out.println(answer);
            System.out.println(questions.get(i).getQuestionClass());
            result += questions.get(i).gradeQuestion(answer);
//            String realAnswer = ((SingleAnswer)questions.get(i)).getQuestionAnswer();
//            if(answer == null) continue;
//            if(answer.equals(realAnswer)){
//                result += questions.get(i).getQuestionGrade();
//            }
        }
        req.getSession().setAttribute("result",result);
        RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
        dispatcher.forward(req,resp);
    }
}
