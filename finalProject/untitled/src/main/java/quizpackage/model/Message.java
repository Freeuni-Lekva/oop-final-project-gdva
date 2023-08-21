package quizpackage.model;

import java.sql.Date;

public class Message {
    Account from;
    Account to;
    Date date;
    String text;
    String type;

    public Message(Account from, Account to, Date date, String text,String type){
        this.from = from;
        this.to = to;
        this.date = date;
        this.text = text;
        this.type = type;
    }

    public Account getFrom() {
        return from;
    }

    public Account getTo() {
        return to;
    }

    public Date getDate() {
        return date;
    }

    public String getText() {
        return text;
    }
    public String getType(){return type;}
}
