package Profile;

import SignUp.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfileModel {

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
    public static void updateBio(User user, String newBio)
    {
        Statement update = null;
        try {
            DBConnect();
            update = c.createStatement();
            System.out.println(user.getEmail());
            String statement = "UPDATE users SET bio = '" + newBio + "' WHERE email =" +"'"+user.getEmail()+"'"+ ";";
            System.out.println(statement);
            update.executeUpdate(statement);
            c.commit();

            update.close();
            c.close();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("ERROR: Update operation failed.");
        }
        System.out.println("Update operation successful.");
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