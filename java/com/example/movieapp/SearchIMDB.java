package com.example.movieapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SearchIMDB
{
    private static final String API_key = "k_x7zt341i";

    protected static ArrayList<String> readMovieJson(String urlid)
    {
        String fronturl = "https://imdb-api.com/en/API/SearchMovie/";
        String json = getJSON(fronturl, urlid);
        String movieid = "";
        ArrayList<String> imdbArray = new ArrayList<>();

        if (json != null)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = new JSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++)
                {
                    System.out.println(jsonArray.getJSONObject(i).getString("id"));
                    imdbArray.add("\nTitle: " + jsonArray.getJSONObject(i).getString("title") + "\nDescription: " +
                    jsonArray.getJSONObject(i).getString("description") +
                    jsonArray.getJSONObject(i).getString("id") + "\n");
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return imdbArray;
    }

    public static String readMovieDetailsJson(String text)
    {
        String fronturl = "https://imdb-api.com/API/Ratings/";
        String json = getJSON(fronturl, text);
        String imdbRating = null;
        if (json != null)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(json);
                imdbRating = jsonObject.getString("imDb");
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
        return imdbRating;
    }

    public static String getJSON(String fronturl, String urlid) {
        String response = null;
        try {
            URL url = new URL(fronturl + API_key + urlid);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            response = stringBuilder.toString();
            inputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return  response;
    }
}
