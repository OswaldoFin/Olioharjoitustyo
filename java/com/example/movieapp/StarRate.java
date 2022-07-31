package com.example.movieapp;

public class StarRate
{
    private int stars;
    private String comment;
    private int id;
    private String title;
    private Movie movie;

    StarRate(Movie movie, int id, String title, int stars, String text)
    {
        this.stars = stars;
        this.comment = text;
        this.id = id;
        this.title = title;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Movie getMovie() {
        return movie;
    }
}