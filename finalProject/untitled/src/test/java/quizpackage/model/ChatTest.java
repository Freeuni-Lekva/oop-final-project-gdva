package quizpackage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatTest {

    Account gio, fxala;
    Chat chat;
    @BeforeEach
    void setUp() {
        Date date = new Date(1,1,1);
        gio = new User("giorgi", "kobakhia", "gk", "gio123", 20, 4, "koba.jpg");
        fxala = new User("nika", "fxaladze", "nf", "fxala12", 20, 1, "fxala.jpg");
        ArrayList<Message> messages = new ArrayList<>();
        messages.add(new Message(gio, fxala, date, "baro fxala"));
        messages.add(new Message(fxala, gio, date, "baro"));
        messages.add(new Message(gio, fxala, date, "paka"));
        messages.add(new Message(fxala, gio, date, "paka"));
        chat = new Chat(fxala, gio, messages);
    }

    @Test
    void getReceiverAccount() {
        assertEquals(fxala, chat.getReceiverAccount());
    }

    @Test
    void getDisplayAccount() {
        assertEquals(gio, chat.getDisplayAccount());
    }

    @Test
    void getDisplayName() {
        assertEquals("giorgi ko...", chat.getDisplayName());
    }

    @Test
    void getDisplayMessage() {
        assertEquals("paka", chat.getDisplayMessage());
    }
}