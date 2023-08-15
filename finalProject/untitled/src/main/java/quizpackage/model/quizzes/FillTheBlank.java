package quizpackage.model.quizzes;

public class FillTheBlank extends SingleAnswer {

    public FillTheBlank(String text,double grade,String... answers ){
        super(text,answers[0].toLowerCase(),grade);
    }
    @Override
    public double answerPercent(String... answer){
        String[] answers = this.getQuestionAnswer().split("~");
        double counter = 0;
        for(int i = 0; i<answers.length;i++){
            if(answers[i].equals(answer[i].toLowerCase())){
                counter++;
            }
        }
        return answers.length == 0 ? 1 :  counter / (double) answers.length;
    }
    @Override
    public String getQuestionClass(){
        return "FillTheBlank";
    }


}
