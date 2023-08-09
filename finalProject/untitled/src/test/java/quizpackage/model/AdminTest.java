package quizpackage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminTest {

    Admin admin;
    @BeforeEach
    void setUp() {
        admin = new Admin("giorgi", "kobakhia", "gk", "gio123", 20, 4, "koba.jpg");
    }

    @Test
    void getName() {
        assertEquals("giorgi", admin.getName());
    }

    @Test
    void getSurname() {
        assertEquals("kobakhia", admin.getSurname());
    }

    @Test
    void getUsername() {
        assertEquals("gk", admin.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("gio123", admin.getPassword());
    }

    @Test
    void getAge() {
        assertEquals(20, admin.getAge());
    }

    @Test
    void getId() {
        assertEquals(4, admin.getId());
    }
    @Test
    void getImage() {
        assertEquals("koba.jpg", admin.getImage());
    }
}