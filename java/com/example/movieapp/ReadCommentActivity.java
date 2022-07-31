package com.example.movieapp;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ReadCommentActivity extends AppCompatActivity
{
    StarRate starRate;
    Movie movie;
    RatingBar ratingbar;
    TextView comment;
    TextView movietitle;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_comment);

        movie = (Movie) getIntent().getSerializableExtra("movie");
        StarManager starManager = new StarManager(movie);
        starRate = starManager.getRating(ReadCommentActivity.this);
        movietitle = findViewById(R.id.name_of_movie);
        comment = findViewById(R.id.comment_rating);
        movietitle.setText(movie.getTitle());
        ratingbar.setRating(starRate.getStars());
        comment.setText(starRate.getComment());
    }
}
