package com.example.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    MovieTheatres movieTheatres;
    Spinner theatreSpinner;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        theatreSpinner = findViewById(R.id.theatre_list_spinner);
        button = findViewById(R.id.select_theatre_button);
        movieTheatres = MovieTheatres.getInstance();
        movieTheatres.fetchTheatres();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                movieTheatres.getTheatreNames());
        theatreSpinner.setAdapter(arrayAdapter);
    }

    public void loadImdbView()
    {
        Intent intent = new Intent(MainActivity.this, ImdbSearchActivity.class);
        startActivity(intent);
    }

    public void loadImdbView(View view)
    {
        Intent intent = new Intent(MainActivity.this, ImdbSearchActivity.class);
        startActivity(intent);
    }

    public void loadRatingsView()
    {
        Intent intent = new Intent(MainActivity.this, SeeRatingsActivity.class);
        startActivity(intent);
    }

    public void loadRatingsView(View view)
    {
        Intent intent = new Intent(MainActivity.this, SeeRatingsActivity.class);
        startActivity(intent);
    }

    public void loadTheatreActivity()
    {
        Intent intent = new Intent(MainActivity.this, TheatreActivity.class);
        intent.putExtra("object", movieTheatres.getTheatre(theatreSpinner.getSelectedItemPosition()));
        startActivity(intent);
    }

    public void loadTheatreActivity(View view)
    {
        Intent intent = new Intent(MainActivity.this, TheatreActivity.class);
        intent.putExtra("object", movieTheatres.getTheatre(theatreSpinner.getSelectedItemPosition()));
        startActivity(intent);
    }
}