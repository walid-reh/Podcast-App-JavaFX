package model;


 import javafx.scene.layout.BorderPane;
 import kong.unirest.JsonNode;
 import kong.unirest.Unirest;
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
 import kong.unirest.json.JSONArray;
 import  kong.unirest.HttpResponse;
 import kong.unirest.json.JSONObject;
 import kong.unirest.json.JSONString;
 import org.apache.http.client.methods.CloseableHttpResponse;
 import org.apache.http.client.methods.HttpGet;
 import org.apache.http.impl.client.CloseableHttpClient;
 import org.apache.http.impl.client.HttpClients;
 import java.util.ArrayList;
 import java.util.List;
import java.lang.Object;
import java.sql.Timestamp;




public class PodcastApiFetcher {
    private ArrayList<String> listeRss;
    private ArrayList<String> listeRSSpopular;
    public ArrayList<PodcastProperties> PPRequestResult;
    public ArrayList<PodcastProperties> listepopular;

    public PodcastApiFetcher(){
        this.listeRss=new ArrayList<String>();
        this.listeRSSpopular=new ArrayList<String>();
        this.PPRequestResult=new ArrayList<PodcastProperties>();
        this.listepopular=new ArrayList<PodcastProperties>();
    }

    public ArrayList<PodcastProperties> getPPRequestResult() {
        return PPRequestResult;
    }

    public ArrayList<PodcastProperties> getListepopular() {
        return listepopular;
    }



    public ArrayList<String> search(String motcle, String genre, String dateBefore, String dateafter, String language){

        String request="https://listen-api.listennotes.com/api/v2/search?q=";
        String cle="";
        boolean dateBeforeisValid=false;
        boolean dateAfterisValid=false;
        int j=0;
        for(int i=0;i<motcle.length();i++){
            if(motcle.charAt(i)!=' '){
                cle=cle+motcle.charAt(i);
            }
            else{
                cle=cle+"%20";
            }
        }
        Timestamp dateBefor=new Timestamp(0);
        Timestamp dateaftr=new Timestamp(0);

        String[] ParsedDateAfter=dateafter.split("/");
        if(ParsedDateAfter.length<3){
            System.out.println("YOU NEED TO HAVE 3 ARGUMENTS");
        }else{
            dateAfterisValid=true;

            dateaftr= new Timestamp(Integer.parseInt(ParsedDateAfter[2]),Integer.parseInt(ParsedDateAfter[1]),Integer.parseInt(ParsedDateAfter[0]),0,0,0,0);
        }

        String[] ParsedDateBefor=dateafter.split("/");
        if(ParsedDateAfter.length<3){
            System.out.println("YOU NEED TO HAVE 3 ARGUMENTS");
        }else{
            dateAfterisValid=true;
            dateBefor= new Timestamp(Integer.parseInt(ParsedDateBefor[2]),Integer.parseInt(ParsedDateBefor[1]),Integer.parseInt(ParsedDateBefor[0]),0,0,0,0);
        }
        if(dateafter==""){
            dateAfterisValid=true;
            dateaftr=new Timestamp(0);
        }
        if(dateBefore==""){
            dateBeforeisValid=true;
            dateBefor=new Timestamp(0);

        }

        dateBefore=Long.toString(dateBefor.getTime());
        dateafter=Long.toString(dateaftr.getTime());


        System.out.println("cle:" +cle);
        ArrayList<String> liste = new ArrayList<String>();
        if(!genre.equals("")) {

            HttpResponse<JsonNode> response2 = Unirest.get("https://listen-api.listennotes.com/api/v2/genres")
                    .header("X-ListenAPI-Key", "2311beaa41054b63996aa886ce03d339")
                    .asJson();
            JSONArray genres = (JSONArray) response2.getBody().getObject().get("genres");
            for (int i = 0; i < genres.length(); i++) {
                JSONArray array = (JSONArray) response2.getBody().getObject().get("genres");
                JSONObject objet = (JSONObject) array.get(i);
                String object = (String) objet.get("id").toString();
                String THECHOSENONE = (String) objet.get("name");
                //String id=(String) objet.get("name").get;
                if (THECHOSENONE.contains(genre)) {
                    liste.add(object);
                    System.out.println(THECHOSENONE);
                }
            }
        }
        if(liste.size()==0) {


            if (dateBeforeisValid == true && dateAfterisValid == true) {
                request = request + cle + "&genre_ids=" + "" + "%2C" + "" + "&published_before=" + dateafter + "&published_after=" + dateBefore + "&language=" + language;
            }
            else{
                request = request + cle + "&genre_ids=" + "" + "%2C" + "" + "&language=" + language;
            }
        }
        else{
            if(dateBeforeisValid == true && dateAfterisValid == true) {
                request = request + cle + "&genre_ids=" + liste.get(0) + "%2C" + liste.get(0)+"&published_before=" + dateafter + "&published_after=" + dateBefore+"&language=" + language;
            }
            else{
                request = request + cle + "&genre_ids=" + liste.get(0) + "%2C" + liste.get(0)+"&language=" + language;
            }
        }
        HttpResponse<JsonNode> response4 = Unirest.get(request)
                .header("X-ListenAPI-Key", "2311beaa41054b63996aa886ce03d339")
                .asJson();
        int count= (int)response4.getBody().getObject().get("count");
        int limit=0;
        for(int i=0;i<count;i++) {
            JSONArray array = (JSONArray) response4.getBody().getObject().get("results");
            JSONObject objet = (JSONObject) array.get(i);
            String THECHOSENONE = (String) objet.get("rss");

            PodcastFinder feeder = new PodcastFinder(THECHOSENONE);
            if(feeder==null || feeder.getfeed()==null){

                continue;
            }else {
                if((feeder.getfeed().getLanguage() == null )&& (feeder.getfeed()==null)  && (feeder.getfeed().getTitle() == null) && (feeder.getfeed().getUri() == null )){
                    continue;
                }
                limit++;
                if(limit<=10){
                    listeRss.add(THECHOSENONE);
                }
                //listeRss.add(THECHOSENONE);
                else{
                    break;
                }
            }

        }
        return  listeRss;
    }



    public ArrayList<PodcastProperties>searchPopularPodcasts() throws Exception {
        searchpopular();
        ArrayList<PodcastProperties> ppd=parsepopular();
        return ppd;
    }
    private ArrayList<String> searchpopular(){

        HttpResponse<JsonNode> response4 = Unirest.get("https://listen-api.listennotes.com/api/v2/best_podcasts?genre_ids=02C144&page=2&region=us&safe_mode=1")
                .header("X-ListenAPI-Key", "2311beaa41054b63996aa886ce03d339")
                .asJson();
       // int count= (int)response4.getBody().getObject().get("count");
        JSONArray array= (JSONArray)response4.getBody().getObject().get("podcasts");
        int counter=0;
        int i=0;

        int stopper = 0;
        while (counter<3 && stopper < 5) {
            //JSONArray array=(JSONArray)response4.getBody().getObject().get("results");
            JSONObject objet = (JSONObject) array.get(i);
            String THECHOSENONE = (String) objet.get("rss");
            PodcastFinder feed = new PodcastFinder(THECHOSENONE);
       //     if(feed==null || (feed.getfeed().getLanguage() == null )|| (feed.getfeed()==null) || (feed.getfeed().getAuthor()==null)|| (feed.getfeed().getImage().getUrl() == null) || (feed.getfeed().getDescription() == null) || (feed.getfeed().getTitle() == null) || (feed.getfeed().getLink() == null) ){
            if(feed==null || (feed.getfeed().getLanguage() == null )|| (feed.getfeed()==null)  || (feed.getfeed().getTitle() == null) || (feed.getfeed().getLink() == null) || (feed.getfeed().getImage().getUrl() == null) ){

                stopper++;
                continue;
            }else {
                listeRSSpopular.add(THECHOSENONE);
                counter++;
                stopper++;
            }
            i++;


        }
        return  listeRSSpopular;
    }



    public ArrayList<PodcastProperties> parse(ArrayList<String> listedeflux) throws Exception {
        for(int i=0;i<listedeflux.size();i++){
            ArrayList<Episode> listeEpisode = new ArrayList<Episode>();
            Podcast pod= new Podcast(listedeflux.get(i));
            PodcastFinder pdf = new PodcastFinder(listedeflux.get(i));
            pod.setFeed(pdf);
            PodcastProperties p= new PodcastProperties();
            p.setLanguage(pdf.getLangage());
            p.setImageUrl(pdf.getImageUrl());
            p.setDescription(pdf.getDescription());
            p.setAuthor(pdf.getAuthor());
            int count=50;
            for(int j=0;j<pdf.getEntries().size();j++){
                Episode ep= new Episode();
                ep.setName(pdf.getEntries().get(j).getTitle());
                ep.setURL(pdf.getEntries().get(j).getEnclosures().get(0).getUrl());
                ep.setDescription(pdf.getEntries().get(j).getDescription().getValue());
              //  ep.setType(pdf.getEntries().get(i).get);
                if(j<count){
                    listeEpisode.add(ep);
                }
                else{
                    break;
                }
                //listeEpisode.add(ep);
            }
            p.setEpisodeList(listeEpisode);

            PPRequestResult.add(p);
        }
        return PPRequestResult;
    }


    public ArrayList<PodcastProperties> parsepopular() throws Exception {
        for(int i=0;i<listeRSSpopular.size();i++){
            ArrayList<Episode> listeEpisode = new ArrayList<Episode>();
            Podcast pod= new Podcast(listeRSSpopular.get(i));
            PodcastFinder pdf = new PodcastFinder(listeRSSpopular.get(i));
            pod.setFeed(pdf);
            PodcastProperties p= new PodcastProperties();
            p.setLanguage(pdf.getLangage());
            p.setImageUrl(pdf.getImageUrl());
            p.setDescription(pdf.getDescription());
            p.setAuthor(pdf.getAuthor());
            int count=50;
            for(int j=0;j<pdf.getEntries().size();j++){
                Episode ep= new Episode();
                ep.setName(pdf.getEntries().get(i).getTitle());
                ep.setURL(pdf.getEntries().get(i).getEnclosures().get(0).getUrl());
                ep.setDescription(pdf.getEntries().get(i).getDescription().getValue());
                //  ep.setType(pdf.getEntries().get(i).get);
                if(j<count){
                    listeEpisode.add(ep);
                }
              //  listeEpisode.add(ep);
                else{
                    break;
                }
            }
            p.setEpisodeList(listeEpisode);

            listepopular.add(p);
        }
        return listepopular;
    }



}
