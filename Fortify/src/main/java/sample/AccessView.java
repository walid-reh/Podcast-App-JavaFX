package sample;

import Misc.EcouteurAccueil;
import Misc.EcouteurPreference;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import model.PodcastApp;

public class AccessView extends VBox {
    private PodcastApp app;
    private Button Accueil=new Button("Accueil");
    private Button Mypreferences=new Button("My preferences");
    private AccessFeaturesView features;

    public AccessView(PodcastApp app){
        this.app=app;
        this.features=new AccessFeaturesView(app);
        this.getChildren().addAll(Accueil,Mypreferences,features);
        this.Accueil.setOnAction(new EcouteurAccueil(app));
        this.Mypreferences.setOnAction(new EcouteurPreference(app));

    }

}
