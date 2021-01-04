package model;

import javafx.beans.InvalidationListener;

import java.lang.reflect.Array;
import java.util.Observable;

import java.util.ArrayList;
import java.util.List;

import model.PodcastApiFetcher;

public class PodcastApp extends Observable{
// timer to add
//View to add
    private  PodcastProperties podcastchoisi;
    private ArrayList<PodcastProperties> preference = new ArrayList<>();
    private ArrayList<PodcastProperties> currentpodcasts=preference;
    private ArrayList<PodcastProperties>  popular;
    private ArrayList<PodcastProperties> requested = new ArrayList<>();

    private String datajsonfile = "data/iTunes.json" ;


    // Podcast pod= new

    public void switchToPreference(){
        this.currentpodcasts=getPreference();
        setChanged();
        notifyObservers();
    }
    public void switchToRequested(){
        this.currentpodcasts=getRequested();


    }
    public void switchToPopular(){
        this.currentpodcasts=getPopular();
        this.podcastchoisi=this.currentpodcasts.get(0);
        setChanged();
        notifyObservers();
    }


    //pour le bouton coeur
    public void addToPreference(PodcastProperties podcast){
        if(!getPreference().contains(podcast)){
            getPreference().add(podcast);
        }else{
            System.out.println("The Podcast is already in the Preference list");
        }

    }
    public void SaveBeforeExit(String configpath){
        PodcastListLoader podcastPreference = new PodcastListLoader(configpath);
        PodcastListEntry pd = new PodcastListEntry(preference);
        podcastPreference.ClearAndSave(datajsonfile,pd);
        System.out.println("DONE SAVING");
       // ClearAndSave
        preference.clear();

    }



    public void FilterPodcast(String motcle, String genre, String dateBefore,String dateAfter,String language) throws Exception {
        requested.clear();
        PodcastApiFetcher Fetcher=new PodcastApiFetcher();
        ArrayList<String> rsslist=Fetcher.search(motcle,genre,dateBefore,dateAfter,language);
        ArrayList<PodcastProperties> test = Fetcher.parse(rsslist);


        for (int c=0 ; c<test.size() ;c++ ){
            requested.add(test.get(c));
        }
        this.switchToRequested();
        if(getCurrentpodcasts().get(0)!=null)
        this.podcastchoisi=getCurrentpodcasts().get(0);
        setChanged();
        notifyObservers();

    }



    public void removeFromPreference(PodcastProperties podcast){
        if(getPreference().contains(podcast)){
            getPreference().remove(podcast);
        }else{
            System.out.println("The Podcast doesn't exist in the Preference list");
        }
    }


    public PodcastApp(String configpath) throws Exception {
        PodcastListLoader podcastData = new PodcastListLoader(configpath);
        podcastData.loadLocalPodcastJSON(preference,datajsonfile);
        podcastchoisi=this.preference.get(0);
        PodcastApiFetcher mypodcastfetcher=new PodcastApiFetcher();
        this.popular=mypodcastfetcher.searchPopularPodcasts();

    }

    public PodcastProperties getPodcastChoisi(){
        return podcastchoisi;
    }

    public boolean choose(PodcastProperties podcast){
        if(podcast==null){
            setChanged();
            notifyObservers();
            return false;
        }
        podcastchoisi=podcast;
        setChanged();
        notifyObservers();
        return true;
    }

    public ArrayList<PodcastProperties> getCurrentpodcasts(){
        return this.currentpodcasts;
    }
    public ArrayList<PodcastProperties> getPreference(){
        return preference;
    }

    public ArrayList<PodcastProperties> getPopular(){ return popular; }

    public ArrayList<PodcastProperties> getRequested(){ return requested; }


}
