package quizpackage.model.quizzes;

public class FillTheBlank extends SingleAnswer {

    public FillTheBlank(String text,double grade,String... answers ){
        super(text,answers[0].toLowerCase(),grade);
    }
    @Override
    public double answerPercent(String... answer){
        String[] answers = this.getQuestionAnswer().split("~");
        String[] userAnswer = answer[0].split("~");
        double counter = 0;
        for(int i = 0; i<answers.length;i++){
            if(answers[i].equals(userAnswer[i].toLowerCase())){
                counter++;
            }
        }
        return answers.length == 0 ? 1 :  counter / (double) userAnswer.length;
    }
    @Override
    public String getQuestionClass(){
        return "FillTheBlank";
    }


}
