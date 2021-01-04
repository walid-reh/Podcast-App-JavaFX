package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;

import java.io.File;
import java .net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;


public class VideoPlayerController implements Initializable{
    private static Media media;
    private static MediaPlayer mediaPlayer;

    private static String filePath;

    @FXML
    private MediaView mediaView;
    public VideoPlayerController() {

    }
    public VideoPlayerController(String filePath){
        if (media!=null) {
            mediaPlayer.stop();
        }
        media = new Media(filePath);
        this.filePath=filePath;
        mediaPlayer=new MediaPlayer(media);

    }
    @FXML
    private void handleButtonAction(ActionEvent event){
        if(mediaPlayer!=null)
            mediaPlayer.stop();
        FileChooser filechooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file  (*.mp3)", "*.mp3");
        filechooser.getExtensionFilters().add(filter);
        File file = filechooser.showOpenDialog(null);

       // filePath = file.toURI().toString();
        filePath="http://www.wbur.org/modernlove/2018/02/28/from-he-to-she-in-first-grade-beals-frankel";
        if (filePath != null){
            Media media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);
            /*DoubleProperty width = mediaView.fitWidthProperty();
            DoubleProperty hight = mediaView.fitHeightProperty();
            width.bind(Bindings.selectDouble(mediaView.sceneProperty(),"width"));
            hight.bind(Bindings.selectDouble(mediaView.sceneProperty(),"height"));*/
            mediaPlayer.pause();
        }

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
    private void fastVideo(ActionEvent event){
        mediaPlayer.setRate(1.5);

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


    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}