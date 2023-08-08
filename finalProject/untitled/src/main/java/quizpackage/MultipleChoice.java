package quizpackage;

public interface MultipleChoice extends Question {
     static String convertText(String... text){
        char cur = 'A';
        String res = "";
        for(String str : text){
            res+= cur + ". ";
            res+=str;
            res+= "\n";
            cur++;
        }
        return res;
    }
}
