package quizpackage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    User user;
    @BeforeEach
    void setUp() {
        user = new User("giorgi", "kobakhia", "gk", "gio123", 20, 4, "koba.jpg");
    }

    @Test
    void getName() {
        assertEquals("giorgi", user.getName());
    }

    @Test
    void getSurname() {
        assertEquals("kobakhia", user.getSurname());
    }

    @Test
    void getUsername() {
        assertEquals("gk", user.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals("gio123", user.getPassword());
    }

    @Test
    void getAge() {
        assertEquals(20, user.getAge());
    }

    @Test
    void getId() {
        assertEquals(4, user.getId());
    }

    @Test
    void testEquals() {
        assertEquals(true, user.equals(new User("a", "a", "gk", "a", 10, 1, "asdf")));
        assertEquals(false, user.equals(new User("a", "a", "sdf", "a", 10, 1, "asdf")));
    }

    @Test
    void getImage() {
        assertEquals("koba.jpg", user.getImage());
    }
}