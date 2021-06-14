package SignUp;

import App.App_Controller;

import java.math.BigInteger;
import java.security.MessageDigest;

public class User {
    private final String email;
    private final String fname;
    private final String sname;
    private String passwordHash;
    private String type;
    private String bio;

    User(String email, String fname, String sname, String password) {
        this.fname = fname;
        this.sname = sname;
        this.email = email;
        this.passwordHash = hashPassword(password);
        this.type = App_Controller.type;
    }

    User(String email, String fname, String sname) {
        this.fname = fname;
        this.sname = sname;
        this.email = email;
    }

    public static String hashPassword(String password) {
        //https://www.geeksforgeeks.org/md5-hash-in-java/
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = messageDigest.digest(password.getBytes());
            BigInteger bigInt = new BigInteger(1, bytes);

            String hash = bigInt.toString(16);
            while (hash.length() < 32) {
                hash = "0" + hash;
            }
            return hash;

        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e);
            return null;
        }
    }

    public String getEmail() {
        return this.email;
    }

    public String getFname() {
        return this.fname;
    }

    public String getSname() {
        return this.sname;
    }

    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String toString() {
        return email + " - " + fname + " - " + sname + " - " + passwordHash;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBio(){return this.bio;}
    public void setBio(String bio){this.bio = bio;}


//    public static void main(String[] args) {
//        if (CreateAccountController.checkEmail("gae19jtu@")) {
//            System.out.println("Valid");
//        } else {
//            System.out.println("Invalid");
//        }
//    }
}

