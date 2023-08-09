package quizpackage;

public class Admin implements Account{
    String name;
    String surname;
    String username;
    String password;
    int age;
    int id;
    String image;
    public Admin(String name, String surname, String username,String password,int age, int id,String image){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.age = age;
        this.id = id;
        this.image = image;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getImage(){
        return image;
    }
}
