package com.example.movieapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SeeRatingsActivity extends AppCompatActivity {
    ListView listView;
    TextView noRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_ratings);

    StarManager starManager = new StarManager();
    ArrayList<StarRate> starRates = starManager.getAllRatings(SeeRatingsActivity.this);
    noRating = (TextView) findViewById(R.id.no_rating);
    listView = (ListView) findViewById(R.id.your_ratings_view);

    ArrayList<String> parsedRatings = new ArrayList<>();
    for (StarRate starRate : starRates)
        {
            String parsed = "Elokuva: " + starRate.getTitle() + "\n\nKommentoi: " + starRate.getStars() + "/5" + "\n" + starRate.getComment();
            parsedRatings.add(parsed);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, parsedRatings)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent)
            {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE);
                return view;
            }
        };

        if (parsedRatings.size() < 1)
        {
            noRating.setText("Et ole vielä arvostellut yhtään elokuvaa");
        }
        else
        {
            listView.setAdapter(arrayAdapter);
        }
    }
}
