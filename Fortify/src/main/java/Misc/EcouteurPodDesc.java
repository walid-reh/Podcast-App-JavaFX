package Misc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.w3c.dom.events.Event;
import model.PodcastApp;
import model.PodcastProperties;

import javax.swing.*;

public class EcouteurPodDesc implements EventHandler<ActionEvent> {
    private PodcastApp app;
    private PodcastProperties podcastliee;

    public EcouteurPodDesc(PodcastApp app,PodcastProperties podcastliee){
        this.app=app;
        this.podcastliee=podcastliee;
    }
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Button clicked");
        app.choose(podcastliee);

    }
}