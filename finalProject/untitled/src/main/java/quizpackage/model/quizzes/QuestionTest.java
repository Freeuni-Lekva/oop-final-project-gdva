package quizpackage.model.quizzes;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeAll;
import quizpackage.model.quizzes.Question;
import quizpackage.model.quizzes.QuestionResponse;

public class QuestionTest extends TestCase {

    public void testQuestionResponse(){
        SingleAnswerQuestion question = new QuestionResponse("who's the goat?","Messi",1);
        assertTrue(question.getQuestionText().equals("who's the goat?"));
        assertTrue(question.getQuestionGrade() == 1);
        assertTrue(question.answerPercent("Messi") == 1);
        assertTrue(question.answerPercent("messi") == 1);
        assertTrue(question.gradeQuestion("Messi") == 1);
        assertTrue(question.gradeQuestion("messi") == 1);
    }
    public void testEmptyInputForQuestionResponse(){
        SingleAnswerQuestion question = new QuestionResponse("","",10);
        assertTrue(question.getQuestionText().equals(""));
        assertTrue(question.getQuestionGrade() == 10);
        assertTrue(question.answerPercent("") == 1);
        assertTrue(question.answerPercent("") == 1);
        assertTrue(question.gradeQuestion("") == 10);
        assertTrue(question.gradeQuestion("") == 10);
    }
    public void testSingleAnswer(){
        SingleAnswer question = new SingleAnswer("who's the goat?","Messi",9.5);
        assertTrue(question.getQuestionAnswer().equals("messi"));
        assertTrue(question.gradeQuestion("messi") == 9.5);
    }
    public void testFillTheBlank(){
        SingleAnswerQuestion question = new FillTheBlank("the goat is ___",9.9,"Messi");
        assertTrue(question.getQuestionText().equals("the goat is ___"));
        assertTrue(question.getQuestionGrade() == 9.9);
        assertTrue(question.answerPercent("Messi") == 1);
        assertTrue(question.answerPercent("messi") == 1);
        assertTrue(question.gradeQuestion("Messi") == 9.9);
        assertTrue(question.gradeQuestion("messi") == 9.9);
    }
    public void testMultipleChoiceSingleAnswer(){
        SingleAnswerQuestion question = new MultipleChoiceSingleAnswer("Messi",9.99,"vinaa goati?","Messi","Koba","Lasa","Khvicha");
        assertTrue(question.getQuestionText().equals("vinaa goati? \nA. Messi \nB. Koba \nC. Lasa \nD. Khvicha \n"));
        assertTrue(question.getQuestionGrade() == 9.99);
        assertTrue(question.answerPercent("Messi") == 1);
        assertTrue(question.answerPercent("messi") == 1);
        assertTrue(question.gradeQuestion("Messi") == 9.99);
        assertTrue(question.gradeQuestion("messi") == 9.99);
    }

    public void testMultipleChoiceEmpty(){
        SingleAnswerQuestion question = new MultipleChoiceSingleAnswer("",9);
        assertTrue(question.getQuestionText().equals(""));
    }

    public void testPictureResponse(){
        SingleAnswerQuestion question = new PictureResponse("who's this?","Messi","img.png",9.90);
        assertTrue(question.getQuestionText().equals("who's this?"));
        assertTrue(question.getQuestionGrade() == 9.90);
        assertTrue(question.answerPercent("Messi") == 1);
        assertTrue(question.answerPercent("messi") == 1);
        assertTrue(question.gradeQuestion("Messi") == 9.90);
        assertTrue(question.gradeQuestion("messi") == 9.90);
    }
    public void testPictureResponseImage(){
        PictureResponse question = new PictureResponse("who's this?","Messi","img.png",9.90);
        assertTrue(question.getImage().equals("img.png"));
    }
}
