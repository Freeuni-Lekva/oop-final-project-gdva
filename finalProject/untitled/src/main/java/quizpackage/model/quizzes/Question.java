package quizpackage.model.quizzes;

import java.util.List;

public interface Question {
    String getQuestionText();
    double answerPercent(String... answer);
    double getQuestionGrade();
    default double gradeQuestion(String... answer){
        System.out.println("in gradeQuestion");
        return answerPercent(answer) * getQuestionGrade();
    }
    String getQuestionClass();
    String getAnswer();
}
