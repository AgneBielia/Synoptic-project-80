package Help;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelpController {

    @FXML
    public Button mainBtn,profileBtn,helpBtn;

    public void changeScene(ActionEvent event) throws IOException {
        String link = "";
        try{
            if(event.getSource() == mainBtn)
                link = "file:src/Homepage/HomepageView.fxml";
            else if(event.getSource() == profileBtn)
                link = "file:src/Profile/ProfilePage.fxml";
            else if(event.getSource() == helpBtn)
                link = "file:src/Help/HelpView.fxml";
        }catch (Exception e){
            System.out.println("page change failed");
            link = "";
        }
        Parent root = FXMLLoader.load(new URL(link));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void exit(){
        System.exit(0);
    }
}