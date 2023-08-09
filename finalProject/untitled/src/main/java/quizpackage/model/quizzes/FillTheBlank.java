package quizpackage;

public class FillTheBlank extends SingleAnswer {

    public FillTheBlank(String text,double grade,String... answers ){
        super(text,convertAnswer(answers),grade);
    }

    private static String convertAnswer(String... answer){
        String res = "";
        for(String str : answer){
            res += str.toLowerCase();
            res += "~";
        }
        return res;
    }
}
