package sample;

import Misc.EcouteurRechercher;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.PodcastApp;

import javax.xml.soap.Text;

public class AccessFeaturesView extends VBox {
    //(String motcle, String genre, String dateBefor, String dateafter, String language)
    PodcastApp app;
    private Label motcle_label=new Label("Mot clé: ");
    private Label genre_label=new Label("Genre:");
    private Label dateBefore=new Label("Date Before:");
    private Label dateAfter=new Label("Date After:");
    private Label langue=new Label("Langue:");
    private TextField motcle_field=new TextField();
    private TextField genre_field=new TextField();
    private TextField dateBefore_field=new TextField();
    private TextField dateAfter_field=new TextField();
    private TextField langue_field=new TextField();
    private Button ResearchButton=new Button("Rechercher");
    public AccessFeaturesView(PodcastApp app){
        this.app=app;
        HBox Containermotcle=new HBox(motcle_label,motcle_field);
        Containermotcle.setSpacing(20);
        HBox Containergenre=new HBox(genre_label,genre_field);
        Containergenre.setSpacing(20);
        HBox Containerlangue=new HBox(langue,langue_field);
        Containerlangue.setSpacing(20);
        HBox ContainerdateBefore=new HBox(dateBefore,dateBefore_field);
        ContainerdateBefore.setSpacing(20);
        HBox ContainerdateAfter=new HBox(dateAfter,dateAfter_field);
        ContainerdateAfter.setSpacing(20);
        this.setSpacing(10);
        //    public EcouteurRechercher(PodcastApp app, TextField motcle_field, TextField genre_field,TextField langue_field, TextField dateBefore_field, TextField dateAfter_field){
        ResearchButton.setOnAction(new EcouteurRechercher(app,motcle_field,genre_field,langue_field,dateBefore_field,dateAfter_field));
        this.getChildren().addAll(Containermotcle,Containergenre,ContainerdateBefore,ContainerdateAfter,Containerlangue,ResearchButton);

    }

}
