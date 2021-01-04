package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Misc.ButtonPodDesc;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Podcast;
import model.PodcastApp;
import model.PodcastProperties;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class PodcastsView  extends VBox implements Observer {
    ObservableList<GridPane> Podcastitems = FXCollections.observableArrayList();
    ListView<GridPane> listPodcasts=new ListView<GridPane>(Podcastitems);

    public PodcastsView (PodcastApp application) throws Exception {
        application.addObserver(this);
        //this.setStyle("-fx-background-color:linear-gradient(to top, #1b56af, #1e4f9b, #204888, #234175, #243a62);");
        Label PodcastsName=new Label("Podcasts:");

        PodcastsName.setStyle("-fx-font-size:25px;-fx-text-fill: white; -fx-font-family: 'Trebuchet MS', Helvetica, sans-serif;");
        this.getChildren().add(PodcastsName);
        //ListView
        ArrayList<PodcastProperties> podcasttoadd = application.getCurrentpodcasts();


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
            System.out.println(image.getHeight());
            clip.setArcWidth(15);
            clip.setArcHeight(15);
            imageView.setClip(clip);
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage imageView1 = imageView.snapshot(parameters, null);

            // remove the rounding clip so that our effect can show through.
            imageView.setClip(null);

            // apply a shadow effect.
            imageView.setEffect(new DropShadow(20, javafx.scene.paint.Color.BLACK));

            // store the rounded image in the imageView.
            imageView.setImage(imageView1);


            Podcastitems.add(Container);

            if (i%2==0) {
                podcastname.setStyle("-fx-text-fill: black");
                podcastdesc.setStyle("-fx-text-fill: black");
                Container.setStyle("-fx-background-color: #FF9900;");
            }else {
                podcastname.setStyle("-fx-text-fill: orange");
                podcastdesc.setStyle("-fx-text-fill: orange");
                Container.setStyle("-fx-background-color: #171717;");
            }
            label_title_desc.getChildren().addAll(podcastname,podcastdesc);
            ButtonPodDesc play_btn = new ButtonPodDesc("SHOW EPISODES",application,podcast_i);

            imageView.setFitHeight(150);
            imageView.setFitWidth(200);
            Container.addRow(0,imageView,label_title_desc,play_btn);
            Container.setHgap(10);
        }
        listPodcasts.setItems(Podcastitems);
        listPodcasts.setStyle("-fx-background-color: #000000;");
        this.getChildren().add(listPodcasts);
        listPodcasts.setStyle("-fx-background-color: linear-gradient(#328BDB 0%, #207BCF 25%, #1973C9 75%, #0A65BF 100%);");


    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("I should update my shit");
        PodcastApp application=((PodcastApp) o);
        this.listPodcasts.getItems().clear();
        this.Podcastitems.clear();
        ArrayList<PodcastProperties> podcasttoadd = application.getCurrentpodcasts();


        ///
        for (int i=0;i<podcasttoadd.size();i++){
            GridPane Container=new GridPane();
            PodcastProperties podcast_i=podcasttoadd.get(i);
            Label podcastname=new Label(podcast_i.getTitle());
            //ImageView image=new ImageView(new Image(podcasttoadd.get(i).getImageURL()));
            Image image= null;

            image = new Image(podcast_i.getImageUrl());

            ImageView imageView = new ImageView(image);
            Label podcastdesc=new Label(podcast_i.getDescription());
            VBox label_title_desc = new VBox();
            Rectangle clip = new Rectangle();
            clip.setHeight(image.getHeight());
            clip.setWidth(image.getWidth());
            System.out.println(image.getHeight());
            clip.setArcWidth(15);
            clip.setArcHeight(15);
            imageView.setClip(clip);
            SnapshotParameters parameters = new SnapshotParameters();
            parameters.setFill(Color.TRANSPARENT);
            WritableImage imageView1 = imageView.snapshot(parameters, null);

            // remove the rounding clip so that our effect can show through.
            imageView.setClip(null);

            // apply a shadow effect.
            imageView.setEffect(new DropShadow(20, javafx.scene.paint.Color.BLACK));

            // store the rounded image in the imageView.
            imageView.setImage(imageView1);


            Podcastitems.add(Container);

            if (i%2==0) {
                podcastname.setStyle("-fx-text-fill: black");
                podcastdesc.setStyle("-fx-text-fill: black");
                Container.setStyle("-fx-background-color: #FF9900;");
            }else {
                podcastname.setStyle("-fx-text-fill: orange");
                podcastdesc.setStyle("-fx-text-fill: orange");
                Container.setStyle("-fx-background-color: #171717;");
            }
            label_title_desc.getChildren().addAll(podcastname,podcastdesc);
            label_title_desc.setSpacing(30);
            ButtonPodDesc play_btn = new ButtonPodDesc("SHOW EPISODES",application,podcast_i);
            label_title_desc.setPrefWidth(240);
            imageView.setFitHeight(150);
            imageView.setFitWidth(200);
            Container.addRow(0,imageView,label_title_desc,play_btn);
            Container.setHgap(10);
        }
        listPodcasts.setItems(Podcastitems);
        listPodcasts.setStyle("-fx-background-color: #000000;");
        listPodcasts.setStyle("-fx-background-color: linear-gradient(#328BDB 0%, #207BCF 25%, #1973C9 75%, #0A65BF 100%);");

    }
}