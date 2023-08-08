package quizpackage;

import java.util.List;

public interface MultipleAnswerQuestion extends Question{
    List<String> getQuestionAnswers();
}
