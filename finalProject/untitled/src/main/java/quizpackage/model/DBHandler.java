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
        dataSource.setUrl("jdbc:mysql://localhost:3306/finalproject");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot2023");
        try{
            connection = dataSource.getConnection();
        }
        catch (SQLException se){
            se.printStackTrace();
        }
    }

    public DBHandler(boolean f){
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("rootroot2023");
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
                Account cur = getSingleAccount(resultSet);
                accounts.add(cur);
            }
            return accounts;
        }
        catch (SQLException se){
            se.printStackTrace();
            return null;
        }
    }
    private Account getSingleAccount(ResultSet resultSet) throws SQLException{
        Account acc = new User(resultSet.getString("firstname"),
                resultSet.getString("surname"),
                resultSet.getString("username"),
                resultSet.getString("pass"),
                resultSet.getInt("age"),
                resultSet.getInt("id"),
                resultSet.getString("img"));
        return acc;
    }

    public void addAccount(Account account){
        try{
            connection.createStatement().execute("insert into accounts(id,firstname,surname,username,pass,age,img) value "+
                    "("+ account.getId() + "," +
                    "\'" + account.getName()+"\'" + ","+
                    "\'" +account.getSurname()+"\'" + ","+
                    "\'" +account.getUsername()+"\'" + ","+
                    "\'" +account.getPassword() + "\'" + "," +
                    account.getAge() + "," +
                    "\'" +account.getImage() + "\'" + ");");

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
            boolean passwordCorrect = false;
            Account account = castResultToAccount(st);
         //   connection.createStatement().executeQuery("insert into debug(id) value (" + account.getId()+")");
            if(account != null){
                usernameCorrect = true;
                passwordCorrect = PasswordHasher.isPassword(password,account.getPassword());
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
            if(st.next()){
                int id = st.getInt("id");
                String name = st.getString("firstname");
                String surname = st.getString("surname");
                String username = st.getString("username");
                String pswrd = st.getString("pass");
                String image = st.getString("img");
                int age = st.getInt("age");
                account = new User(name,surname,username,pswrd,age,id,image);
            }
            return account;
        }
        catch(SQLException e){
            debug("castshi daicatcha");
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
            debug("getAccountshi daicatcha");
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
        //System.out.println(username);
        try{
            //System.out.println("delete from accounts where username = " + "\'" + username + "\'; commit;");
            connection.createStatement().executeUpdate("delete from accounts where username = " + "\'" + username + "\'; ");
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void promoteUser(String username){
        int id = getAccount(username).getId();
        if(isAdmin(id)) return ;
        try{
            connection.createStatement().executeUpdate("insert into admins(id) value (" + id + ");");
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

    public List<Message> getAccountMessages(Account account){
        try{
            ResultSet st = connection.createStatement().executeQuery("select * from messages where from_id = " +account.getId() +" or to_id = " + account.getId() + " order by send_date asc, id asc;");
            List<Message> messages = new ArrayList<>();
            while(st.next()){
                messages.add(castResultToMessage(st));
            }
            return messages;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public void debug(String text){
        try{
             connection.createStatement().execute("insert into debug(txt) value (\'" + text + "\');");
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }


    public Account getMostRecentMessageAccount(Account account){
        try{
            ResultSet st = connection.createStatement().executeQuery("select from_id, to_id from messages where send_date = (select max(send_date) from messages where from_id = " + account.getId() + " or to_id = " +account.getId() +") order by id desc;");
            while(st.next()){
                int from_id = st.getInt("from_id");
                int to_id = st.getInt("to_id");
                if(from_id == account.getId()){
                    return getAccount(to_id);
                }
                else{
                    return getAccount(from_id);
                }
            }
            return null;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Message> getDialogue(Account acc1, Account acc2){
        try{
            List<Message> result = new ArrayList<>();
            ResultSet st = connection.createStatement().executeQuery("select * from messages where (from_id = " + acc1.getId() + " and to_id = " + acc2.getId()+ " ) or ( from_id = " + acc2.getId() + " and to_id = " + acc1.getId()+") order by send_date asc, id asc;" );
            while(st.next()){
                result.add(castResultToMessage(st));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Message castResultToMessage(ResultSet st){
        try{
            Account from = getAccount(st.getInt("from_id"));
            Account to = getAccount(st.getInt("to_id"));
            Date date = st.getDate("send_date");
            String txt = st.getString("txt");
            Message message = new Message(from,to,date,txt);
            return message;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public void addMessage(Account from, Account to, String text){
        try{
            if(from == null){
                debug("from is null");
            }
            if(to == null){
                debug("to is null");
            }
            debug("" + from.getId() + " " + to.getId() + " " +text);
            connection.createStatement().execute("insert into messages(from_id,to_id,send_date,txt) value ("+ from.getId() + ","+ to.getId()+","+"sysdate()"+"," + "\'"+text+"\');");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public List<Account> filterUsersByPrefix(String username){
        List<Account> accounts = new ArrayList<>();
        try{
            ResultSet rs = connection.createStatement().
                    executeQuery("select * from accounts where username like " + "\'" + username +"%"+ "\'");
            while(rs.next()){
                Account cur = getSingleAccount(rs);
                accounts.add(cur);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }
    public void addFriendRequest(Account from,Account to){
         try{
             connection.createStatement().
                     executeUpdate("insert into sent_requests(sender_id,receiver_id,response) value (" +
                             + from.getId() + ","+ to.getId()+","+"\'"+"N/A"+"\')");
         }catch (SQLException e){
            e.printStackTrace();
         }
    }

    public boolean isRequestSent(Account from, Account to){
        try{
            ResultSet rs = connection.createStatement().
                    executeQuery("select sender_id from sent_requests where sender_id = " + from.getId()
                            +" and "+"receiver_id = "+to.getId());
            return rs.next();
        }catch (SQLException e){
            debug("select sender_id from sent_requests where sender_id = " + from.getId()
                    +" and "+"receiver_id = "+to.getId());
            e.printStackTrace();
        }
        return false;
    }

    public List<Account> getReceivedFriendRequests(Account receiver){
        List<Account> accounts = new ArrayList<>();
        try{
            ResultSet rs = connection.createStatement().
                    executeQuery("select sender_id from sent_requests where receiver_id = "+receiver.getId()+
                            " and response =" +"\'"+"N/A"+"\'" );
            while(rs.next()){
                int id = rs.getInt("sender_id");
                Account acc = getAccount(id);
                accounts.add(acc);
            }
        }catch (SQLException e){
            debug("select sender_id from sent_requests where receiver_id = "+receiver.getId()+
                    " and response =" +"\'"+"N/A"+"\'");
            e.printStackTrace();
        }
        return accounts;
    }

    public void generateResponseToFriendRequest(boolean response,int fromID,int toID){
        if(response){
            try{
                connection.createStatement()
                        .executeUpdate("insert into friends(first_friend_id,second_friend_id) value (" + fromID+","+
                                toID+")");
                connection.createStatement()
                        .executeUpdate("delete from sent_requests where sender_id ="
                                + fromID + " and receiver_id = " + toID+";");
            }catch (SQLException e){
                debug("insert into friends(first_friend_id,second_friend_id) value (" + fromID+","+
                        toID+")");
                debug("update sent_requests set sender_id =" + fromID +", receiver_id ="+ toID +", response = 'Accept'" +
                        "where sender_id = " + fromID + " and receiver_id = " + toID +";");
                e.printStackTrace();
            }

        } else {
            try{
                connection.createStatement()
                        .executeUpdate("delete from sent_requests where sender_id ="
                                + fromID + " and receiver_id = " + toID+";");
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public boolean areFriends(Account first,Account second){
        int firstID = first.getId();
        int secondID = second.getId();
        try{
            ResultSet rs = connection.createStatement()
                    .executeQuery("select * from friends " +
                            "where (first_friend_id ="+ firstID+ " and second_friend_id ="+ secondID +
                            ") or (first_friend_id = " + secondID + " and second_friend_id = " + firstID +");");
            return rs.next();
        }catch (SQLException e){
                debug("select * from friends " +
                        "where (first_friend_id ="+ firstID+ " and second_friend_id ="+ secondID +
                        ") or (first_friend_id = " + secondID + " and second_friend_id = " + firstID +");");
            e.printStackTrace();
        }
        return false;
    }

    public int numberOfAccounts(){
        return getAccounts().size();
    }

    public int numberOfAdmins(){
        try {
            int result = 0;
            ResultSet resultSet = connection.createStatement().executeQuery("Select * from admins");
            while (resultSet.next()) {
                result++;
            }
            return result;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    public void removeFriend(Account first, Account second){
        int firstID = first.getId();
        int secondID = second.getId();
        try {
            connection.createStatement()
                    .executeUpdate("delete from friends " +
                            "where (first_friend_id ="+ firstID+ "  and second_friend_id ="+ secondID +
                            ") or (first_friend_id = " + secondID + "  and second_friend_id = " + firstID +");");
        }catch (SQLException e){
            debug("delete from friends " +
                    "where (first_friend_id ="+ firstID+ " and second_friend_id ="+ secondID +
                    ") or (first_friend_id = " + secondID + " and second_friend_id = " + firstID +");");
            e.printStackTrace();
        }

    }

}

