package Misc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import model.PodcastApp;

public class EcouteurRechercher  implements EventHandler<ActionEvent> {
    private TextField motcle_field;
    private TextField genre_field;
    private TextField dateBefore_field;
    private TextField dateAfter_field;
    private TextField langue_field;

    private PodcastApp app;
    public EcouteurRechercher(PodcastApp app, TextField motcle_field, TextField genre_field,TextField langue_field, TextField dateBefore_field, TextField dateAfter_field){
        this.app=app;
        this.motcle_field=motcle_field;
        this.genre_field=genre_field;
        this.langue_field=langue_field;
        this.dateBefore_field=dateBefore_field;
        this.dateAfter_field=dateAfter_field;
    }
    //    public void FilterPodcast(String motcle, String genre, String dateBefore,String dateAfter,String language) throws Exception {
    @Override
    public void handle(ActionEvent event) {
        try {
            if(motcle_field.getText()==null){
                this.motcle_field.setText("");
            }
            if(genre_field.getText()==null) {
                this.genre_field.setText("");
            }
            if(dateBefore_field.getText()==null){
                this.dateBefore_field.setText("");
            }

            if(this.dateAfter_field.getText()==null){
                this.dateAfter_field.setText("");
            }
            if(this.langue_field.getText()==null)
            this.langue_field.setText("");

            System.out.println(motcle_field.getText() +"\td  "+genre_field.getText()+"\td "+ dateBefore_field.getText()+"\td "+ dateAfter_field.getText()+"\td "+langue_field.getText());
            app.FilterPodcast(motcle_field.getText(),genre_field.getText(),dateBefore_field.getText(),dateAfter_field.getText(),langue_field.getText());

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
