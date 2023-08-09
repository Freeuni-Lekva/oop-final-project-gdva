package quizpackage.model.quizzes;

import quizpackage.model.DBHandler;

public class mainclass {

    public static void main(String[] args){
        DBHandler handler = new DBHandler();
        handler.getAccounts();
    }
}
