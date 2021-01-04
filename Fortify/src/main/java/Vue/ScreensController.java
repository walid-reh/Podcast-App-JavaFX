package Vue;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

import javafx.util.Duration;
import model.PodcastApp;
import sample.VideoPlayerController;

import java.util.HashMap;

public class ScreensController extends StackPane {
    private VideoPlayerController video;
    //Contient les screens a display
    private HashMap<String, Node> screens=new HashMap<>();

    public ScreensController(){
        super();
    }

    public void addScreen(String name,Node screen){
        screens.put(name,screen);
    }

    public Node getScreen(String name){
        return screens.get(name);
    }

    public boolean loadScreen(String name,String resource){
        try {
            FXMLLoader myloader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myloader.load();
            ControlledScreen myScreenControler = (ControlledScreen) myloader.getController();
            myScreenControler.setScreenParent(this);
            addScreen(name,loadScreen);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean setScreen(final String name) {

        if (screens.get(name) != null) {   //screen loaded
            final DoubleProperty opacity = opacityProperty();

            if (!getChildren().isEmpty()) {    //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(1000), new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent t) {
                                getChildren().remove(0);                    //remove the displayed screen
                                getChildren().add(0, screens.get(name));     //add the screen
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();

            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));       //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(2500), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("screen hasn't been loaded!!! \n");
            return false;
        }
    }


        public boolean updatePodcastListActualite(String name,String ressource,PodcastApp application){
        try {
            FXMLLoader myloader = new FXMLLoader(getClass().getResource(ressource));
            Parent loadScreen = (Parent) myloader.load();
            MainViewerController podcastsMenuController = myloader.getController();
            podcastsMenuController.podcastApp=application;
            podcastsMenuController.currentPodcast=podcastsMenuController.podcastApp.getPodcastChoisi();
            podcastsMenuController.currentEpisode=podcastsMenuController.podcastApp.getPodcastChoisi().getEpisodeList().get(0);
            //podcastsMenuController.podcastApp=application;
            podcastsMenuController.loadDataPodcast(application.getPopular(),application);
            ControlledScreen myScreenControler = (ControlledScreen) podcastsMenuController;
            myScreenControler.setScreenParent(this);
            addScreen(name,loadScreen);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean updatePodcastListPreference(String name,String ressource,PodcastApp application){
        try {
            FXMLLoader myloader = new FXMLLoader(getClass().getResource(ressource));
            Parent loadScreen = (Parent) myloader.load();
            MainViewerController podcastsMenuController = myloader.getController();
            podcastsMenuController.podcastApp=application;
            podcastsMenuController.currentPodcast=podcastsMenuController.podcastApp.getPodcastChoisi();
            podcastsMenuController.currentEpisode=podcastsMenuController.podcastApp.getPodcastChoisi().getEpisodeList().get(0);
            //podcastsMenuController.podcastApp=application;
            podcastsMenuController.loadDataPodcastPreference(application);
            ControlledScreen myScreenControler = (ControlledScreen) podcastsMenuController;
            myScreenControler.setScreenParent(this);
            addScreen(name,loadScreen);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    public boolean updatePodcastListRequested(String name,String ressource,PodcastApp application){
        try {
            FXMLLoader myloader = new FXMLLoader(getClass().getResource(ressource));
            Parent loadScreen = (Parent) myloader.load();
            MainViewerController podcastsMenuController = myloader.getController();
            podcastsMenuController.podcastApp=application;
            podcastsMenuController.currentPodcast=podcastsMenuController.podcastApp.getPodcastChoisi();
            podcastsMenuController.currentEpisode=podcastsMenuController.podcastApp.getPodcastChoisi().getEpisodeList().get(0);
            //podcastsMenuController.podcastApp=application;
            podcastsMenuController.loadDataPodcast(application.getRequested(),application);
            ControlledScreen myScreenControler = (ControlledScreen) podcastsMenuController;
            myScreenControler.setScreenParent(this);
            addScreen(name,loadScreen);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
    /*
    public boolean updateEpisodeList(String name,String ressource,PodcastApp application){
        try {
            FXMLLoader myloader = new FXMLLoader(getClass().getResource(ressource));
            Parent loadScreen = (Parent) myloader.load();
            EpisodesMenuController episodesMenuController = myloader.getController();
            episodesMenuController.loadDataEpisodes(application,video);
            ControlledScreen myScreenControler = (ControlledScreen) episodesMenuController;
            myScreenControler.setScreenParent(this);
            addScreen(name,loadScreen);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }*/

        public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Screen didn't exist");
            return false;
        } else {
            return true;
        }
    }


}
