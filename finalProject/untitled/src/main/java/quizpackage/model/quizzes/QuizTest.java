package quizpackage.model.quizzes;

import junit.framework.TestCase;
import quizpackage.model.quizzes.Answer;
import quizpackage.model.quizzes.Question;
import quizpackage.model.quizzes.QuestionResponse;
import quizpackage.model.quizzes.Quiz;

import java.util.ArrayList;
import java.util.List;

public class QuizTest extends TestCase {

    public void testQuiz1(){
        Question question1 = new QuestionResponse("who's the goat?", "Messi",10);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        Quiz quiz = new Quiz(questions);
        List<Question> testQuestions = quiz.getQuestions();
        for(int i = 0 ;i<testQuestions.size();i++){
            assertTrue(testQuestions.get(i).getQuestionText().equals("who's the goat?"));
            assertTrue(testQuestions.get(i).getQuestionGrade() == 10);
            assertTrue(testQuestions.get(i).gradeQuestion("Messi")== 10);
            assertTrue(testQuestions.get(i).answerPercent("messi") == 1);
        }
        List<Answer> answers = new ArrayList<>();
        List<String> questionAnswers = new ArrayList<>();
        questionAnswers.add("Messi");
        Answer answer = new Answer(1,questionAnswers);
        answers.add(answer);
        assertTrue(quiz.gradeQuiz(answers) == 10);
    }

    public void testQuiz2(){
        Question question1 = new QuestionResponse("who's the goat?", "Messi",10);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        Question question2 = new MultipleChoiceSingleAnswer("fxala",15,3,"vinaa yvelaze magari da dzlieri?","siko chumburidze","kukusha","fxala");
        questions.add(question2);
        Question question3 = new FillTheBlank("___ ari yvelaze ___ da ___",25.5,"fxala","magari","dzlieri");
        questions.add(question3);
        List<String> question1Answers = new ArrayList<>();
        List<String> question2Answers = new ArrayList<>();
        List<String> question3Answers = new ArrayList<>();
        question1Answers.add("messi");
        question2Answers.add("FxAlA");
        question3Answers.add("fxala");
        question3Answers.add("bandzi");
        question3Answers.add("susti");
        Answer answer1 = new Answer(1,question1Answers);
        Answer answer2 = new Answer(2,question2Answers);
        Answer answer3 = new Answer(3,question3Answers);
        List<Answer>answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        Quiz quiz = new Quiz(questions);
        assertTrue(quiz.gradeQuiz(answers) == 25 + (double) 1 / 3 * 25.5);

    }

    public void testQuiz3(){
        Question question1 = new QuestionResponse("vinaa goati?", "Messi",10);
        List<Question> questions = new ArrayList<>();
        questions.add(question1);
        Question question2 = new FillTheBlank("___ ari yvelaze ___ da ___",25.5,"fxala","magari","dzlieri");
        questions.add(question2);
        Quiz quiz = new Quiz(questions);
        quiz.randomizeQuestions();
        List<Question> quizQuestions = quiz.getQuestions();
        assertTrue(quizQuestions.size() == questions.size());
        boolean first = false;
        boolean second = false;
        for(int i = 0; i<quizQuestions.size();i++){
            if(quizQuestions.get(i).getQuestionText().equals("vinaa goati?")){
                first = true;
            }
            if(quizQuestions.get(i).getQuestionText().equals("___ ari yvelaze ___ da ___")){
                second = true;
            }
        }
        assertTrue(first && second);
    }
}
