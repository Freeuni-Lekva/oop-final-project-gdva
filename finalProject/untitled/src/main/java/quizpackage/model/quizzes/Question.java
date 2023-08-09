package quizpackage.model.quizzes;

import java.util.List;

public interface Question {
     String getQuestionText();
    double answerPercent(String... answer);
    double getQuestionGrade();
    default double gradeQuestion(String... answer){
        return answerPercent(answer) * getQuestionGrade();
    }
}
