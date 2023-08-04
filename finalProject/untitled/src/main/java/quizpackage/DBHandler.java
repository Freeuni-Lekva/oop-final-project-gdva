package quizpackage;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.Date;
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
        dataSource.setPassword("root1234");
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
            ResultSet resultSet = connection.createStatement().executeQuery("Select * from accounts");
            while(resultSet.next()){
                Account cur = new User(resultSet.getString("firstname"),
                        resultSet.getString("surname"),
                        resultSet.getString("username"),
                        resultSet.getString("pass"),
                        resultSet.getInt("age"), resultSet.getInt("id"));
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
            connection.createStatement().execute("insert into accounts(id,firstname,surname,username,pass,age) value "+
                    "("+ account.getId() + ", " + "\'" + account.getName()+"\'" + ","+ "\'" +account.getSurname()+"\'" + ","+"\'" +account.getUsername()+"\'" + ","+"\'" +account.getPassword() + "\'" + ","+account.getAge()+")");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Account getAccount(String username, String password){
        try{
            ResultSet st = connection.createStatement().executeQuery("Select * from accounts where username = "
                    + "\'" + username +"\'");
            boolean usernameCorrect = false;
            boolean passwordCorrect;
            Account account = castResultToAccount(st);
         //   connection.createStatement().executeQuery("insert into debug(id) value (" + account.getId()+")");
            if(account != null){
                usernameCorrect = true;
            }
            passwordCorrect = PasswordHasher.isPassword(password,account.getPassword());
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

    public Account getAccount(String username){
        try{
            ResultSet st =
                    connection.createStatement().executeQuery("Select * from accounts where username = "
                    + "\'" + username +"\'");
            return castResultToAccount(st);
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }

    }
    private Account castResultToAccount(ResultSet st){
        try{
            Account account = null;
            while(st.next()){
                int id = st.getInt("id");
                String name = st.getString("firstname");
                String surname = st.getString("surname");
                String username = st.getString("username");
                String pswrd = st.getString("pass");
                int age = st.getInt("age");
                account = new User(name,surname,username,pswrd,age,id);
            }
            return account;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public Account getAccount(int id){
        try{
            ResultSet st =
                    connection.createStatement().executeQuery("Select * from accounts where id = "
                            + id + ";");
            return castResultToAccount(st);
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
        }catch(SQLException e){
            e.printStackTrace();
            return true;
        }
    }

    public boolean isAdmin(long id){
        try{
            ResultSet st = connection.createStatement().executeQuery("Select * from admins where id = " + "\'" + id + "\'");
            return st.next();
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public void removeUser(String username){
        if(isAdmin(getAccount(username).getId())) return;
        try{
            connection.createStatement().executeUpdate("delete from accounts where username = " + "\'" + username + "\'; commit;");
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public List<Announcement> getAnnouncements(){
        try{
            List<Announcement> announcements = new ArrayList<>();
            ResultSet st = connection.createStatement().executeQuery("select * from posts");
            while (st.next()) {
                Announcement announcement = getSingleAnnouncementFromResultSet(st);
                announcements.add(announcement);
            }
            return announcements;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public Announcement getAnnouncement(String id){
        try{
            ResultSet st = connection.createStatement().executeQuery("Select * from posts where id =" + id +";");
            Announcement announcement = null;
            while(st.next()){
                announcement = getSingleAnnouncementFromResultSet(st);
            }
            return announcement;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Announcement getSingleAnnouncementFromResultSet(ResultSet st) throws SQLException {
            int id = st.getInt("id");
            String title = st.getString("title");
            String plot = st.getString("plot");
            String image = st.getString("img");
            Date date = st.getDate("upload_date");
            int ID = st.getInt("author_id");
            Account account = getAccount(ID);
            Announcement announcement = new Announcement(title, plot, image, date, account,id);
            return announcement;
    }

    public void addAnnouncement(Announcement announcement){
        try{
            connection.createStatement().executeUpdate("insert into posts(id,title,plot,img,upload_date,author_id) value "+
                    "("+ announcement.getId() + ", " + "\'" + announcement.getTitle()+"\'" + ","+ "\'" +
                    announcement.getText()+"\'" + ","+"\'" +announcement.getImgSrc()+"\'" + ","+"\'" +announcement.getUploadDate()
                    + "\'" + ","+announcement.getAuthor().getId()+")");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public int getMaxId(){
        try{
            ResultSet st = connection.createStatement().executeQuery("select id from accounts where id = (select max(id) from accounts);");
            if(st.next()){
                return st.getInt("id");
            }
            return -1;
        }
        catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

    public int getMaxIdOFAnnouncements(){
        try{
            ResultSet st = connection.createStatement().
                    executeQuery("select id from posts where id = (select max(id) from posts);");
            if(st.next()){
                return st.getInt("id");
            }
            return -1;
        }
        catch(SQLException e){
            e.printStackTrace();
            return -1;
        }
    }

}


