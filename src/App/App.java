package App;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            GridPane root = FXMLLoader.load(new URL("file:src/App/App_View.fxml"));

            stage.setTitle("First Page");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (java.io.IOException e) {
            System.err.println(e);
        }
    }
}
