package quizpackage.model.quizzes;

import java.util.List;

public interface MultipleAnswerQuestion extends Question{
    List<String> getQuestionAnswers();
}
