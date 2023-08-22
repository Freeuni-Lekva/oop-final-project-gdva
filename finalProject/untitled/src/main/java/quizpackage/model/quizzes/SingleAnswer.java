package quizpackage.model.quizzes;

public class SingleAnswer implements SingleAnswerQuestion {
    String questionText;
    String questionAnswer;
    double grade;

    public SingleAnswer(String text,String answer,double grade){
        questionAnswer = answer.toLowerCase();
        questionText = text;
        this.grade = grade;
    }
    @Override
    public String getQuestionText() {
        return questionText;
    }

    @Override
    public double answerPercent(String... answer) {
        return questionAnswer.equals(answer[0].toLowerCase()) ? 1 : 0;
    }

    @Override
    public double getQuestionGrade() {
        return grade;
    }

    @Override
    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public String getQuestionClass(){
        return "SingleAnswer";
    }

    @Override
    public String getAnswer() {
        return questionAnswer;
    }
}
