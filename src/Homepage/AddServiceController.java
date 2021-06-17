package Homepage;

import javafx.fxml.FXML;
import Login.Login_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import SignUp.User;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AddServiceController {

    @FXML
    public TextField titleTxtBox,costTxtBox,descTxtBox,locationTxtBox;
    @FXML
    public Button cancelBtn,submitBtn;

    /*
    Submits the service
     */
    public void submit(ActionEvent event) throws IOException {
        User loggedInUser = Login_Controller.logged_in_user_t;
        Service service = new Service(titleTxtBox.getText(),Integer.parseInt(costTxtBox.getText()),
                locationTxtBox.getText(),descTxtBox.getText(),loggedInUser.getEmail());
        HomepageModel.insertService(loggedInUser,service);
        back(event);
    }
    /*
    return to main
     */
    public void back(ActionEvent event) throws IOException {
        String link = null;
        link = "file:src/Homepage/HomepageView.fxml";
        Parent root = FXMLLoader.load(new URL(link));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
