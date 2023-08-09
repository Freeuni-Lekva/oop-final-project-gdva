package quizpackage;

import java.sql.Date;

public class Message {
    Account from;
    Account to;
    Date date;
    String text;

    public Message(Account from, Account to, Date date, String text){
        this.from = from;
        this.to = to;
        this.date = date;
        this.text = text;
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
}
