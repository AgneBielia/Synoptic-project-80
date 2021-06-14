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
}