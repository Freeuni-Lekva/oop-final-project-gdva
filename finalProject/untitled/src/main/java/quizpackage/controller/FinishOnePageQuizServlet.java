package quizpackage.controller;

import quizpackage.model.DBHandler;
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
        DBHandler handler = (DBHandler)req.getServletContext().getAttribute("handler");
        String value = req.getParameter("buttonValue");
        String id = (String)req.getSession().getAttribute("quizID");
        ArrayList<Question> questions = (ArrayList<Question>)req.getSession().getAttribute("Questions");

        if(value != null && value.equals("Confirm")){
            String questionId = req.getParameter("questionId");
            Question question = questions.get(Integer.parseInt(questionId));
            req.getSession().setAttribute("ImmediateResponseQuestion",question);

            int idx = (int)req.getSession().getAttribute("curIdx");
            String parameterName = String.valueOf(idx); // Change this line
            String answer = req.getParameter(parameterName);
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("immediateresponse.jsp?answer="+answer+"&questionId="+questionId);
            dispatcher.forward(req,resp);
            return;
        }

        if(value != null && value.equals("Next")){
            int idx = (int)req.getSession().getAttribute("curIdx");
            String parameterName = String.valueOf(idx); // Change this line
            String answer = req.getParameter(parameterName);

            handler.debug("IDX: " + idx);
            handler.debug("BEFORE"+answer);
            req.getSession().setAttribute("q"+(idx-1),answer);

            if(req.getSession().getAttribute("result") == null){
                if(answer != null) {
                    double curPoint = questions.get(idx-1).gradeQuestion(answer);
                    req.getSession().setAttribute("result",curPoint);
                }
            } else {
                double result = (double) req.getSession().getAttribute("result");
                if(answer != null) {
                    req.getSession().setAttribute("result",
                            result+questions.get(idx-1).gradeQuestion(answer));
                }
            }
            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("quiz.jsp?id="+id+"&buttonValue=Next");
            dispatcher.forward(req,resp);
            return;
        }

        if(req.getSession().getAttribute("result") != null){
            int idx = (int)req.getSession().getAttribute("curIdx");
            String answer = req.getParameter(idx+"");
            req.getSession().setAttribute("q"+(idx-1),answer);

            if(answer != null) {
                req.getSession().setAttribute("result",
                        (double)req.getSession().getAttribute("result")+
                                questions.get(idx-1).gradeQuestion(answer));
            }
            handler.debug(answer);
            req.getSession().setAttribute("Result",req.getSession().getAttribute("result"));
            RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
            dispatcher.forward(req,resp);
            return;
        }
        Double result = 0.;
        for(int i = 0; i < questions.size(); i++){
            String parameterName = String.valueOf(i + 1); // Change this line
            String answer = req.getParameter(parameterName);
            req.getSession().setAttribute("q"+i,answer);
            if(answer == null) continue;
            result += questions.get(i).gradeQuestion(answer);
        }
        req.getSession().setAttribute("Result",result);
        RequestDispatcher dispatcher = req.getRequestDispatcher("result.jsp");
        dispatcher.forward(req,resp);
        for(int i = 0; i < questions.size(); i++){
            req.getSession().setAttribute("q"+i,null);
        }
    }
}
