package com.example.movieapp;

import android.content.Context;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StarManager {
    private int eventId;
    private String movieTitle;
    private Movie movie;
    StarRate starRate;

    StarManager() {
    }

    StarManager(Movie movie) {
        this.movie = movie;
        this.eventId = movie.getEventId();
        this.movieTitle = movie.getTitle();
    }

    public void addRating ( int stars, String text, Context context)
    {
        starRate = new StarRate(movie, eventId, movieTitle, stars, text);
            Gson gson = new Gson();
            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(eventId + ".json", Context.MODE_PRIVATE));
                String jsonString = gson.toJson(starRate);
                outputStreamWriter.write(jsonString);
                outputStreamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public StarRate getRating (Context context)
        {
            Gson gson = new Gson();
            try {
                String jsonString = new String(Files.readAllBytes(Paths.get(context.getFilesDir().toString() + "/" + eventId + ".json")));
                starRate = gson.fromJson(jsonString, StarRate.class);
            } catch (IOException e) {
                return null;
            }
            return starRate;
        }

        public ArrayList<StarRate> getAllRatings (Context context)
        {
            ArrayList<StarRate> starRates = new ArrayList<>();
            ArrayList<String> fileNames = new ArrayList<>();
            try {
                File[] files = new File(context.getFilesDir().toString()).listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isFile()) {
                            fileNames.add(file.getName());
                        }
                    }
                    for (String fileName : fileNames) {
                        Gson gson = new Gson();
                        String jsonString = new String(Files.readAllBytes(Paths.get(context.getFilesDir().toString() + "/" + fileName)));
                        starRate = gson.fromJson(jsonString, StarRate.class);
                        starRates.add(starRate);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return starRates;
        }
    }
