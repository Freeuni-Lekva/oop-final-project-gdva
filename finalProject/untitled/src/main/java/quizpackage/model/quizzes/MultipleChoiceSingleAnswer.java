package quizpackage.model.quizzes;

public class MultipleChoiceSingleAnswer extends SingleAnswer {
    private int choiceNumber = 0;
    public MultipleChoiceSingleAnswer(String answer,double grade,int choiceNumber, String... text){
        super(convertText(text),answer,grade);
        this.choiceNumber = choiceNumber;
    }
    public int getChoiceNumber(){
        return choiceNumber;
    }
    static private String convertText(String... text){
        char cur = 'A';
        String res = "";
        if(text.length == 0){
            return res;
        }
        res += text[0];
        res += " \n";
        for(int i = 1; i<text.length;i++){
            res+= cur + ". ";
            res+=text[i];
            res+= " \n";
            cur++;
        }

        return res;
    }
}
