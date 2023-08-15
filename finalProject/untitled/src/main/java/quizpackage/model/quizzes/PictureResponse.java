package quizpackage.model.quizzes;

public class PictureResponse extends SingleAnswer{
    String image;
    public PictureResponse(String text, String answer, String image,double grade){
        super(text,answer,grade);
        this.image = image;
    }

    public String getImage(){
        return image;
    }
    @Override
    public String getQuestionClass(){
        return "PictureResponse";
    }

}
