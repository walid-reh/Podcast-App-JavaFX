package Misc;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import model.Podcast;
import model.PodcastApp;
import model.PodcastProperties;
import sample.EpisodeView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPodDesc extends Button  {
    private PodcastApp app;
    public PodcastProperties podcastliee;

    public ButtonPodDesc(String desc, PodcastApp application, PodcastProperties podcastliee){
        super(desc);
        this.app=application;
        this.podcastliee=podcastliee;
        this.setOnAction(new EcouteurPodDesc(application, podcastliee));

    }



}