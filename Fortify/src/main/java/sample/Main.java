package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Episode;
import model.Podcast;
import model.PodcastFinder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Podcast;
import model.*;
import sample.EpisodeView;
import sample.SearchView;

import java.io.File;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{
        String current=new File(".").getCanonicalPath();
        System.out.println(current+ " PATH! ");
        PodcastApp pd = new PodcastApp("./src/main/resources");
        System.out.println("La taille de json : " + pd.getPreference().size());
        System.out.println("la catégorie est : " + pd.getPreference().get(0).getTitle());
        System.out.println("la catégorie est : " + pd.getPreference().get(1).getTitle());
        BorderPane global_layout = new BorderPane();
        PodcastsView centre_page = new PodcastsView(pd);
        GridPane main_part = new GridPane();
        VBox favoris = new VBox();

        //String title, int nombreEpisodes,ArrayList<Episode> episodes, String description
        // String url,String title,String image,String description
        SearchView searchBar = new SearchView();
        global_layout.setTop(searchBar);
        primaryStage.setTitle("FORTIFY");
        primaryStage.setScene(new Scene(global_layout, 1280, 800));
        //global_layout.getChildren().add(searchBar);
        //global_layout.setCenter(centre_page);



        //  Episode episode1 = new Episode("url","episode1","images/image_episode/episode1.png","look at this");
        // Episode episode2 = new Episode("url","episode2","images/image_episode/episode1.png","look at this");
        //Episode episode3 = new Episode("url","episode3","images/image_episode/episode1.png","look at this");

        //ArrayList<Episode> episodes = new ArrayList<Episode>();
        //podcast.
        //Podcast podcast = new Podcast("The million",3,episodes,"u must watch" );
        // PodcastFinder feed = new PodcastFinder("http://sw7x7.libsyn.com/rss") ;

        /*ListView<String> list_titre=new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList (podcast.getAllEpisodes().get(1).getName()
        );
        list_titre.setItems(items);
        list_titre.setPrefSize(100, 250);*/










        VideoPlayerController video = new VideoPlayerController(pd.getPodcastChoisi().getEpisodeList().get(0).getURL());
        //PodcastsView list_podcast=new PodcastsView("http://sw7x7.libsyn.com/rss",video);
        EpisodeView episode_part = new EpisodeView(pd,video);
        //global_layout.setRight(episode_part);
        //global_layout.setStyle("-fx-background-color: #000000; -fx-pref-height: 800; -fx-pref-width: 1000");
        centre_page.setStyle("-fx-pref-height: 500;" +
                "    -fx-pref-width: 900;" +
                "    -fx-background-color: rgb(46, 46, 46);" +
                "    -fx-background-radius: 15px;");
        centre_page.setPadding(new Insets(20));
        episode_part.setStyle("-fx-pref-height: 500;" +
                "    -fx-pref-width: 374;"
                +"-fx-background-color: rgb(46, 46, 46);-fx-background-radius: 15px; ");
        episode_part.setPadding(new Insets(20));
        searchBar.setStyle("-fx-background-color: #484848;" +
                "    -fx-pref-height: 62;" +
                "    -fx-pref-width: 1000;");
        main_part.addRow(0,centre_page,episode_part);
        main_part.setPadding(new Insets(20));
        main_part.setStyle("    -fx-pref-height: 500;" +
                "    -fx-pref-width: 1000;");
        global_layout.setCenter(main_part);
        main_part.setHgap(10);
        /*
        centre_page.getChildren().add(favoris);
        centre_page.getChildren().add(list_podcast);
        centre_page.getChildren().add(episode_part);
        favoris.setPrefHeight(581);
        list_podcast.setPrefHeight(581);
        favoris.setPrefWidth(270);
        list_podcast.setPrefWidth(786);


        primaryStage.setTitle("Podhub");
        primaryStage.setScene(new Scene(global_layout, 1280, 800));
        //global_layout.getChildren().add(searchBar);
        global_layout.setCenter(centre_page);



            */

        Parent player = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //  primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root));
        global_layout.setBottom(player);
        global_layout.setLeft(new AccessView(pd));

        primaryStage.show();
    }



   /* public static void main(String[] args) throws Exception {

        PodcastListLoader podcastData = new PodcastListLoader("/home/walid/Bureau/Fruitify");
        Podcast pod= new Podcast("https://rss.art19.com/levar-burton-reads");
        pod.loadFeed("https://rss.art19.com/levar-burton-reads");
        podcastData.addPodcastEntry(pod);

    }*/
   public static void main(String[] args) {
       launch(args);
    }
}