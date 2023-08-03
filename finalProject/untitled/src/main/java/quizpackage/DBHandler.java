package quizpackage;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBHandler {

    private BasicDataSource dataSource;
    private Connection connection;

    public DBHandler(){
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/finalProject");
        dataSource.setUsername("root");
        dataSource.setPassword("Vpxdukkdaash1");
        try{
            connection = dataSource.getConnection();
        }
        catch (SQLException se){
            se.printStackTrace();
        }
    }

    public Connection getConnection(){
        try{
            return dataSource.getConnection();
        }
        catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Account> getAccounts(){
        try{
            List<Account> accounts = new ArrayList<>();
            ResultSet resultSet = connection.createStatement().executeQuery("Select * from Accounts");
            while(resultSet.next()){
                Account cur = new User(resultSet.getString("firstname"),
                        resultSet.getString("surname"),
                        resultSet.getString("username"),
                        resultSet.getString("pass"),
                        resultSet.getInt("age"));
                accounts.add(cur);
            }
            return accounts;
        }
        catch (SQLException se){
            se.printStackTrace();
            return null;
        }
    }

    public void addAccount(Account account){
        try{
            connection.createStatement().execute("insert into Accounts(firstname,surname,username,pass,age) value "+
                    "("+ "\'" + account.getName()+"\'" + ","+ "\'" +account.getSurname()+"\'" + ","+"\'" +account.getUsername()+"\'" + ","+"\'" +account.getPassword() + "\'" + ","+account.getAge()+")");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Account getAccount(String username, String password){
        try{
            ResultSet st = connection.createStatement().executeQuery("Select * from Accounts where username = "
                    + "\'" + username +"\'");
            boolean usernameCorrect = false;
            boolean passwordCorrect = false;
            Account account = null;
            while(st.next()){
                usernameCorrect = true;
                String name = st.getString("firstname");
                String surname = st.getString("surname");
                String pswrd = st.getString("pass");
                int age = st.getInt("age");
                passwordCorrect = PasswordHasher.isPassword(password,pswrd);
                account = new User(name,surname,username,pswrd,age);
            }
            if(usernameCorrect && passwordCorrect){
                return account;
            }
            return null;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public boolean containsUsername(String username){
        try{
            ResultSet st = connection.createStatement().executeQuery("Select * from accounts where username = " + "\'" + username + "\'");
            return st.next();
        }
        catch(SQLException e){
            e.printStackTrace();
            return true;
        }
    }
}
