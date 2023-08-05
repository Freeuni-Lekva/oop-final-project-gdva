package quizpackage;

public class User implements Account{

    String name;
    String surname;
    String username;
    String password;
    int age;
    int id;
    String image;
    public User(String name, String surname, String username,String password,int age, int id,String image){
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
        return username;
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
    public boolean equals(Object a){
        return ((Account)a).getUsername().equals(username);
    }

    @Override
    public String getImage(){
        return image;
    }
}
