package model;

import java.util.ArrayList;
import java.util.List;

public class PodcastProperties {
    private String title;
    private String URL;
    private String author;
    private String category;
    private List<Episode> episodeList;
    private String imageUrl;
    private String description;
    private String language;
    public PodcastProperties () {

    }





    public void setLanguage(String region){
        this.language=language;
    }


    public void setImageUrl(String imageURL){
        this.imageUrl=imageURL;
    }
    public void setDescription (String description) {
        this.description = description;
    }

    public void setTitle (String title) {
        this.title = title;
    }

    public void setURL (String URL) {
        this.URL = URL;
    }

    public String getTitle () {
        return title;
    }

    public String getLanguage () {
        return language;
    }

    public String getAuthor () {
        return author;
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    public String getURL () {
        return URL;
    }

    public void setCategory (String category) {
        this.category = category;
    }

    public String getCategory () {
        return category;
    }

    public String getImageUrl () {
        return imageUrl;
    }

    public String getDescription(){
        return description;
    }
    public void setEpisodeList (List<Episode> episodes) {
        episodeList = episodes;
    }

    public List<Episode> getEpisodeList () {
        List<Episode> tempEpisodeList = new ArrayList<>();

        for (Episode episode : episodeList) {
            episode.setName(episode.getName().trim());
            tempEpisodeList.add(episode);
        }
        return tempEpisodeList;
    }


}
