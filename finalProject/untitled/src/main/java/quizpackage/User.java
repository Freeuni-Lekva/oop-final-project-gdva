package quizpackage;

public class User implements Account{

    String name;
    String surname;
    String username;
    String password;
    int age;
    static long id = 0;
    public User(String name, String surname, String username,String password,int age){
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.age = age;
        this.id = ++id;
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
    public long getId() {
        return id;
    }
}
