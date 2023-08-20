package quizpackage.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class QuizStatisticsTest {
    QuizStatistics quizStatistics;
    Date date = new Date(1, 1, 1);
    @BeforeEach
    void setUp(){
        quizStatistics = new QuizStatistics(1, 2, 3,4, date);
    }
    @Test
    void getQuizId() {
        assertEquals(1, quizStatistics.getQuizId());
    }

    @Test
    void getAccountId() {
        assertEquals(2, quizStatistics.getAccountId());
    }

    @Test
    void getScore() {
        assertEquals(3, quizStatistics.getScore());
    }

    @Test
    void getTime() {
        assertEquals(4, quizStatistics.getTime());
    }

    @Test
    void getStartDate() {
        assertEquals(date, quizStatistics.getStartDate());
    }
}