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

public class AddServiceController {

    @FXML
    public TextField titleTxtBox,costTxtBox,descTxtBox,locationTxtBox;
    @FXML
    public Button cancelBtn,submitBtn;

    public void initialise(){

    }

    public void submit(ActionEvent event){
        User loggedInUser = Login_Controller.logged_in_user_t;
        Service service = new Service(titleTxtBox.getText(),Integer.parseInt(costTxtBox.getText()),
                locationTxtBox.getText(),descTxtBox.getText(),loggedInUser.getEmail());
        HomepageModel.insertService(loggedInUser,service);
    }
    public void cancel(ActionEvent event){

    }
}
