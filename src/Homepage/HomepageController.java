package Homepage;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import java.util.ArrayList;


public class HomepageController
{
    //texts to be edited
    @FXML
    public Text title1Txt,title2Txt,title3Txt,title4Txt,desc1Txt,desc2Txt,desc3Txt,desc4Txt,
        location1Txt,location2Txt,location3Txt,location4Txt,cost1Txt,cost2Txt,cost3Txt,cost4Txt;
    @FXML
    public Button exitBtn,prevPgBtn,nextPgBtn;
    public int row = 0;
    public int page = 1;
    public int newRow = 4 * page;


    ArrayList<Service> services = HomepageModel.getServices();


    public void initialize() {
        title1Txt.setText("22");
        title2Txt.setText("22");
        title3Txt.setText("22");
        title4Txt.setText("22");
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
            row++;
        }
        System.out.println("First row" + row);
    }

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
    public void prevPage(ActionEvent event){
        page--;
        System.out.println(newRow+"MEWRWP");

        Text[] titles    = {title1Txt ,title2Txt,title3Txt,title4Txt};
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
}