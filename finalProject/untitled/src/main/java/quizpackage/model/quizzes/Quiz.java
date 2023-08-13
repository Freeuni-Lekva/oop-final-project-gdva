package quizpackage.model.quizzes;

import java.util.Collections;
import java.util.List;

public class Quiz {
    List<Question> questions;
    String title;

    public Quiz(List<Question> questions, String title){
        this.questions = questions;
        this.title = title;
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
            result += questions.get(answer.getQuestionNumber()-1).gradeQuestion(answer.getAnswers().toArray(new String[answer.getAnswers().size()]));
        }
        return result;
    }

    public String getTitle(){
        return title;
    }
}
