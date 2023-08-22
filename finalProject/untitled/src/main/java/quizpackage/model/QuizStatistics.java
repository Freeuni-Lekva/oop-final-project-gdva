package quizpackage.model;

import java.sql.Date;

public class QuizStatistics {
    private int quizId;
    private int accountId;
    private double score;
    private int time;
    private Date startDate;
    public QuizStatistics(int quiz_id, int account_id, double score, int time, Date startDate){
        this.quizId = quiz_id;
        this.accountId = account_id;
        this.score = score;
        this.time = time;
        this.startDate = startDate;
    }

    public int getQuizId(){
        return quizId;
    }

    public int getAccountId(){
        return accountId;
    }

    public double getScore(){
        return score;
    }

    public int getTime(){
        return time;
    }

    public Date getStartDate(){
        return startDate;
    }

    public String toString(){
        return "quiz_id: " + quizId + " account_id: " + accountId + " score: " + score + " time: "+ time + " start_date: " + startDate;
    }
}
