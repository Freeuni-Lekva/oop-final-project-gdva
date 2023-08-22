package quizpackage.model.quizzes;

import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeAll;
import quizpackage.model.quizzes.Question;
import quizpackage.model.quizzes.QuestionResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionTest extends TestCase {

    public void testQuestionResponse(){
        SingleAnswerQuestion question = new QuestionResponse("who's the goat?","Messi",1);
        assertTrue(question.getQuestionText().equals("who's the goat?"));
        assertTrue(question.getQuestionGrade() == 1);
        assertTrue(question.answerPercent("Messi") == 1);
        assertTrue(question.answerPercent("messi") == 1);
        assertTrue(question.gradeQuestion("Messi") == 1);
        assertTrue(question.gradeQuestion("messi") == 1);
        assertTrue(question.getQuestionClass().equals("QuestionResponse"));
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
        assertTrue(question.getQuestionClass().equals("SingleAnswer"));
        assertTrue(question.getAnswer().equals("messi"));
    }
    public void testFillTheBlank(){
        SingleAnswerQuestion question = new FillTheBlank("the goat is ___",9.9,"Messi");
        assertTrue(question.getQuestionText().equals("the goat is ___"));
        assertTrue(question.getQuestionGrade() == 9.9);
        assertTrue(question.answerPercent("Messi") == 1);
        assertTrue(question.answerPercent("messi") == 1);
        assertTrue(question.gradeQuestion("Messi") == 9.9);
        assertTrue(question.gradeQuestion("messi") == 9.9);
        assertTrue(question.getQuestionClass().equals("FillTheBlank"));
    }
    public void testMultipleChoiceSingleAnswer(){
        MultipleChoiceSingleAnswer question = new MultipleChoiceSingleAnswer("Messi",9.99,4,"vinaa goati?","Messi","Koba","Lasa","Khvicha");
        assertTrue(question.getQuestionText().equals("vinaa goati?#Messi#Koba#Lasa#Khvicha"));
        assertTrue(question.getQuestionGrade() == 9.99);
        assertTrue(question.answerPercent("Messi") == 1);
        assertTrue(question.answerPercent("messi") == 1);
        assertTrue(question.gradeQuestion("Messi") == 9.99);
        assertTrue(question.gradeQuestion("messi") == 9.99);
        assertTrue(question.getQuestionClass().equals("MultipleChoiceSingleAnswer"));
        assertTrue(question.getChoiceNumber() == 4);
        List<String> answers = new ArrayList<>();
        answers.add("Messi");
        answers.add("Koba");
        answers.add("Lasa");
        answers.add("Khvicha");
        assertTrue(Arrays.deepEquals(answers.toArray(),question.getPossibleAnswers().toArray()));
    }
    public void testMultipleChoiceOneAnswer(){
        MultipleChoiceSingleAnswer question = new MultipleChoiceSingleAnswer("Messi",10,1,"vinaa goati?");
        assertTrue(question.getQuestionText().equals("vinaa goati?"));
    }

    public void testMultipleChoiceEmpty(){
        SingleAnswerQuestion question = new MultipleChoiceSingleAnswer("",9,0);
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
        assertTrue(question.getQuestionClass().equals("PictureResponse"));
    }
    public void testPictureResponseImage(){
        PictureResponse question = new PictureResponse("who's this?","Messi","img.png",9.90);
        assertTrue(question.getImage().equals("img.png"));
    }
}
