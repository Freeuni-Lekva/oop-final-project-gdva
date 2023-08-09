package quizpackage.model.quizzes;

public interface MultipleChoice extends Question {
    static String convertText(String... text){
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
