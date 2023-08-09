package quizpackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DBHandlerTest {

    private DBHandler handler;
    @BeforeEach
    void setUp() {
        handler = new DBHandler(false);
        try {
            Connection connection = handler.getConnection();
            Statement statement = connection.createStatement();

            // clear all tables
            statement.executeUpdate("drop table if exists debug;");
            statement.executeUpdate("drop table if exists messages;");
            statement.executeUpdate("drop table if exists posts;");
            statement.executeUpdate("drop table if exists admins;");
            statement.executeUpdate("drop table if exists Accounts;");

            // create accounts table
            statement.executeUpdate("" +
                    "create table Accounts (\n" +
                    "\tid int primary key,\n" +
                    "    firstname VARCHAR(255),\n" +
                    "    surname VARCHAR(255),\n" +
                    "    username VARCHAR(255),\n" +
                    "    pass VARCHAR(255),\n" +
                    "    age int,\n" +
                    "    img varchar(255)\n" +
                    ");");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void accountManagement() {
        // accounts
        Account gio = new User("giorgi", "kobakhia", "gk", "gio123", 20, 4, "koba.jpg");
        Account fxala = new User("nika", "fxaladze", "nf", "fxala12", 20, 1, "fxala.jpg");

        // test getAccount and addAccount
        handler.addAccount(gio);
        Account res = handler.getAccount("gk");
        assertEquals(true, res.equals(gio));
        assertEquals(null, handler.getAccount("fxala"));
        assertEquals(true, gio.equals(handler.getAccount(4)));
        assertEquals(false, gio.equals(handler.getAccount(3)));

        handler.addAccount(fxala);
        assertEquals(true,fxala.equals(handler.getAccount("nf")));
        assertEquals(false,fxala.equals(handler.getAccount("gk")));
        assertEquals(true, fxala.equals(handler.getAccount(1)));
        assertEquals(false, gio.equals(handler.getAccount(1)));

        // test getAccounts
        List<Account> accounts = handler.getAccounts();
        assertEquals(2, accounts.size());

        // test containsUsername
        assertEquals(true, handler.containsUsername("nf"));
        assertEquals(true, handler.containsUsername("gk"));
        assertEquals(false, handler.containsUsername("gl"));
    }

    @Test
    void isAdmin() {
    }

    @Test
    void removeUser() {
    }

    @Test
    void promoteUser() {
    }

    @Test
    void getAnnouncements() {
    }

    @Test
    void getAnnouncement() {
    }

    @Test
    void getSingleAnnouncementFromResultSet() {
    }

    @Test
    void addAnnouncement() {
    }

    @Test
    void getMaxId() {
    }

    @Test
    void getMaxIdOFAnnouncements() {
    }

    @Test
    void getAccountMessages() {
    }

    @Test
    void debug() {
    }

    @Test
    void getMostRecentMessageAccount() {
    }

    @Test
    void getDialogue() {
    }

    @Test
    void castResultToMessage() {
    }

    @Test
    void addMessage() {
    }

    @Test
    void filterUsersByPrefix() {
    }

    @Test
    void addFriendRequest() {
    }

    @Test
    void isRequestSent() {
    }

    @Test
    void getReceivedFriendRequests() {
    }

    @Test
    void generateResponseToFriendRequest() {
    }

    @Test
    void areFriends() {
    }

    @Test
    void numberOfAccounts() {
    }

    @Test
    void numberOfAdmins() {
    }

    @Test
    void removeFriend() {
    }
}