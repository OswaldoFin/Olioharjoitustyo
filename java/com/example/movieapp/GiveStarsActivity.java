package com.example.movieapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

public class GiveStarsActivity extends AppCompatActivity
{
    StarManager starManager;
    Movie movie;
    RatingBar giveStars;
    EditText ratingText;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give_stars);
        movie = (Movie) getIntent().getSerializableExtra("movie");
        starManager = new StarManager(movie);
        giveStars = findViewById(R.id.stars_bar);
        ratingText = findViewById(R.id.rating_text);
    }

    public void sendRating(View view)
    {
        starManager.addRating((int) giveStars.getRating(), ratingText.getText().toString(), GiveStarsActivity.this);
        super.finish();
        super.onBackPressed();
    }
}
