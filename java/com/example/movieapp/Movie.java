package com.example.movieapp;

import androidx.annotation.NonNull;
import org.w3c.dom.Element;
import java.io.Serializable;

public class Movie implements Serializable
{
    private String title;
    private int id;
    private int runtime;
    private String imageurl;
    private String agerating;
    private String origTitle;
    private String genre;
    private String language;
    private String presentationmethod;
    private String theatre;
    private int eventid;
    private String startofmovie;

    Movie(@NonNull Element movieInfo) {
        this.title = movieInfo.getElementsByTagName("Title").item(0).getTextContent();
        this.id = Integer.parseInt(movieInfo.getElementsByTagName("ID").item(0).getTextContent());
        this.runtime = Integer.parseInt(movieInfo.getElementsByTagName("LengthInMinutes").item(0).getTextContent());
        this.imageurl = movieInfo.getElementsByTagName("Images").item(0).getTextContent();
        this.agerating = movieInfo.getElementsByTagName("Rating").item(0).getTextContent();
        this.origTitle = movieInfo.getElementsByTagName("OriginalTitle").item(0).getTextContent();
        this.genre = movieInfo.getElementsByTagName("Genres").item(0).getTextContent();
        this.language = movieInfo.getElementsByTagName("SpokenLanguage").item(0).getTextContent();
        this.presentationmethod = movieInfo.getElementsByTagName("PresentationMethod").item(0).getTextContent();
        this.theatre = movieInfo.getElementsByTagName("Theatre").item(0).getTextContent();
        this.eventid = Integer.parseInt(movieInfo.getElementsByTagName("EventID").item(0).getTextContent());
        this.startofmovie = movieInfo.getElementsByTagName("dttmShowStart").item(0).getTextContent();
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getOrigTitle()
    {
        return origTitle;
    }

    public String getGenre() {
        return genre;
    }

    public int getEventId() {
        return eventid;
    }

    public String getLanguage() {
        return language;
    }

    public String getPresentationMethod() {
        return presentationmethod;
    }

    public String getAgerating() { return agerating;}

    public String getTheatre() {
        return theatre;
    }

    public String getStartOfMovie()
    {
    startofmovie =startofmovie.split("T")[1];
    startofmovie =startofmovie.substring(0,5);
        return startofmovie;
    }
}
