package Homepage;

import SignUp.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import SignUp.User;
import java.util.List;

public class HomepageModel
{
    private static Connection c = null;
    public static void DBConnect()
    {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/syp", "postgres", "help");
            c.setAutoCommit(false);
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: Connection to DB not established.");
        }
        System.out.println("Connected to DB successfully.");
    }

    /***
     * Returns a list of all services
     * I dont know howim doing main page yet
     * @return
     */
    public static ArrayList<Service> getServices(){
        Statement select = null;
        ArrayList<Service> services = new ArrayList<>();
        try
        {
            DBConnect();
            select = c.createStatement();

            ResultSet query_result = select.executeQuery("SELECT * FROM service;");

            while(query_result.next())
            {
                int uniqueId = query_result.getInt("unique_id");
                int cost = query_result.getInt("cost");
                String title = query_result.getString("title");
                String desc = query_result.getString("description");
                String location = query_result.getString("location");
                String authorEmail = query_result.getString("author_email");

                services.add(new Service(title, cost, location, desc, uniqueId,authorEmail));
            }
            query_result.close();
            select.close();
            c.close();
            System.out.println("Select operation successful.");
            return services;
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: Select operation failed.");
            return null;
        }
    }
    public static String isGuide(User user){

        Statement select = null;
        try
        {
            DBConnect();
            select = c.createStatement();

            ResultSet query_result = select.executeQuery("SELECT * FROM users WHERE email =" +"'"+user.getEmail()+"'"+ ";");
            while(query_result.next())
            {
                String type = query_result.getString("user_type");
                if(type.equals("G"))
                    return "G";
                else if(type.equals("T"))
                    return "T";
            }
            query_result.close();
            select.close();
            c.close();
            return " ";
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: Select operation failed.");
            return null;
        }
    }
    public static User findAuthor(String string){
        Statement select = null;
        try
        {
            DBConnect();
            select = c.createStatement();
            System.out.println(string);
            ResultSet query_result = select.executeQuery("SELECT * FROM users WHERE email =" +"'"+string+"'"+ ";");

            while(query_result.next())
            {
                String email = query_result.getString("email");
                String firstname = query_result.getString("firstname");
                String surname = query_result.getString("surname");
                System.out.println(email);
                return new User(email,firstname,surname);
            }
            query_result.close();
            select.close();
            c.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: Select operation failed.");
            return null;
        }
        return null;
    }


    public static void insertService(User user,Service service){
        Statement insert = null;

        String title = service.getTitle();
        String desc = service.getDescription();
        int cost = service.getCost();
        String location = service.getLocation();
        String author = user.getEmail();

        try
        {
            DBConnect();
            insert = c.createStatement();

            String statement = "INSERT INTO service (title, cost, location, description, author_email" +
                    ")VALUES("+"'"+ title + "', " + cost + ", '" + location + "','" + desc + "' , '" +
                    author + "'" + ");";

            insert.executeUpdate(statement);

            insert.close();
            c.commit();
            c.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: Insert operation failed.");
        }
        System.out.println("Insert operation successful.");
    }
    public static String loadBio(User user){

        Statement select = null;
        try
        {
            DBConnect();
            select = c.createStatement();

            //ResultSet query_result = select.executeQuery();
            ResultSet query_result = select.executeQuery("SELECT * FROM users WHERE email =" +"'"+user.getEmail()+"'"+ ";");
            while(query_result.next())
                return query_result.getString("bio");

            query_result.close();
            select.close();
            c.close();
            return " ";
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: Select operation failed.");
            return null;
        }
    }

}