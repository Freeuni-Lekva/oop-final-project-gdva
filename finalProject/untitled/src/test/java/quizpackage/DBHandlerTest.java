package quizpackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import quizpackage.model.*;
import quizpackage.model.quizzes.Quiz;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBHandlerTest {

    private DBHandler handler;
    private Connection connection;
    private Statement statement;
    @BeforeEach
    void setUp() {
        handler = new DBHandler(false);
        try {
            connection = handler.getConnection();
            statement = connection.createStatement();

            // clear all tables
            statement.executeUpdate("drop table if exists debug;");
            statement.executeUpdate("drop table if exists messages;");
            statement.executeUpdate("drop table if exists posts;");
            statement.executeUpdate("drop table if exists admins;");
            statement.executeUpdate("drop table if exists sent_requests;");
            statement.executeUpdate("drop table if exists friends;");
            statement.executeUpdate("drop table if exists quiz_history;");
            statement.executeUpdate("drop table if exists questions;");
            statement.executeUpdate("drop table if exists quizzes;");
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


            // create admins table
            statement.executeUpdate("create table admins (\n" +
                    "    id int,\n" +
                    "    foreign key (id) references accounts(id)\n" +
                    ");");

            // add accounts
            statement.executeUpdate("insert into accounts(id, firstname, surname, username, pass, age, img) values\n" +
                    "(1,'baro','lasha','barolasha','barolasha',20,'baro.jpg'),\n" +
                    "(2,'beka','beka','bekabeka','bekabeka',20,'beka.jpg'),\n" +
                    "(3,'beso','beso','besobeso','besobeso',20,'beso.jpg'),\n" +
                    "(4,'cima','cima','cimacima','cimacima',20,'cima.jpg');");

            // add admins
            statement.executeUpdate("insert into admins(id) values (1), (2), (3);");


            // add posts table
            statement.executeUpdate("create table posts(\n" +
                    "\tid int primary key,\n" +
                    "\ttitle varchar(255),\n" +
                    "    plot varchar(1000),\n" +
                    "    img varchar(255),\n" +
                    "    upload_date Date,\n" +
                    "    author_id int,\n" +
                    "    foreign key (author_id) references accounts(id)\n" +
                    ");");

            // add posts
            statement.executeUpdate("insert into posts(id,title,plot,img,upload_date,author_id) values\n" +
                    "(2,'kachoki lasa','kachoki lasa dakachokda kachaobit sakachokeshi','lasa.jpg',sysdate(),1),\n" +
                    "(3,'koba koba','gamarjobat me var koba da ravi ra ratomac ara','koba.jpg',sysdate(),1),\n" +
                    "(4,'fxalla fxala','fxalas sicxe aq','fxala.jpg',sysdate(),1);");


            // create messages table
            statement.executeUpdate("create table messages(\n" +
                    "\tid int auto_increment primary key,\n" +
                    "    from_id int,\n" +
                    "    to_id int,\n" +
                    "    send_date datetime,\n" +
                    "    txt varchar(1000),\n" +
                    "    message_type varchar(1000),\n" +
                    "    foreign key(from_id) references accounts(id),\n" +
                    "    foreign key(to_id) references accounts(id)\n" +
                    ");");

            // create debug table
            statement.executeUpdate("create table debug(\n" +
                    "\ttxt varchar(255)\n" +
                    ");");

            // create sent_requests table
            statement.executeUpdate("create table sent_requests(\n" +
                    "                              sender_id int,\n" +
                    "                              receiver_id int,\n" +
                    "                              foreign key (sender_id) references accounts(id),\n" +
                    "                              foreign key (sender_id) references accounts(id)\n" +
                    ");");

            // create friends table
            statement.executeUpdate("create table friends(\n" +
                    "                        first_friend_id int,\n" +
                    "                        second_friend_id int,\n" +
                    "                        foreign key (first_friend_id) references accounts(id),\n" +
                    "                        foreign key (second_friend_id) references accounts(id)\n" +
                    ");");

            // create quizzes table
            connection.createStatement().executeUpdate("create table quizzes(\n" +
                    "id int auto_increment primary key,\n" +
                    "title varchar(55),\n" +
                    "question_order varchar(55),\n" +
                    "question_alignment varchar(55),\n" +
                    "answer_type varchar(55),\n" +
                    "creator_id int,\n" +
                    "quiz_description varchar(1000),\n" +
                    "create_date datetime,\n" +
                    "foreign key (creator_id) references accounts(id)"+
                    ");");


            // create quiz history table
            connection.createStatement().executeUpdate("create table quiz_history(\n" +
                    "\tquiz_id int,\n" +
                    "    account_id int,\n" +
                    "    score double,\n" +
                    "    time int,\n" +
                    "    start_date datetime, \n" +
                    "    foreign key(quiz_id) references quizzes(id),\n" +
                    "    foreign key(account_id) references accounts(id)\n" +
                    ");");

            connection.createStatement().executeUpdate("create table questions(\n" +
                    "\tquestion_id  int auto_increment primary key,\n" +
                    "\tquestion_type varchar(55),\n" +
                    "    question_text varchar(255),\n" +
                    "    question_answer varchar(255),\n" +
                    "    question_image varchar(255),\n" +
                    "    question_choices_number int,\n" +
                    "    question_grade double,\n" +
                    "    quiz_id int\n" +
                    ");");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void accountManagement() {
        // accounts
        Account gio = new User("giorgi", "kobakhia", "gk", PasswordHasher.hashPassword("gio123"), 20, 5, "koba.jpg");
        Account fxala = new User("nika", "fxaladze", "nf", PasswordHasher.hashPassword("fxala12"), 20, 6, "fxala.jpg");

        // test getAccount(int id), getAccount(String username) and addAccount
        handler.addAccount(gio);
        Account res = handler.getAccount("gk");
        assertEquals(true, res.equals(gio));
        assertEquals(null, handler.getAccount("fxala"));
        assertEquals(true, gio.equals(handler.getAccount(5)));
        assertEquals(false, gio.equals(handler.getAccount(3)));

        handler.addAccount(fxala);
        assertEquals(true,fxala.equals(handler.getAccount("nf")));
        assertEquals(false,fxala.equals(handler.getAccount("gk")));
        assertEquals(true, fxala.equals(handler.getAccount(6)));
        assertEquals(false, gio.equals(handler.getAccount(1)));

        // test getAccount(String username, String password)
        assertEquals(gio, handler.getAccount("gk", "gio123"));
        assertEquals(fxala, handler.getAccount("nf", "fxala12"));
        assertEquals(null, handler.getAccount("gk", "gio12"));

        // test getAccounts
        List<Account> accounts = handler.getAccounts();
        assertEquals(6, accounts.size());

        // test containsUsername
        assertEquals(true, handler.containsUsername("nf"));
        assertEquals(true, handler.containsUsername("gk"));
        assertEquals(false, handler.containsUsername("gl"));
    }

    @Test
    void isAdmin() {
        // test isAdmin
        assertEquals(true, handler.isAdmin(2));
        assertEquals(false, handler.isAdmin(4));
    }

    @Test
    void removeUser() {
        // remove user
        handler.removeUser("cimacima");
        assertEquals(null, handler.getAccount("cimacima"));

        // try removing admin
        handler.removeUser("barolasha");
        assertEquals(handler.getAccount(1), handler.getAccount("barolasha"));
    }

    @Test
    void promoteUser() {
        // promote cima
        assertEquals(false, handler.isAdmin(4));
        handler.promoteUser("cimacima");
        assertEquals(true, handler.isAdmin(4));

        // promote admin
        assertEquals(true, handler.isAdmin(1));
        handler.promoteUser("barolasha");
        assertEquals(true, handler.isAdmin(1));
    }

    @Test
    void announcementManagement() {
        // check initial announcements
        assertEquals(3, handler.getAnnouncements().size());

        // add new announcement
        java.sql.Date date = new java.sql.Date(1,1,1);
        Announcement announcement = new Announcement("qiravdeba", "qiravdeba 50 lari", "img.jpg", (java.sql.Date)date, handler.getAccount(1), 1);

        // test getAnnouncement
        handler.addAnnouncement(announcement);
        assertEquals(announcement.getId(), handler.getAnnouncement("1").getId());
        assertEquals(announcement.getText(), handler.getAnnouncement("1").getText());
        assertEquals(announcement.getAuthor(), handler.getAnnouncement("1").getAuthor());

        // check getAnnouncements
        assertEquals(4, handler.getAnnouncements().size());
    }

    @Test
    void getMaxId() {
        assertEquals(4, handler.getMaxUserId());
        try {
            statement.executeUpdate("delete from posts;");
            statement.executeUpdate("delete from admins;");
            statement.executeUpdate("delete from accounts;");
            assertEquals(-1, handler.getMaxUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getMaxIdOFAnnouncements() {
        assertEquals(4, handler.getMaxIdOFAnnouncements());
        try {
            statement.executeUpdate("delete from posts;");
            assertEquals(-1, handler.getMaxIdOFAnnouncements());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void messageManagement(){
        Account lasha = handler.getAccount(1);
        Account beka = handler.getAccount(2);
        Account beso = handler.getAccount(3);
        Account cima = handler.getAccount(4);

        // situacia: dead lock
        handler.addMessage(beka, lasha, "baro lasha","text");
        handler.addMessage(beso, cima, "cima rava xar?","text");
        handler.addMessage(beso, cima, "xo xar janze?","text");
        handler.addMessage(cima, beso, "ki besik, shen rava xar?","text");
        handler.addMessage(lasha, beka, "baro","text");
        handler.addMessage(beka, lasha, "lasha 50 lari xo ar gaqvs?","text");
        handler.addMessage(lasha, beso, "besik 50 lari xo ar gaqvs?","text");
        handler.addMessage(beso, cima, "cimush 50 lari xo ar gaqvs?","text");
        handler.addMessage(cima, beka, "beshqen 50 lari xo ar gaqvs?","text");

        // test getDialogue
        assertEquals(3,handler.getDialogue(beka, lasha).size());
        assertEquals(3,handler.getDialogue(lasha, beka).size());
        assertEquals(1,handler.getDialogue(lasha, beso).size());
        assertEquals(4,handler.getDialogue(cima, beso).size());
        Message message1 =  handler.getDialogue(beka, lasha).get(0);
        Message message2 =  handler.getDialogue(beka, lasha).get(1);
        Message message3 =  handler.getDialogue(beka, lasha).get(2);
        assertEquals("baro lasha", message1.getText());
        assertEquals("baro", message2.getText());
        assertEquals("lasha 50 lari xo ar gaqvs?", message3.getText());
        assertEquals(beka, message1.getFrom());
        assertEquals(lasha, message1.getTo());

        // test getMostRecentMessageAccount
        assertEquals(true, beka.equals(handler.getMostRecentMessageAccount(cima)));
        assertEquals(true, cima.equals(handler.getMostRecentMessageAccount(beka)));
        assertEquals(true, beso.equals(handler.getMostRecentMessageAccount(lasha)));
        assertEquals(true, cima.equals(handler.getMostRecentMessageAccount(beso)));

        // test getAccountMessages
        assertEquals(4, handler.getAccountMessages(beka).size());
        assertEquals(5, handler.getAccountMessages(beso).size());
        assertEquals(4, handler.getAccountMessages(lasha).size());
        assertEquals(5, handler.getAccountMessages(cima).size());

        // test getAccountMessages
        assertEquals(message1.getText(), handler.getAccountMessages(beka).get(0).getText());
        assertEquals(message2.getText(), handler.getAccountMessages(lasha).get(1).getText());
        assertEquals(message3.getText(), handler.getAccountMessages(beka).get(2).getText());

    }

    @Test
    void filterUsersByPrefix() {
        try {
            connection.createStatement().executeUpdate("delete from admins");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals(3, handler.filterUsersByPrefix("b").size());
        assertEquals(2, handler.filterUsersByPrefix("be").size());
        assertEquals(1, handler.filterUsersByPrefix("beka").size());
        assertEquals(0, handler.filterUsersByPrefix("a").size());
        assertEquals(1, handler.filterUsersByPrefix("c").size());
    }

    @Test
    void friendManagement(){
        Account lasha = handler.getAccount(1);
        Account beka = handler.getAccount(2);
        Account beso = handler.getAccount(3);
        Account cima = handler.getAccount(4);

        assertEquals(null, handler.getMostRecentMessageAccount(lasha));

        // test isRequesetSent
        assertEquals(false, handler.isRequestSent(lasha, beka));
        handler.addFriendRequest(lasha, beka);
        assertEquals(false, handler.isRequestSent(beka, lasha));
        assertEquals(true, handler.isRequestSent(lasha, beka));

        // test areFriends
        assertEquals(false, handler.areFriends(beka, lasha));
        handler.addFriendRequest(beka, lasha);
        handler.generateResponseToFriendRequest(false, 1, 2);
        assertEquals(false, handler.areFriends(beka, lasha));
        handler.generateResponseToFriendRequest(true, 2, 1);
        assertEquals(true, handler.areFriends(lasha, beka));
        assertEquals(true, handler.areFriends(beka, lasha));
        assertEquals(1, handler.getFriends(lasha.getId()).size());

        // test getReceivedFriendRequests
        handler.addFriendRequest(beka, cima);
        handler.addFriendRequest(beso, cima);
        handler.addFriendRequest(lasha, cima);
        assertEquals(0, handler.getReceivedFriendRequests(lasha).size());
        assertEquals(3, handler.getReceivedFriendRequests(cima).size());
        assertEquals(0, handler.getReceivedFriendRequests(beka).size());

        // test getFriends
        handler.generateResponseToFriendRequest(true, cima.getId(), beka.getId());
        handler.generateResponseToFriendRequest(true, cima.getId(), beso.getId());
        handler.generateResponseToFriendRequest(true, cima.getId(), lasha.getId());
        List<Account> friends = handler.getFriends(cima.getId());
        assertEquals(3, friends.size());
        assertEquals(beka.getId(), friends.get(0).getId());
        assertEquals(beso.getId(), friends.get(1).getId());
        assertEquals(lasha.getId(), friends.get(2).getId());

        // test removeFriend
        handler.removeFriend(beka, lasha);
        assertEquals(false, handler.areFriends(beka, lasha));
    }

    @Test
    void numberOfAccounts() {
        assertEquals(4, handler.numberOfAccounts());
    }

    @Test
    void numberOfAdmins() {
        assertEquals(3, handler.numberOfAdmins());
    }


    @Test
    void quizManagement(){

        // add statistics
        handler.addQuiz("first quiz", "asdf", "asdf", "asdf", 4, "dasf");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = "2023-08-22 00:55:13";
        String TwoDaysAgo = "2023-06-22 00:55:13";
        String OneDayAgo = "2023-07-22 08:55:14";
        handler.updateQuizHistory(1, 1, 10, 12, currentTime);
        handler.updateQuizHistory(1, 2, 20, 14, TwoDaysAgo);
        handler.updateQuizHistory(1, 3, 30, 20, TwoDaysAgo);
        handler.updateQuizHistory(1, 4, 50, 10, OneDayAgo);
        handler.updateQuizHistory(1, 1, 90, 7, currentTime);
        handler.updateQuizHistory(1, 1, 80, 20, TwoDaysAgo);

        // test getQuizzesByAuthor quiz
        handler.addQuiz("gio", "as","","", 1, "");
        handler.addQuiz("gio", "as","","", 1, "");
        assertEquals(true, handler.containsQuizTitle("gio"));
        assertEquals(2, handler.getQuizID("gio"));
        assertEquals(0, handler.getQuizID("gia"));

        handler.addQuestion("",1.0,"",1,"", "", 1);
        assertEquals(1, handler.getQuizzesByAuthor(1).size());
        assertEquals(0, handler.getQuizzesByAuthor(2).size());
        assertEquals("gio", handler.getQuiz(2).getTitle());

        // test getRecentQuizzes;
        handler.addQuiz("fxala","","","",2,"");
        List<Quiz> quizzes = handler.getRecentQuizzes();
        assertEquals(3, quizzes.size());
        assertEquals(4, quizzes.get(0).getCreatorID());
        assertEquals(1, quizzes.get(1).getCreatorID());
        assertEquals(2, quizzes.get(2).getCreatorID());

        // test getPopularQuizzes
        handler.updateQuizHistory(2,1,10,10,currentTime);
        quizzes = handler.getPopularQuizzes();
        assertEquals(2, quizzes.size());
        assertEquals("first quiz", quizzes.get(0).getTitle());
        assertEquals("gio", quizzes.get(1).getTitle());

        // test getRecentActivities
        List<QuizStatistics> recentActivities = handler.getRecentActivities(1);
        assertEquals(4, recentActivities.size());
        assertEquals(1, recentActivities.get(0).getQuizId());
        assertEquals(1, recentActivities.get(1).getQuizId());
        assertEquals(2, recentActivities.get(2).getQuizId());
        assertEquals(1, recentActivities.get(3).getQuizId());

        // test getQuizStaticsForUserAndOrder
        // order by start_date
        assertEquals(1, handler.getQuizID("first quiz"));
        List<QuizStatistics> quizStatistics = handler.getQuizStatisticsForUserAndOrder(1, 1, 0);
        assertEquals(10, quizStatistics.get(0).getScore());
        assertEquals(90, quizStatistics.get(1).getScore());

        // order by score
        quizStatistics = handler.getQuizStatisticsForUserAndOrder(1, 1, 1);
        assertEquals(90, quizStatistics.get(0).getScore());
        assertEquals(80, quizStatistics.get(1).getScore());
        assertEquals(10, quizStatistics.get(2).getScore());

        // order by time
        quizStatistics = handler.getQuizStatisticsForUserAndOrder(1, 1, 2);
        assertEquals(7, quizStatistics.get(0).getTime());
        assertEquals(12, quizStatistics.get(1).getTime());
        assertEquals(20, quizStatistics.get(2).getTime());


        handler.updateQuizHistory(1, 1, 0, 20, currentTime);
        handler.updateQuizHistory(1, 1, 0, 20, currentTime);
        handler.updateQuizHistory(1, 1, 0, 20, TwoDaysAgo);

        quizStatistics = handler.getQuizStatisticsForUserAndOrder(1, 1, 2);
        // check limit
        assertEquals(DBHandler.limit, quizStatistics.size());

        // check getTopPerformersOfAllTime
        quizStatistics = handler.getTopPerformersOfAllTime(1);
        assertEquals(DBHandler.limit, quizStatistics.size());
        assertEquals(90, quizStatistics.get(0).getScore());
        assertEquals(80, quizStatistics.get(1).getScore());
        assertEquals(50, quizStatistics.get(2).getScore());
        assertEquals(30, quizStatistics.get(3).getScore());
        assertEquals(20, quizStatistics.get(4).getScore());

        // check getTopPerformersOfTheDay
        quizStatistics = handler.getTopPerformersOfTheDay(1);
        assertEquals(DBHandler.limit-1, quizStatistics.size());
        assertEquals(90, quizStatistics.get(0).getScore());
        assertEquals(10, quizStatistics.get(1).getScore());
        assertEquals(0, quizStatistics.get(2).getScore());
        assertEquals(0, quizStatistics.get(3).getScore());

        //check getLastPerformers
        quizStatistics = handler.getLastPerformers(1);
        assertEquals(DBHandler.limit, quizStatistics.size());
        assertEquals(10, quizStatistics.get(0).getScore());
        assertEquals(90, quizStatistics.get(1).getScore());
        assertEquals(0, quizStatistics.get(2).getScore());
        assertEquals(0, quizStatistics.get(3).getScore());
        assertEquals(50, quizStatistics.get(4).getScore());

        // test getAllPerformances
        List<QuizStatistics> performances = handler.getAllPerformances(1);
        assertEquals(9, performances.size());
        assertEquals(1, performances.get(0).getAccountId());
        assertEquals(2, performances.get(1).getAccountId());
        assertEquals(3, performances.get(2).getAccountId());
        assertEquals(4, performances.get(3).getAccountId());

        // test number ofQuizzesTaken
        assertEquals(10, handler.numberOfQuizzesTaken());

        //test friendsQuizzes
        handler.addQuiz("as","","","",1,"");
        handler.addFriendRequest(handler.getAccount(1), handler.getAccount(2));
        handler.generateResponseToFriendRequest(true, 1, 2);
        quizzes = handler.friendsQuizzes(2);
        assertEquals(2, quizzes.size());
        assertEquals("gio", quizzes.get(0).getTitle());
        assertEquals("as", quizzes.get(1).getTitle());

        //test getFriendsStatistics
        assertEquals(5, handler.friendsStatistics(2).size());
        assertEquals(1, handler.friendsStatistics(1).size());

        //test removeQuiz
        handler.removeQuiz("first quiz");
        handler.clearQuizHistory(1);
        assertEquals(false, handler.containsQuizTitle("first quiz"));
    }
}