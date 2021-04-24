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

    Button dateButton, timeButton;
    TextView dateTextView, timeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_selector);

        dateButton = findViewById(R.id.dateButton);
        timeButton = findViewById(R.id.timeButton);
        dateTextView = findViewById(R.id.dateTextView);
        timeTextView = findViewById(R.id.timeTextView);

        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDateButton();
            }
        });
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButton();
            }
        });
    }
    private void handleDateButton() {
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

                dateTextView.setText(dateText);
            }
        }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }

    private void handleTimeButton() {
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
                timeTextView.setText(dateText);
            }
        }, HOUR, MINUTE, is24HourFormat);
        timePickerDialog.show();
    }
}