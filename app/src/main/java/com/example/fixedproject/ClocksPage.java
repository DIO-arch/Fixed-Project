package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
    public void onClick(View view) {
        Intent i = new Intent();
        if(view.getId()==R.id.ToUser) i = new Intent(this, UserInfo.class);
        else if (view.getId()==R.id.ToMeetingsList)
            i = new Intent(this, MeetingsList.class);
        else if (view.getId()==R.id.ToStopWatch)
            i = new Intent(this, StopWatch.class);
        else if (view.getId()==R.id.ToTimer)
            i = new Intent(this, Timer.class);
        startActivity(i);
    }
}