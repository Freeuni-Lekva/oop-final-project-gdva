package quizpackage;

import java.util.Date;

public class Announcement {
    String title;
    String text;
    String imgSrc;
    Date uploadDate;
    Account author;
    public Announcement(String title, String text,String imgSrc,Date uploadDate, Account author){
        this.title = title;
        this.text = text;
        this.imgSrc = imgSrc;
        this.uploadDate = uploadDate;
        this.author = author;
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
}
