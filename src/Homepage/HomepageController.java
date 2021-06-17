package Homepage;

import Login.Login_Controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import SignUp.User;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class HomepageController
{
    //texts to be edited
    @FXML
    public Text title1Txt,title2Txt,title3Txt,title4Txt,desc1Txt,desc2Txt,desc3Txt,desc4Txt,
        location1Txt,location2Txt,location3Txt,location4Txt,cost1Txt,cost2Txt,cost3Txt,cost4Txt;
    @FXML
    public Button exitBtn,prevPgBtn,nextPgBtn,mainBtn,profileBtn,helpBtn;
    public int page = 1;

    //Determines which page on to show correct listings
    public int newRow = 4 * page;
    public Button addNewServiceButton;
    public Button moreBtn4,moreBtn3,moreBtn2,moreBtn1;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    ArrayList<Service> services = HomepageModel.getServices();

    /**
     * loads initial info and checks whether user is guide or not
     */
    public void initialize() {
        User loggedInUser = Login_Controller.logged_in_user_t;
        System.out.println(HomepageModel.isGuide(loggedInUser));
        loggedInUser.setType(HomepageModel.isGuide(loggedInUser));
        if(loggedInUser.getType().equals("G"))
            addNewServiceButton.setVisible(true);
        else
            System.out.println("is T");
        populateMenu();
    }
    
    public void populateMenu()
    {
        Text[] titles    = {title1Txt ,title2Txt,title3Txt,title4Txt};
        Text[] descs     = {desc1Txt,desc2Txt,desc3Txt,desc4Txt};
        Text[] locations = {location1Txt,location2Txt,location3Txt,location4Txt};
        Text[] costs     = {cost1Txt,cost2Txt,cost3Txt,cost4Txt};
        for(int i = 0; i < 4; i++){
            titles[i].setText(services.get(i).getTitle());
            descs[i].setText(services.get(i).getDescription());
            locations[i].setText(services.get(i).getLocation());
            costs[i].setText(String.valueOf(services.get(i).getCost()));
        }
    }

    /***
     * Shows the next page of services
     * @param event
     */
    public void nextPage(ActionEvent event){
        int currentPage = page;
        if(services.get(page*4-4)!=null)
            page++;
        System.out.println(newRow+"MEWRWP");
        Text[] titles    = {title1Txt ,title2Txt,title3Txt,title4Txt};
        Text[] descs     = {desc1Txt,desc2Txt,desc3Txt,desc4Txt};
        Text[] locations = {location1Txt,location2Txt,location3Txt,location4Txt};
        Text[] costs     = {cost1Txt,cost2Txt,cost3Txt,cost4Txt};
        int arrInc = 0;
        for(int i = 4*page-4; i < 4*page; i++){
            if(services.get(page*4-4) ==null){
                page=currentPage;
                break;
            }
            try{
                titles[arrInc].setText(services.get(i).getTitle());
                descs[arrInc].setText(services.get(i).getDescription());
                locations[arrInc].setText(services.get(i).getLocation());
                costs[arrInc].setText(String.valueOf(services.get(i).getCost()));
                arrInc++;
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }
    /***
     * Shows the previous page of services
     * @param event
     */
    public void prevPage(ActionEvent event){
        page--;

        Text[] titles    = {title1Txt,title2Txt,title3Txt,title4Txt};
        Text[] descs     = {desc1Txt,desc2Txt,desc3Txt,desc4Txt};
        Text[] locations = {location1Txt,location2Txt,location3Txt,location4Txt};
        Text[] costs     = {cost1Txt,cost2Txt,cost3Txt,cost4Txt};
        int arrInc = 0;
        for(int i = 4*page-4; i < 4*page; i++){
            if(page<=0){
                page=1;
                break;
            }
            try{
                titles[arrInc].setText(services.get(i).getTitle());
                descs[arrInc].setText(services.get(i).getDescription());
                locations[arrInc].setText(services.get(i).getLocation());
                costs[arrInc].setText(String.valueOf(services.get(i).getCost()));
                arrInc++;
            }catch (Exception e){
                System.out.println("bad");
            }
        }
    }

    /**
     *test for the more button
     * displays an alert of the guides information
     * @param user
     */
    public void userAlert(User user){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Guide name : " + user.getFname() + " " + user.getSname());
        alert.setHeaderText("Guides Bio: \n"+HomepageModel.loadBio(user)
        + "\n Users email: "+user.getEmail()+"\n **sign up to view tour**");
        alert.showAndWait();
    }

    /**
     * shows more information about the service
     * uses userAlert
     * @param event
     */
    public void more(ActionEvent event){
        User user;
        if(event.getSource() == moreBtn1){
            user =HomepageModel.findAuthor(services.get(4*page-4).getAuthor_email());
            System.out.println("EMAIL"+user.getEmail());
            userAlert(user);
        }
        else if(event.getSource() ==moreBtn2){
            user = HomepageModel.findAuthor(services.get(4*page-3).getAuthor_email());
            System.out.println("EMAIL"+user.getEmail());
            userAlert(user);
        }
        else if(event.getSource() ==moreBtn3){
            user =HomepageModel.findAuthor(services.get(4*page-2).getAuthor_email());
            System.out.println("EMAIL"+user.getEmail());
            userAlert(user);
        }
        else if(event.getSource() ==moreBtn4){
            user =HomepageModel.findAuthor(services.get(4*page-1).getAuthor_email());
            System.out.println("EMAIL"+user.getEmail());
            userAlert(user);
        }
    }

    /**
     * changes the scene to the event target
     * @param event
     * @throws IOException
     */
    public void changeScene(ActionEvent event) throws IOException {
       String link = "";
       try{
           if(event.getSource() == mainBtn)
               link = "file:src/Homepage/HomepageView.fxml";
           else if(event.getSource() == profileBtn)
               link = "file:src/Profile/ProfilePage.fxml";
           else if(event.getSource() == addNewServiceButton)
               link = "file:src/Homepage/AddServiceView.fxml";
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

   //exit
   public void exit(ActionEvent event){ System.exit(0); }
}