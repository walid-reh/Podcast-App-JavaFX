package model;

import sample.VideoPlayerController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;


public class Episode {
    String name;
    String date;
    String description;
    String type;
    String URL;
    Duration duration;

    public Episode () {

    }

    public Episode (String name, String date, String description, String type, String URL) throws Exception {
        setName(name);
        setDate(date);
        setDescription(description);
        setType(type);
        setURL(URL);
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getDate () {
        return date;
    }

    public void setDate (String date) {
        this.date = date;
    }

    public String getDescription () {
        return description;
    }

    public void setDescription (String description) {
        this.description = description;
    }

    public String getType () {
        return type;
    }

    public void setType (String type) {
        this.type = type;
    }

    public String getURL () {
        return URL;
    }

    public void setURL (String URL) {
        this.URL = URL;
    }

}
