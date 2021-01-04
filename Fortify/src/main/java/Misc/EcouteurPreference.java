package Misc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.PodcastApp;

public class EcouteurPreference implements EventHandler<ActionEvent> {
    private PodcastApp app;
    public EcouteurPreference(PodcastApp app){
        this.app=app;
    }
    @Override
    public void handle(ActionEvent event) {
        app.switchToPreference();

    }
}
