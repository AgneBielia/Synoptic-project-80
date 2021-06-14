package Login;

import SignUp.SignUpModel;
import SignUp.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;

public class Login_Controller {
    public TextField email;
    public PasswordField password;
    public Label lbl_err;
    public static String logged_in_user;
    public static User logged_in_user_t;

    @FXML
    protected void login(ActionEvent event){
        SignUpModel db = new SignUpModel();
        Node[] txt_elements = {email, password};
        for (Node e : txt_elements) {
            e.setStyle("-fx-text-inner-color: black;");
        }
        lbl_err.setText("");
        lbl_err.setStyle("-fx-text-fill: red;");
        if (email.getText().length() == 0 || !(email.getText().contains("@"))) {
            email.setStyle("-fx-text-inner-color: red;");
            lbl_err.setText("Invalid email address");
        }
        else if (password.getText().length() < 5) {
            password.setStyle("-fx-text-inner-color: red;");
            lbl_err.setText("Password too short");
        }
        else{

            for (Node e : txt_elements) {
                e.setStyle("-fx-text-inner-color: black;");
            }
            lbl_err.setText("");
            try {
                logged_in_user_t = db.getUser(email.getText(), password.getText());

                try {
                    logged_in_user = email.getText();
                    Parent root = FXMLLoader.load(new URL("file:src/Profile/ProfilePage.fxml"));
                    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (java.io.IOException e) {
                    System.err.println(e);
                }
                for (Node e : txt_elements) {
                    e.setStyle("-fx-text-inner-color: black;");
                }
                lbl_err.setText("");

            } catch (SignUpModel.UserNotFoundException e) {
                lbl_err.setText(e.getMessage());
            } catch (SignUpModel.UserLockedOutException e) {
                lbl_err.setText("User is locked out\nContact an administrator");
            } catch (SignUpModel.IncorrectPasswordException e) {
                lbl_err.setText(e.getMessage());

            } catch (SQLException e) {
                lbl_err.setText(e.getMessage());
            }
        }
    }

    // todo: could implement later
    public void resetPassword(ActionEvent event) {
    }

    public void goBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(new URL("file:src/App/App_View.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setTitle("First page");
            stage.setScene(scene);
            stage.show();
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }
}
