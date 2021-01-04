package model;

import com.rometools.rome.feed.module.DCModuleImpl;
import com.rometools.rome.feed.synd.SyndEnclosureImpl;
import com.rometools.rome.feed.synd.SyndEntry;
import org.jdom2.Element;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Podcast implements RSSItem{

    private String title;
    private String URL;
    private String author;
    private String imageURL;
    private String description;
    private String category;
    private String language;
    //  private List<SyndEntry> entries;
    private List<Episode> episodes;
    public PodcastFinder feed;

    public Podcast (String URL) throws Exception {
        loadFeed(URL);
        this.URL = URL;
        feed = new PodcastFinder(URL);

    }


    @Override
    public String getLanguage(){
        return language;
    }

    @Override
    public String getDescription(){
        return description;
    }

    @Override
    public String getImageURL(){
        return  imageURL;
    }

    @Override
    public String getCategoy() {
        return category;
    }

    @Override
    public String getTitle () {
        return title;
    }

    @Override
    public String getURL () {
        return URL;
    }
    @Override
    public String getAuthor () {
        return author;
    }


    public void  setFeed(PodcastFinder p){
        this.feed=p;
    }





    @Override
    public List<Episode> getAllEpisodes () {
        return episodes;
    }

    public void loadFeed(String URL) throws Exception{
            feed = new PodcastFinder(URL);
            title = feed.getTitle();
            author = feed.getForeignElementValue("author");
            imageURL = feed.getImageUrl();
            description = feed.getDescription();
            language = feed.getLangage();
            episodes = getEntriesTitles();


    }


    private List<Episode> getEntriesTitles () throws Exception {
        List<Episode> episodeListEntryList = new ArrayList<>();
        List<SyndEntry> entries = feed.getEntries();
        //https://rss.art19.com/levar-burton-reads

        for (final Iterator iter = entries.iterator(); iter.hasNext(); ) {
            final SyndEntry entry = (SyndEntry) iter.next();
            Episode episodeListEntry = new Episode(); // construction d'une episode

            episodeListEntry.setName(entry.getTitle());
            episodeListEntry.setDescription(entry.getDescription().getValue());
            if(entry.getEnclosures().get(0) != null){
                episodeListEntry.setURL(entry.getEnclosures().get(0).getUrl());
                episodeListEntry.setType(entry.getEnclosures().get(0).getType());
            }

           // episodeListEntry.setURL(entry.getEnclosures().get(1).getUrl());

            episodeListEntry.setDate(entry.getPublishedDate().toString());
         //   episodeListEntry.setType(entry.getEnclosures().get(0).get);


            if ((episodeListEntry != null) && (episodeListEntry.getURL() != null) && (episodeListEntry.getName() != null)) {


                  episodeListEntryList.add(episodeListEntry);



            } else {
                continue;
            }



        }
        return episodeListEntryList;

    }
}
