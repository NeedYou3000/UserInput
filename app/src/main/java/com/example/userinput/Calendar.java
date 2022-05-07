package com.example.userinput;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

public class Calendar extends AppCompatActivity {
    CalendarView calendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            Intent returnIntent = new Intent();
            returnIntent.putExtra("Date", "" + dayOfMonth + "/" + (month + 1) + "/" + year);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();
        });
    }
}