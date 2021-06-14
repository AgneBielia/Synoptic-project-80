package Profile;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ProfileController
{
    //userinfo
    @FXML
    public Text nameTxt,emailTxt,bioTxt;
    //?
    User loggedInUser;
    //testUser
    User testUser;

    public void initialize() {
        test();
    }


    //TODO GET LOGGED IN USER????
    public void test(){
        try{
            //DatabaseControl.fetchUser();
            nameTxt.setText(testUser.getFirstname() + " " + testUser.getSurname());
            emailTxt.setText(testUser.getEmail());
            bioTxt.setText(testUser.getBio());
        }catch (Exception e){
            System.out.println("User does not exist");
        }
    }
}