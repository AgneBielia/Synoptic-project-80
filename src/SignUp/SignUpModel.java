package SignUp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class SignUpModel {

    private final String dburl;
    private final String dbusername;
    private final String dbpassword;
    Connection c;

    public SignUpModel() {
        this.dburl = "jdbc:postgresql://localhost:5432/synoptic_db";
        this.dbusername = "postgres";
        this.dbpassword = "1password";
    }

    public static Connection Connect()
    {
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
             c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/synoptic_db", "postgres", "1password");
            c.setAutoCommit(false);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            System.err.println("ERROR: Connection to database failed.");
        }
        System.out.println("Database successfully connected.");
        return c;
    }

    /**
     *
     * Reference: Code reused from Team 2.1 addUser method SE Study planner project.
     *
     * Method: addUser(User user)
     *
     * Description: Adds a User object to the database.
     * Throws a generic SQLException if there's an error
     *
     * Author: Edward Attenborough
     *
     * Reimplemented by: Agne Bieliajevaite, Date: 11/06/2021
     */
    public void addUser(User user) throws SQLException {
            Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword);
            String sql = "INSERT INTO users (email, firstname, surname, password, user_type) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getFname());
            statement.setString(3, user.getSname());
            statement.setString(4, user.getPasswordHash());
            statement.setString(5, user.getType());
            statement.execute();
            connection.close();

    }

    /**
     * Reference: Code reused from Team 2.1 getUser method SE Study planner project.
     *
     * Method: getUser(String email, String password)
     *
     * Description: Returns a User object which is fetched from the database.
     * Takes an email address and an unhashed password as arguments.
     * Throws a UserNotFound exception if a user was not found.
     * Throws an IncorrectPasswordException if the password hashes don't match.
     * Throws a UserLockedOutException if the user has used the wrong password three times.
     * Throws a generic SQLException if there is any other error.
     *
     * Author: Edward Attenborough
     *
     * Reimplemented by: Agne Bieliajevaite, Date: 11/06/2021
     */
    public User getUser(String email, String password) throws SQLException, UserNotFoundException, IncorrectPasswordException, UserLockedOutException {
        Connection connection = DriverManager.getConnection(dburl, dbusername, dbpassword);
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.execute();
        ResultSet results = statement.getResultSet();
        User user;
        if (results.next()) {
            user = new User(
                    results.getString(1),
                    results.getString(2),
                    results.getString(3)
            );
            user.setPasswordHash(results.getString(4));     // use this method instead of the default
            // constructor, since otherwise it will
            // hash it again
        } else {
            connection.close();
            throw new UserNotFoundException("A user with that email was not found in the database.");
        }
        connection.close();

        // compare the password hashes to see if they're the same
        if (user.getPasswordHash().equals(User.hashPassword(password))) {
            return user;
        } else {
            throw new IncorrectPasswordException("The incorrect password was specified for that user.");
        }
    }

    public static class UserNotFoundException extends Exception {
        public UserNotFoundException(String message) {
            super(message);
        }
    }

    public static class IncorrectPasswordException extends Exception {
        public IncorrectPasswordException(String message) {
            super(message);
        }
    }

    public static class UserLockedOutException extends Exception {
        public UserLockedOutException(String message) {
            super(message);
        }
    }
}
