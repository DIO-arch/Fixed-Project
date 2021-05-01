package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class DataSelector extends AppCompatActivity {
    private static final String TAG = "SelectSchedule";

    Button sdateButton, stimeButton, edatebutton, etimebutton;
    TextView sdateTextView, stimeTextView, edateTextView, etimeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_selector);

        sdateButton = findViewById(R.id.start_date_btn);
        stimeButton = findViewById(R.id.start_time_btn);
        sdateTextView = findViewById(R.id.start_date_TV);
        stimeTextView = findViewById(R.id.start_time_TV);
        edatebutton = findViewById(R.id.end_date_btn);
        etimebutton = findViewById(R.id.end_time_btn);
        edateTextView = findViewById(R.id.end_date_TV);
        etimeTextView = findViewById(R.id.end_time_TV);


        sdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDatesButtons(view);
            }
        });

        edatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDatesButtons(view);
            }
        });

        stimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButtons(view);
            }
        });
        
        etimebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { handleTimeButtons(view); }
        });
    }

    private void handleDatesButtons(View v){
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int YEAR = calendar.get(java.util.Calendar.YEAR);
        int MONTH = calendar.get(java.util.Calendar.MONTH);
        int DATE = calendar.get(java.util.Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                calendar1.set(java.util.Calendar.YEAR, year);
                calendar1.set(java.util.Calendar.MONTH, month);
                calendar1.set(java.util.Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                if(v.getId() == R.id.start_date_btn) sdateTextView.setText(dateText); //dateTextView
                else if(v.getId() == R.id.end_date_btn) edateTextView.setText(dateText);
                //else throw
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }

    private void handleTimeButtons(View v) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int HOUR = calendar.get(java.util.Calendar.HOUR);
        int MINUTE = calendar.get(java.util.Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i(TAG, "onTimeSet: " + hour + minute);
                java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                calendar1.set(java.util.Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("h:mm a", calendar1).toString();

                if(v.getId()==R.id.start_time_btn) stimeTextView.setText(dateText);
                else if(v.getId()==R.id.end_time_btn) etimeTextView.setText(dateText);
            }
        }, HOUR, MINUTE, is24HourFormat);
        timePickerDialog.show();
    }
}