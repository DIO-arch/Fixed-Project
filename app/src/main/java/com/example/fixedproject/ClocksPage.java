package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AnalogClock;
import android.widget.DigitalClock;

public class ClocksPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clocks_page);

        AnalogClock ac = (AnalogClock) findViewById(R.id.analog);
        DigitalClock dc = (DigitalClock) findViewById(R.id.digital);
    }
}