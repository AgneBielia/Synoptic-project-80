package Profile;

public class User
{
    int    userid;
    String email;
    String firstmame;
    String surname;
    String password;
    String userType;
    String bio;

    //constructor for the database to use
    public User(int userid,String email,String firstname,String surname,String password, String userType,String bio)
    {
        this.userid    = userid;
        this.email     = email;
        this.firstmame = firstname;
        this.surname   = surname;
        this.password  = password;
        this.userType  = userType;
        this.bio       = bio;
    }

    public int getUserid()      {return userid;}
    public String getFirstname(){return firstmame;}
    public String getSurname()  {return surname;}
    public String getEmail()    {return email;}
    public String getPassword() {return password;}
    public String getUserType() {return userType;}
    public String getBio()      {return bio;}
}