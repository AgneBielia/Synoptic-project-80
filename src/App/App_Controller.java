package App;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class App_Controller {
    public static String type = null;
    public Button SignUp;

    public void signUp(ActionEvent event) throws IOException {
        type = "T";
        Parent root = FXMLLoader.load(new URL("file:src/SignUp/SignUp_View.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setTitle("Join as a Tourist");
        stage.setScene(scene);
        stage.show();
    }

    public void signUpGuide(ActionEvent event) throws IOException {
        type = "G";
        Parent root = FXMLLoader.load(new URL("file:src/SignUp/SignUp_View.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setTitle("Join as a Local Guide");
        stage.setScene(scene);
        stage.show();
    }

    public void SignIn(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(new URL("file:src/Login/Login_View.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene scene = new Scene(root);
            stage.setTitle("Sign in");
            stage.setScene(scene);
            stage.show();
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }
}
