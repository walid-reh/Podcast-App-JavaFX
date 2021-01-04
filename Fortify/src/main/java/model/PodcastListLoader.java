package model;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PodcastListLoader {
    private List<PodcastProperties> iTunesData;
    private PodcastListEntry podcastEntry;
    private String configPath;
    private String iTunesFile;

    public PodcastListLoader (String configPath) {
        this.configPath = configPath;
        iTunesData = new ArrayList<>();
        podcastEntry = new PodcastListEntry();
        iTunesFile = "data/iTunes.json";
        loadSavedPodcasts();
    }

    protected void loadSavedPodcasts () {
        loadLocalPodcastJSON(iTunesData, iTunesFile);
    }

    protected void loadLocalPodcastJSON (List<PodcastProperties> dataList, String fileName) {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(configPath + "/" + fileName));
            podcastEntry = gson.fromJson(reader, PodcastListEntry.class);

            for (PodcastProperties item : podcastEntry.getProperties()) {
                dataList.add(item);
            }

        } catch (IOException ex) {
        }
    }

    public List<PodcastProperties> getITunesData () {
        return iTunesData;
    }

    public void addPodcastEntry (RSSItem feed) {
        podcastEntry.addPodcast(feed.getTitle(), feed.getURL(), feed.getAuthor(), feed.getCategoy(), feed.getDescription() , feed.getImageURL(), feed.getLanguage() ,feed.getAllEpisodes());
        updateLocalPodcastData(iTunesFile);

    }

    public void updateLocalPodcastData (String fileName) {
        try {
            Gson gson = new Gson();
            gson = new GsonBuilder().setPrettyPrinting().create();
            ArrayList<PodcastProperties> listeToappend = new ArrayList<PodcastProperties>();
            int a  = podcastEntry.getProperties().size();
            listeToappend.add(podcastEntry.getProperties().get(a-1));
            PodcastListEntry podacstor = new PodcastListEntry(listeToappend);
            String json = gson.toJson(podacstor);
            FileWriter writer = new FileWriter(configPath + "/" + fileName,true);
            writer.write(json);
            writer.close();
        //    for (int i=0; i<podcastEntry.getProperties().size();i++){
         //       podcastEntry.removePodcast(i);
         //    }
        } catch (IOException e) {
            System.out.println("Error writing to file '" + configPath + "/" + fileName);
        }
    }

    public void ClearAndSave(String fileName, PodcastListEntry podcastentered){
        try {
            Gson gson = new Gson();
            gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(podcastentered);
            //      System.out.println("this is the size that we're going to add Using the method Clear and Save:"+ liste.getProperties().size());
            FileWriter writer = new FileWriter(configPath + "/" + fileName);
            writer.write(json);
            writer.close();
            podcastentered.getProperties().clear();
        } catch (IOException e) {
            System.out.println("Error writing to file '" + configPath + "/" + fileName);
        }
    }

    public void removePodcastEntry (int index) {
        podcastEntry.removePodcast(index);
        updateLocalPodcastData(iTunesFile);
    }

    public List<Episode> getEpisodeList (int podcastIndex) {
        return podcastEntry.getEpisodes(podcastIndex);
    }
}
