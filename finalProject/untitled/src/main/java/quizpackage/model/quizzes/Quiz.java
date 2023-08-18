package quizpackage.model.quizzes;

import java.util.Collections;
import java.util.List;

public class Quiz {
    List<Question> questions;
    String title;
    String order;
    String alignment;
    String answerType;
    int creatorID;
    int id;
    String description;
    double totalScore;

    public Quiz(List<Question> questions, String title, String order,String alignment, String answerType,
            int creatorId, int id,String description){
        this.questions = questions;
        this.title = title;
        this.order = order;
        this.alignment = alignment;
        this.answerType = answerType;
        this.creatorID = creatorId;
        this.id = id;
        this.description = description;
        totalScore = 0;
        for(int i = 0; i<questions.size();i++){
            totalScore += questions.get(i).getQuestionGrade();
        }
    }

    public void randomizeQuestions(){
        Collections.shuffle(questions);
    }

    public List<Question> getQuestions(){
        return questions;
    }

    public double gradeQuiz(List<Answer> answers){
        double result = 0;
        for(Answer answer : answers){
            result += questions.get(answer.getQuestionNumber()-1).gradeQuestion(answer.getAnswers().toArray(new String[answer.getAnswers().size()]));
        }
        return result;
    }

    public String getTitle(){
        return title;
    }
    public int getId(){return  id;}
    public int getCreatorID() {
        return creatorID;
    }
    public String getAlignment() {
        return alignment;
    }
    public String getAnswerType() {
        return answerType;
    }
    public String getOrder() {
        return order;
    }
    public boolean areQuestionsOnSinglePage(){
        return getAlignment().equals("one page");
    }
    public boolean isOrdered(){
        return getOrder().equals("ordered");
    }
    public boolean immediateAnswerNeeded(){
        return getAnswerType().equals("immediate");
    }
    public double getQuizTotalScore(){return totalScore;}
}
