package SignUp;

import App.App_Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp_Controller implements Initializable {
    public TextField email;
    public TextField fname;
    public TextField sname;
    public PasswordField password;
    public PasswordField confirmPassword;
    public Label lbl_err;
    public Label title;

    public void createAccount(ActionEvent event) {
        Node txt_elements[] = {email, fname, sname, password, confirmPassword};
        for (Node e : txt_elements) {
            e.setStyle("-fx-text-inner-color: black;");
        }
        lbl_err.setText("");
        lbl_err.setStyle("-fx-text-fill: red;");
        if (!checkEmail(email.getText())) {
            email.setStyle("-fx-text-inner-color: red;");
            lbl_err.setText("Invalid email address");
        } else if (fname.getText().length() == 0) {
            fname.setStyle("-fx-text-inner-color: red;");
            lbl_err.setText("Invalid first name");
        } else if (sname.getText().length() == 0) {
            sname.setStyle("-fx-text-inner-color: red;");
            lbl_err.setText("Invalid surname");
        } else if (password.getText().length() < 5 || confirmPassword.getText().length() < 5) {
            password.setStyle("-fx-text-inner-color: red;");
            confirmPassword.setStyle("-fx-text-inner-color: red;");
            lbl_err.setText("Password too short");
        } else if (password.getText().compareTo(confirmPassword.getText()) < 0) {
            password.setStyle("-fx-text-inner-color: red;");
            confirmPassword.setStyle("-fx-text-inner-color: red;");
            lbl_err.setText("Different passwords provided");
        } else {
            // reset confirmation
            for (Node e : txt_elements) {
                e.setStyle("-fx-text-inner-color: black;");
            }
            lbl_err.setText("");

            User user = new User(
                    email.getText(),
                    fname.getText(),
                    sname.getText(),
                    password.getText()
            );
            try {
                SignUpModel db = new SignUpModel();
                db.addUser(user);

                for (Node e : txt_elements) {
                    e.setStyle("-fx-text-inner-color: black;");
                }
                lbl_err.setText("");
                lbl_err.setStyle("-fx-text-fill: black;");
                lbl_err.setText("Account created");
                clearTexts();
            } catch (java.sql.SQLIntegrityConstraintViolationException e) {
                email.setStyle("-fx-text-inner-color: red;");
                lbl_err.setText("Duplicate email address");
            } catch (java.sql.SQLException e) {
                lbl_err.setText(e.getMessage());
            }
        }
    }

    private void clearTexts() {
        TextField txt_elements[] = {email, fname, sname, password, confirmPassword};
        for (TextField e : txt_elements) {
            e.setText("");
        }
    }

    public static boolean checkEmail(String email) {
        String pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        // https://www.emailregex.com/

        Pattern re = Pattern.compile(pattern);
        Matcher matcher = re.matcher(email);

        return matcher.find();
    }

    @FXML
    protected void gotoFirstPage(ActionEvent event) {
        Node txt_elements[] = {email, fname, sname, password, confirmPassword};
        for (Node e : txt_elements) {
            e.setStyle("-fx-text-inner-color: black;");
        }
        lbl_err.setText("");
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (App_Controller.type.equals("T"))
        {
            title.setText("Join as a tourist");
        }
        else if (App_Controller.type.equals("G"))
        {
            title.setText("Join as a local guide");
        }
    }
}

