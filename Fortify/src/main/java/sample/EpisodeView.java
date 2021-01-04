package sample;

import Misc.ButtonPodDesc;
import Misc.EcouteurDownload;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import model.Episode;
import model.Podcast;
import com.sun.javafx.font.freetype.HBGlyphLayout;
import javafx.collections.FXCollections;
import javafx.collections.FXCollections.*;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.PodcastApp;
import model.PodcastProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

public class EpisodeView extends VBox implements Observer{

    private VBox episodes;
    private ObservableList<HBox> items = FXCollections.observableArrayList();
    private ListView<HBox> titreEpisodes = new ListView<HBox>(items);
    private VideoPlayerController video;
    private Label episodesLabel;
    public void play(VideoPlayerController video, String AudioURL){

        new VideoPlayerController(AudioURL);
    }
    public EpisodeView(PodcastApp app,VideoPlayerController video) throws FileNotFoundException {
        this.video=video;
        app.addObserver(this);
        this.setStyle("-fx-background-color:linear-gradient(to top, #1b56af, #1e4f9b, #204888, #234175, #243a62);");
        episodesLabel=new Label("Episodes of "+app.getPodcastChoisi().getTitle());
        episodesLabel.setStyle("-fx-font-size:14px;-fx-text-fill: white;");
        this.episodes=new VBox();
        // background-image:
        //this.episodes.setStyle("-fx-background-color: linear-gradient(to bottom, #1b56af, #1e4f9b, #204888, #234175, #243a62);-fx-border-color:black");
        //episodes.setPrefSize(230,251);
        this.episodes.getChildren().add(episodesLabel);




        for(int i=0;i<app.getPodcastChoisi().getEpisodeList().size();i++){
            Episode podcastepisode=app.getPodcastChoisi().getEpisodeList().get(i);
            HBox episode = new HBox();
            Button play_btn = new Button();
            Button download_btn = new Button();
            Label titre_episode = new Label(podcastepisode.getName());
            ImageView playicon= new ImageView(new Image(new FileInputStream("src/main/resources/images/Play-Button-Orange.png")));
            ImageView downloadicon= new ImageView(new Image(new FileInputStream("src/main/resources/images/Download.png")));
            downloadicon.setFitHeight(30);
            downloadicon.setFitWidth(30);
            download_btn.setGraphic(downloadicon);
            download_btn.setOnAction(new EcouteurDownload(podcastepisode));
            playicon.setFitHeight(30);
            playicon.setFitWidth(30);
            play_btn.setGraphic(playicon);
            play_btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,new CornerRadii(0),new Insets(0))));
            download_btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT,new CornerRadii(0),new Insets(0))));

            play_btn.setOnAction(e -> play(video,podcastepisode.getURL()));




            if (i%2==0) {
                titre_episode.setStyle("-fx-text-fill: black");
                episode.setStyle("-fx-background-color: #FF9900;");
            }else {
                titre_episode.setStyle("-fx-text-fill: orange");
                episode.setStyle("-fx-background-color: #171717;");
            }
            episode.getChildren().addAll(titre_episode,play_btn,download_btn);
            items.add(episode);
        }
        titreEpisodes.setItems(items);



        episodes.getChildren().add(titreEpisodes);
        //   episodes.getChildren().addAll(list_titre,play_button);
        //ScrollPane scrollPane = new ScrollPane();
        //scrollPane.setContent(episodes);
        this.getChildren().addAll(episodes);
        //scrollPane.setPrefHeight(581);
        //scrollPane.setPrefWidth(250);
        episodes.setAlignment(Pos.CENTER);
    }

    @Override
    public void update(Observable o, Object arg) {

        PodcastApp app=((PodcastApp) o);
        PodcastProperties podcastchoisi=app.getPodcastChoisi();
        this.episodesLabel.setText("Episodes of " +podcastchoisi.getTitle());
        System.out.println("Updating Episodes for the selected podcast"+podcastchoisi.toString());
        titreEpisodes.getItems().clear();
        System.out.println(app.getPodcastChoisi().getEpisodeList().size());
        for(int i=0;i<app.getPodcastChoisi().getEpisodeList().size();i++) {
            Episode podcastepisode = app.getPodcastChoisi().getEpisodeList().get(i);
            HBox episode = new HBox();
            Button play_btn = new Button();
            Button download_btn = new Button();
            Label titre_episode = new Label(podcastepisode.getName());
            ImageView playicon = null;
            try {
                playicon = new ImageView(new Image(new FileInputStream("src/main/resources/images/Play-Button-Orange.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            ImageView downloadicon = null;
            try {
                downloadicon = new ImageView(new Image(new FileInputStream("src/main/resources/images/Download.png")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            download_btn.setOnAction(new EcouteurDownload(podcastepisode));
            downloadicon.setFitHeight(30);
            downloadicon.setFitWidth(30);
            download_btn.setGraphic(downloadicon);
            playicon.setFitHeight(30);
            playicon.setFitWidth(30);
            play_btn.setGraphic(playicon);
            play_btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(0), new Insets(0))));
            download_btn.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(0), new Insets(0))));

            play_btn.setOnAction(e -> play(video, podcastepisode.getURL()));
            episode.getChildren().addAll(titre_episode, play_btn, download_btn);

            items.add(episode);

            if (i%2==0) {
                titre_episode.setStyle("-fx-text-fill: black");
                episode.setStyle("-fx-background-color: #FF9900;");
            }else {
                titre_episode.setStyle("-fx-text-fill: orange");
                episode.setStyle("-fx-background-color: #171717;");
            }
        }

    }
}