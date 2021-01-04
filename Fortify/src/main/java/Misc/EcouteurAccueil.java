package Misc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.PodcastApp;

public class EcouteurAccueil implements EventHandler<ActionEvent> {
    private PodcastApp app;
    public EcouteurAccueil(PodcastApp app){
        this.app=app;
    }
    @Override
    public void handle(ActionEvent event) {
        app.switchToPopular();

    }
}
