package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Update_Meeting extends AppCompatActivity {
    Intent i = getIntent();
    Button sdateButton, stimeButton, edatebutton, etimebutton;
    TextView sdateTextView, stimeTextView, edateTextView, etimeTextView;
    Type type;
    Meetings meetings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_meeting);

        sdateButton = findViewById(R.id.start_date_btn);
        stimeButton = findViewById(R.id.start_time_btn);
        sdateTextView = findViewById(R.id.start_date_TV);
        stimeTextView = findViewById(R.id.start_time_TV);
        edatebutton = findViewById(R.id.end_date_btn);
        etimebutton = findViewById(R.id.end_time_btn);
        edateTextView = findViewById(R.id.end_date_TV);
        etimeTextView = findViewById(R.id.end_time_TV);
    }
}