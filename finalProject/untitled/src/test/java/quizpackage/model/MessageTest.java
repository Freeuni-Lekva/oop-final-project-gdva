package quizpackage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    Account gio, fxala;
    Date date;
    Message message;

    @BeforeEach
    void setUp() {
        date = new Date(1,1,1);
        gio = new User("giorgi", "kobakhia", "gk", "gio123", 20, 4, "koba.jpg");
        fxala = new User("nika", "fxaladze", "nf", "fxala12", 20, 1, "fxala.jpg");
        message = new Message(gio, fxala, date, "baro fxala");
    }

    @Test
    void getFrom() {
        assertEquals(gio, message.getFrom());
    }

    @Test
    void getTo() {
        assertEquals(fxala, message.getTo());
    }

    @Test
    void getDate() {
        assertEquals(date, message.getDate());
    }

    @Test
    void getText() {
        assertEquals("baro fxala", message.getText());
    }
}