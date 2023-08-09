package quizpackage.model;

import java.sql.Date;

public class Announcement {
    String title;
    String text;
    String imgSrc;
    Date uploadDate;
    Account author;
    int id;
    public Announcement(String title, String text,String imgSrc,Date uploadDate, Account author, int id){
        this.title = title;
        this.text = text;
        this.imgSrc = imgSrc;
        this.uploadDate = uploadDate;
        this.author = author;
        this.id = id;
    }

    public Account getAuthor() {
        return author;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }
}
