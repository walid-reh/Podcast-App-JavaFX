package Vue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import model.PodcastApp;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuSearchController implements Initializable,ControlledScreen {
    PodcastApp podcastApp = new PodcastApp("./src/main/resources");
    ScreensController myController;

    @FXML
    javafx.scene.control.TextField keyword;

    @FXML
    public javafx.scene.control.TextField langue;

    @FXML
    public javafx.scene.control.TextField dateavant;

    @FXML
    public javafx.scene.control.TextField dateapres;

    @FXML
    public javafx.scene.control.TextField categorie;

    public MenuSearchController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;

    }

    @FXML
    public void goToPODCASTLISTActualite(ActionEvent event){

        myController.updatePodcastListActualite(Main.screenPODID, Main.screenPODFile,podcastApp);
        myController.setScreen(Main.screenPODID);
    }
    @FXML
    public void goToPODCASTLISTPreference(ActionEvent event){

        myController.updatePodcastListPreference(Main.screenPODID, Main.screenPODFile,podcastApp);
        myController.setScreen(Main.screenPODID);
    }
    @FXML
    public void goToPODCASTLISTRequested(ActionEvent event) throws Exception {

        podcastApp.FilterPodcast(keyword.getText(),categorie.getText(),dateavant.getText(),dateapres.getText(),langue.getText());
        myController.updatePodcastListRequested(Main.screenPODID, Main.screenPODFile,podcastApp);
        myController.setScreen(Main.screenPODID);
    }
}
