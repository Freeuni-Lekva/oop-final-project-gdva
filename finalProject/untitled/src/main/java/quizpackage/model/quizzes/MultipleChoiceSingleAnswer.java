package quizpackage.model.quizzes;

public class MultipleChoiceSingleAnswer extends SingleAnswer implements MultipleChoice {
    public MultipleChoiceSingleAnswer(String answer,double grade, String... text){
        super(MultipleChoice.convertText(text),answer,grade);
    }
}
