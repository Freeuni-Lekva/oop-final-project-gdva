package quizpackage.model.quizzes;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceSingleAnswer extends SingleAnswer {
    private int choiceNumber = 0;
    private List<String> answers;
    private String fullQuestion;
    public MultipleChoiceSingleAnswer(String answer,double grade,int choiceNumber, String... text){
        super(convertText(text),answer,grade);
        this.choiceNumber = choiceNumber;
        answers = new ArrayList<>();
        if(text.length == 1) fullQuestion = text[0];
        fullQuestion = getQuestionText();
    }
    public int getChoiceNumber(){
        return choiceNumber;
    }
    static private String convertText(String... text){
        String res = "";
        if(text.length == 0){
            return res;
        }
        if(text.length == 1){
            if(text[0].indexOf("#") == -1) return text[0];
        }
        res += text[0];
        res += "#";
        for(int i = 1; i< text.length;i++){
            res += text[i];
            res+= "#";
        }
        return res.substring(0,res.length()-1);
    }
    @Override
    public String getQuestionClass(){
        return "MultipleChoiceSingleAnswer";
    }

    public List<String> getPossibleAnswers(){
        List<Integer> splitIndexes = new ArrayList<>();
        String reserve = fullQuestion;
        int sum = 0;
        for(int i = 0; i < choiceNumber; i++){
            int index = sum + fullQuestion.indexOf("#");
            splitIndexes.add(index);
            sum += fullQuestion.indexOf("#")+1;
            fullQuestion = fullQuestion.substring(fullQuestion.indexOf("#")+1);
        }
        for(int i =0; i < splitIndexes.size()-1; i++){
            answers.add(reserve.substring(splitIndexes.get(i)+1,splitIndexes.get(i+1)));
        }
        answers.add(reserve.substring(splitIndexes.get(splitIndexes.size()-1)+1));
        return answers;
    }
}
