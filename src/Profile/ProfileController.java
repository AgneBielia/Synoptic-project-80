package Profile;

import Login.Login_Controller;
import SignUp.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ProfileController
{
    //userinfo
    @FXML
    public Text nameTxt,emailTxt,bioTxt;
    @FXML
    public Button updateBioBtn,updateBtn,cancelBtn;
    public TextField bioTxtField;
    public Button mainBtn;

    User loggedInUser;

    public void initialize() {
        profileInit();
    }


    /***
     * loads logged in users information
     */
    public void profileInit(){
        try{
            loggedInUser = Login_Controller.logged_in_user_t;
            nameTxt.setText (loggedInUser.getFname() + " " + loggedInUser.getSname());
            emailTxt.setText(loggedInUser.getEmail());
            bioTxt.setText  (loggedInUser.getBio());
        }catch (Exception e){
            System.out.println("User does not exist");
        }
    }

    /**
     * updateBioBtn shows options to update bio
     * @param event
     */
    public void changeBio(ActionEvent event){
        updateBioBtn.setVisible(false);
        bioTxtField.setVisible (true);
        updateBtn.setVisible   (true);
        cancelBtn.setVisible   (true);
    }
    /**
     * finalise update
     * @param event
     */
    public void updateBio(ActionEvent event){
        updateBioBtn.setVisible(true);
        bioTxtField.setVisible (false);
        updateBtn.setVisible   (false);
        cancelBtn.setVisible   (false);
        loggedInUser.setBio(bioTxtField.getText());
        ProfileModel.updateBio(loggedInUser,bioTxtField.getText());
        profileInit();
    }
    /**
     * cancel the update
     * @param event
     */
    public void cancelUpdate(ActionEvent event){
        updateBioBtn.setVisible(true);
        bioTxtField.setVisible (false);
        updateBtn.setVisible   (false);
        cancelBtn.setVisible   (false);
        profileInit();
    }

    public void changeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(new URL("file:src/Homepage/HomepageView.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setTitle("Join as a Local Guide");
        stage.setScene(scene);
        stage.show();
    }
}