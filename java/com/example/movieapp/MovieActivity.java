package com.example.movieapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.file.Files;
import java.nio.file.Paths;

public class MovieActivity extends AppCompatActivity
{
    Movie movie;
    TextView movietitle;
    TextView originaltitle;
    TextView genres;
    TextView language;
    TextView length;
    TextView presentationmethod;
    TextView theatrename;
    TextView moviestart;
    TextView agerating;
    Button button;

    @Override
            protected void onCreate(Bundle savedInstanceState)
            {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_movie);

                movie = (Movie) getIntent().getSerializableExtra("movie");
                genres = (TextView) findViewById(R.id.text_genres);
                movietitle = (TextView) findViewById(R.id.movie_title);
                moviestart = (TextView) findViewById(R.id.start_time);
                originaltitle = (TextView) findViewById(R.id.original_title);
                length = (TextView) findViewById(R.id.length);
                theatrename = (TextView) findViewById(R.id.theatres);
                agerating = (TextView) findViewById(R.id.age_rating);
                language = (TextView) findViewById(R.id.language);
                button = (Button) findViewById(R.id.add_rating);
                presentationmethod = (TextView) findViewById(R.id.presentation_method);


            theatrename.setText(movie.getTheatre());
            presentationmethod.setText(" " + movie.getPresentationMethod() + " ");
            length.setText("Kesto: " + String.valueOf(movie.getRuntime()) + " min");
            movietitle.setText(movie.getTitle());
            originaltitle.setText(movie.getOrigTitle());
            genres.setText(movie.getGenre());
            language.setText("Kieli: " + movie.getLanguage());
            moviestart.setText("Alkamisaika: " + movie.getStartOfMovie());
            agerating.setText("Ikäraja: " + movie.getAgerating());
    }

    public void loadAddRating(View view)
    {
        Intent intent;
        Context context = MovieActivity.this;
        if (Files.exists(Paths.get(context.getFilesDir().toString() + "/" + movie.getEventId() + ".json")))
        {
            intent = new Intent(MovieActivity.this, ReadCommentActivity.class);
        }
        else
        {
            intent = new Intent(MovieActivity.this, GiveStarsActivity.class);
        }
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}
