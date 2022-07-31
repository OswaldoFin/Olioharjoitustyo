package com.example.movieapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class TheatreActivity extends AppCompatActivity
{
    Theatre theatre;
    ListView list;
    LocalDate localdate;
    TextView currentdate;
    LocalTime filterstart, filterend;
    Button timepickerstart;
    int selectedindex;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theatre);

        selectedindex = 0;
        theatre = (Theatre) getIntent().getSerializableExtra("object");
        list = findViewById(R.id.list);
        localdate = LocalDate.now();
        currentdate = findViewById(R.id.current_date);
        timepickerstart = findViewById(R.id.start_time_button);

        filterstart = LocalTime.MIN;
        filterend = LocalTime.MAX;
        updateMovieList(localdate, filterstart, filterend);
        updateDate(localdate);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedindex = i;
                movieChoice();
            }
        });
    }

    private void updateDate(LocalDate date)
    {
        String datestring = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
        currentdate.setText(datestring);
    }

    public void nextDay(View view)
    {
        localdate = localdate.plusDays(1);
        updateDate(localdate);
        updateMovieList(localdate, filterstart, filterend);
    }

    public void previousDay(View view)
    {
        localdate = localdate.minusDays(1);
        updateDate(localdate);
        updateMovieList(localdate, filterstart, filterend);
    }

    private void updateMovieList(LocalDate date, LocalTime periodStart, LocalTime periodEnd) {
        theatre.fetchMovies(date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), periodStart, periodEnd);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_list_item_1, theatre.getMovieNames())
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                textView.setTextColor(Color.WHITE);
                return view;
            }
        };
        list.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }

    public void startTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                if (LocalTime.parse(String.format(Locale.getDefault(), "%02d:%02d", hour, minute)).isBefore(filterend))
                {
                    filterstart = LocalTime.parse(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                    timepickerstart.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                    updateMovieList(localdate, filterstart, filterend);
                }
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this , onTimeSetListener, 00, 00, true);
        timePickerDialog.setTitle("Select start time");
        timePickerDialog.show();
    }

    public void movieChoice()
    {
        Intent intent = new Intent(TheatreActivity.this, MovieActivity.class);
        intent.putExtra("movie", theatre.getMovie(selectedindex));
        startActivity(intent);
    }
}
