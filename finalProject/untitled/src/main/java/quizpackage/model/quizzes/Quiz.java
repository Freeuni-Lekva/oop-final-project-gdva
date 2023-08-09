package quizpackage;

import java.util.Collections;
import java.util.List;

public class Quiz {
    List<Question> questions;

    public Quiz(List<Question> questions){
        this.questions = questions;
    }

    public void randomizeQuestions(){
        Collections.shuffle(questions);
    }

    public List<Question> getQuestions(){
        return questions;
    }

    public double gradeQuiz(List<Answer> answers){
        double result = 0;
        for(Answer answer : answers){
            result += questions.get(answer.getQuestionNumber()).gradeQuestion(answer.getAnswers().toArray(new String[answer.getAnswers().size()]));
        }
        return result;
    }
}
