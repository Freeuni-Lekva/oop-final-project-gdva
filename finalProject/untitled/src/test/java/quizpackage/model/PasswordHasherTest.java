package quizpackage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHasherTest {
    @Test
    void isPassword() {
        String password = "giomagaria";
        assertEquals(true, PasswordHasher.isPassword(password, PasswordHasher.hashPassword(password)));
        assertEquals(true, PasswordHasher.isPassword(password, PasswordHasher.hashPassword(password)));
        assertEquals(false, PasswordHasher.isPassword(password, PasswordHasher.hashPassword("gio bandzi")));
    }
}