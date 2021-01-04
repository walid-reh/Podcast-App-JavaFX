package model;

import com.rometools.rome.feed.synd.SyndEntry;

import java.util.ArrayList;
import java.util.List;

public class PodcastListEntry {
    public ArrayList<PodcastProperties> podcasts;

    public PodcastListEntry(ArrayList<PodcastProperties> liste){
        podcasts = liste;
    }
    public PodcastListEntry(){
    }

    public ArrayList<PodcastProperties> getProperties () {
        return podcasts;
    }

    public void addPodcast (String title, String URL, String author, String category,String desciption,String imageURL , String language , List<Episode> episodes) {
      //  podcasts=new ArrayList<PodcastProperties>();
        PodcastProperties prop = new PodcastProperties();
        prop.setTitle(title);
        prop.setURL(URL);

        prop.setAuthor(author);

        prop.setDescription(desciption);

        prop.setImageUrl(imageURL);

        prop.setLanguage(language);

        prop.setEpisodeList(episodes);

        podcasts.add(prop);
    }

    public void removePodcast (int index) {
        podcasts.remove(index);
    }

    public ArrayList<Episode> getEpisodes (int podcastIndex) {
        return (ArrayList<Episode>)podcasts.get(podcastIndex).getEpisodeList();
    }
}
