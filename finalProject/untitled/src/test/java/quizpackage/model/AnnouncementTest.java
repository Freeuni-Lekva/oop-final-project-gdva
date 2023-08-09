package quizpackage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class AnnouncementTest {

    Announcement announcement;
    Date date;
    Account jaba;
    @BeforeEach
    void setUp() {
        date = new Date(1, 1, 1);
        jaba = new User("jaba", "kiladze", "jk", "londoni", 38, 1, "jaba.jpg");
        announcement = new Announcement("qiravdeba", "qiravdeba 50 lari", "img.jpg", date, jaba, 1);
    }

    @Test
    void getAuthor() {
        assertEquals(jaba, announcement.getAuthor());
    }

    @Test
    void getUploadDate() {
        assertEquals(date, announcement.getUploadDate());
    }

    @Test
    void getImgSrc() {
        assertEquals("img.jpg", announcement.getImgSrc());
    }

    @Test
    void getText() {
        assertEquals("qiravdeba 50 lari", announcement.getText());
    }

    @Test
    void getTitle() {
        assertEquals("qiravdeba", announcement.getTitle());
    }

    @Test
    void getId() {
        assertEquals(1, announcement.getId());
    }
}