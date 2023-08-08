package quizpackage;

import java.util.List;

public class Answer {

    int questionNumber;
    List<String> answers;

    public Answer(int number, List<String> answers){
        this.answers = answers;
        questionNumber = number;
    }

    public int getQuestionNumber(){
        return questionNumber;
    }

    public List<String> getAnswers(){
        return answers;
    }
}
