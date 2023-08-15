package quizpackage.model.quizzes;

public class QuestionResponse extends SingleAnswer{

    public QuestionResponse(String text,String answer,double grade){
        super(text,answer,grade);
    }
    @Override
    public String getQuestionClass(){
        return "QuestionResponse";
    }
}
