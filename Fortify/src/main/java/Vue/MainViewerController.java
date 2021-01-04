package Vue;

import Misc.ButtonPodDesc;
import Misc.EcouteurDownload;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import model.*;
import sample.VideoPlayerController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainViewerController implements Initializable,ControlledScreen {
    ScreensController myController;
    ObservableList<GridPane> Podcastitems = FXCollections.observableArrayList();
    private ObservableList<HBox> items = FXCollections.observableArrayList();
    private VideoPlayerController videoPlayerController;
    public PodcastApp podcastApp;
    //public ArrayList<PodcastProperties> properties = podcastApp.getPopular();
    //public ArrayList<PodcastProperties> preference = podcastApp.getPreference();
    public int indexEpisodePlay = 0;
    public Episode currentEpisode;
    public PodcastProperties currentPodcast ;
    public int indexPodcast =0;
    public MainViewerController() throws Exception {

    }

   /* public void play(VideoPlayerController video, String AudioURL,ActionEvent event) {
        new VideoPlayerController(AudioURL);
        video.playVideo(event);
    }*/
    private static Media media;
    private static MediaPlayer mediaPlayer;

    private static String filePath;
    @FXML
    public Slider slidervolume;




    @FXML
    public javafx.scene.control.Slider sliderprogression ;

    @FXML
    private MediaView mediaView;
    @FXML
     public ListView<HBox> episodeList=new ListView<HBox>(items);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    @FXML
    public ListView<GridPane> podcastList= new ListView<GridPane>(Podcastitems);

    @FXML
    public void goToSEARCHMENU(ActionEvent event){
        myController.setScreen(Main.screenSEARCHID);
    }

    public void loadDataPodcast(ArrayList<PodcastProperties> podcasttoadd, PodcastApp application) throws Exception{

        ///
        for (int i=0;i<podcasttoadd.size();i++){
            GridPane Container=new GridPane();
            PodcastProperties podcast_i=podcasttoadd.get(i);
            Label podcastname=new Label(podcast_i.getTitle());
            //ImageView image=new ImageView(new Image(podcasttoadd.get(i).getImageURL()));
            Image image=new Image(podcast_i.getImageUrl());
            ImageView imageView = new ImageView(image);
            Label podcastdesc=new Label(podcast_i.getDescription());
            VBox label_title_desc = new VBox();
            Rectangle clip = new Rectangle();
            clip.setHeight(image.getHeight());
            clip.setWidth(image.getWidth());
            clip.setArcWidth(15);
            clip.setArcHeight(15);
            imageView.setClip(clip);
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage imageView1 = imageView.snapshot(parameters, null);


            // remove the rounding clip so that our effect can show through.
            imageView.setClip(null);

            // apply a shadow effect.
            imageView.setEffect(new DropShadow(20, Color.BLACK));

            // store the rounded image in the imageView.
            imageView.setImage(imageView1);

            label_title_desc.getChildren().addAll(podcastname,podcastdesc);
            Button play_btn = new Button("SHOW EPISODES");
            Button like_button = new Button("LIKE");
            like_button.setOnAction(event -> {
                try {
                    liked_event(podcast_i,podcasttoadd,application);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Button no_like_button = new Button("NO LIKE");
            no_like_button.setOnAction(event -> {
                try {
                    no_liked_event(podcast_i,podcasttoadd,application);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            int finalI = i;
            play_btn.setOnAction(event -> {
                try {
                    showEpisodes(application, finalI,podcast_i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            //play_btn.setOnAction(showEpisodes);
            imageView.setFitHeight(150);
            imageView.setFitWidth(200);
            if (!(application.getPreference().contains(podcast_i))){
                Container.addRow(0,imageView,label_title_desc);
                Container.addRow(1,play_btn,like_button);
            }else{
                Container.addRow(0,imageView,label_title_desc);
                Container.addRow(1,play_btn,no_like_button);
            }

            Container.setHgap(10);
            Podcastitems.add(Container);
        }
        podcastList.setItems(Podcastitems);
        //podcastList.setStyle("-fx-background-color: #000000;");

        //podcastList.setStyle("-fx-background-color: linear-gradient(#328BDB 0%, #207BCF 25%, #1973C9 75%, #0A65BF 100%);");
        loadDataEpisodes(application.getPodcastChoisi());

    }
    public void loadDataPodcastPreference(PodcastApp application) throws Exception{

        ///
        for (int i=0;i<application.getPreference().size();i++){
            GridPane Container=new GridPane();
            PodcastProperties podcast_i=application.getPreference().get(i);
            Label podcastname=new Label(podcast_i.getTitle());
            //ImageView image=new ImageView(new Image(podcasttoadd.get(i).getImageURL()));
            Image image=new Image(podcast_i.getImageUrl());
            ImageView imageView = new ImageView(image);
            Label podcastdesc=new Label(podcast_i.getDescription());
            VBox label_title_desc = new VBox();
            Rectangle clip = new Rectangle();
            clip.setHeight(image.getHeight());
            clip.setWidth(image.getWidth());
            clip.setArcWidth(15);
            clip.setArcHeight(15);
            imageView.setClip(clip);
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage imageView1 = imageView.snapshot(parameters, null);


            // remove the rounding clip so that our effect can show through.
            imageView.setClip(null);

            // apply a shadow effect.
            imageView.setEffect(new DropShadow(20, Color.BLACK));

            // store the rounded image in the imageView.
            imageView.setImage(imageView1);

            label_title_desc.getChildren().addAll(podcastname,podcastdesc);
            Button play_btn = new Button("SHOW EPISODES");
            Button like_button = new Button("LIKE");
            like_button.setOnAction(event -> {
                try {
                    liked_event(podcast_i,application.getPreference(),application);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            Button no_like_button = new Button("NO LIKE");
            no_like_button.setOnAction(event -> {
                try {
                    no_liked_event(podcast_i,application.getPreference(),application);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            int finalI = i;
            play_btn.setOnAction(event -> {
                try {
                    showEpisodes(application, finalI,podcast_i);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            });
            //play_btn.setOnAction(showEpisodes);
            imageView.setFitHeight(150);
            imageView.setFitWidth(200);
            if (!(application.getPreference().contains(podcast_i))){
                Container.addRow(0,imageView,label_title_desc);
                Container.addRow(1,play_btn,like_button);
            }else{
                Container.addRow(0,imageView,label_title_desc);
                Container.addRow(1,play_btn,no_like_button);
            }

            Container.setHgap(10);
            Podcastitems.add(Container);
        }
        podcastList.setItems(Podcastitems);
        //podcastList.setStyle("-fx-background-color: #000000;");

        //podcastList.setStyle("-fx-background-color: linear-gradient(#328BDB 0%, #207BCF 25%, #1973C9 75%, #0A65BF 100%);");
        loadDataEpisodes(application.getPodcastChoisi());

    }
    public void no_liked_event(PodcastProperties podcastProperties,ArrayList<PodcastProperties> podcasttoadd,PodcastApp application) throws Exception {
            application.getPreference().remove(podcastProperties);
            Podcastitems.clear();
            loadDataPodcast(podcasttoadd,application);
    }
    public void liked_event(PodcastProperties podcastProperties,ArrayList<PodcastProperties> podcasttoadd,PodcastApp application) throws Exception {
            application.getPreference().add(podcastProperties);
            Podcastitems.clear();
            loadDataPodcast(podcasttoadd,application);
    }
    public void showEpisodes(PodcastApp application,int index,PodcastProperties podcast) throws FileNotFoundException {
        currentPodcast=podcast;
        indexPodcast=index;
        items.clear();
        application.choose(podcast);
        loadDataEpisodes(podcast);
    }
    public void play(Episode episode,int index,ActionEvent event){
        indexEpisodePlay=index;
        currentEpisode=episode;
        if (media!=null) {
            stopVideo(event);
        }
        media = new Media(currentEpisode.getURL());

        mediaPlayer=new MediaPlayer(media);




        slidervolume.setValue(mediaPlayer.getVolume() * 50);
        slidervolume.valueProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                mediaPlayer.setVolume(slidervolume.getValue()/100);
            }
        });


        mediaPlayer.currentTimeProperty().addListener(new ChangeListener<Duration>() {
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {
                sliderprogression.setValue(newValue.toSeconds());
            }
        });



        sliderprogression.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                mediaPlayer.seek(Duration.seconds(sliderprogression.getValue()));

            }

        });



        playVideo(event);
    }

    public void loadDataEpisodes(PodcastProperties podcastProperties) throws FileNotFoundException {
        items.clear();
        for(int i=0;i<podcastProperties.getEpisodeList().size();i++){
            Episode podcastepisode=podcastProperties.getEpisodeList().get(i);
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
            int finalI = i;
            play_btn.setOnAction(e -> play(podcastepisode, finalI,e));

            /*if (i%2==0) {
                titre_episode.setStyle("-fx-text-fill: black");
                //episode.setStyle("-fx-background-color: #FF9900;");
            }else {
                titre_episode.setStyle("-fx-text-fill: orange");
                //episode.setStyle("-fx-background-color: #171717;");
            }*/
            episode.getChildren().addAll(titre_episode,play_btn,download_btn);
            items.add(episode);
        }
        episodeList.setItems(items);

    }
@FXML
private void nextEpisode(ActionEvent event){

        indexEpisodePlay=indexEpisodePlay+1;
    if (indexEpisodePlay==podcastApp.getPodcastChoisi().getEpisodeList().size()){
        indexEpisodePlay=0;
    }
        currentEpisode=podcastApp.getPodcastChoisi().getEpisodeList().get(indexEpisodePlay);
        play(currentEpisode,indexEpisodePlay,event);
}

    @FXML
    private void previousEpisode(ActionEvent event){
    indexEpisodePlay=indexEpisodePlay-1;
    if (indexEpisodePlay==-1){
        indexEpisodePlay=podcastApp.getPodcastChoisi().getEpisodeList().size()-1;
    }
    currentEpisode=podcastApp.getPodcastChoisi().getEpisodeList().get(indexEpisodePlay);
    play(currentEpisode,indexEpisodePlay,event);
    }
    @FXML
    private void openFileButton(ActionEvent event){
        if(mediaPlayer!=null)
            mediaPlayer.stop();
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file  (*.mp3)", "*");
        filechooser.getExtensionFilters().add(filter);
        File file = filechooser.showOpenDialog(null);

        filePath = file.toURI().toString();
        if (filePath != null){
            media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            mediaPlayer.pause();
        }

    }

    @FXML
    private void saveExit(ActionEvent event){
        podcastApp.SaveBeforeExit("./src/main/resources");
        System.exit(0);
    }

    @FXML
    private void playVideo(ActionEvent event){
        if (media!=null){
            mediaPlayer.play();
            mediaPlayer.setRate(1);

        }

    }
    @FXML
    private void pauseVideo(ActionEvent event){
        mediaPlayer.pause();
    }

    @FXML
    private void stopVideo(ActionEvent event){
        mediaPlayer.stop();

    }

    @FXML
    private void exitVideo(ActionEvent event){
        System.exit(0);

    }
    @FXML
    public Slider disvitesse;

    @FXML
    private void vitesseVideo(ActionEvent event){
        mediaPlayer.setRate(disvitesse.getValue());

    }


    @FXML
    private void fasterVideo(ActionEvent event){
        mediaPlayer.setRate(2);

    }


    @FXML
    private void slowVideo(ActionEvent event){
        mediaPlayer.setRate(0.75);

    }


    @FXML
    private void slowerVideo(ActionEvent event){
        mediaPlayer.setRate(0.5);
    }



}
