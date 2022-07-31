package com.example.movieapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ImdbSearchActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_imdb);

        EditText movieTitle = findViewById(R.id.imdb_title);
        Button imdbButton = findViewById(R.id.imdb_search_button);
        ListView listView = findViewById(R.id.imdb_list);

        imdbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ArrayList<String> imdbArray = null;
                if (!movieTitle.getText().toString().isEmpty())
                {
                    String titleText = movieTitle.getText().toString();
                    imdbArray = SearchIMDB.readMovieJson(titleText);
                }
                ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, imdbArray);
                listView.setAdapter(arrayAdapter2);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
            {
                System.out.println(position);
                String string = listView.getItemAtPosition(position).toString();
                System.out.println(string);
                String delims = "[\n: ]+";
                String[] tokens = string.split(delims);
                int wholeLength = tokens.length;
                System.out.println(tokens[wholeLength-1]);
                String movieId = (tokens[wholeLength-1]);
                String movieRating = SearchIMDB.readMovieDetailsJson(movieId);
                Snackbar snackbar = Snackbar.make(view, "Movie Rating : " + movieRating, Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });
    }
}
