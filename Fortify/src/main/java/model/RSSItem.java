package model;


import java.util.List;

public interface RSSItem {


    public String getCategoy();

    public String getTitle();

    public String getURL();

    public String getAuthor();

    public String getDescription();


    public String getLanguage();

    public String getImageURL();

    public List<Episode> getAllEpisodes();

  //  public String getCategory();

}
