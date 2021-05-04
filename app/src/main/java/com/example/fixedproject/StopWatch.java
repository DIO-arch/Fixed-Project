package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class StopWatch extends AppCompatActivity {
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_watch);

        chronometer = findViewById(R.id.stopper);
        if (chronometer != null) {
            chronometer.setFormat("Time: %s");
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                @Override
                public void onChronometerTick(Chronometer chronometer) {
                    if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000) {
                        chronometer.setBase(SystemClock.elapsedRealtime());
                        Toast.makeText(StopWatch.this, "Bing!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
    public void startChronometer(View v) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void pauseChronometer(View v) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
    public void resetChronometer(View v) {
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset = 0;
    }
    public void onClick(View view) {
        Intent i = new Intent();
        if(view.getId()==R.id.ToClocksPage)
            i = new Intent(this, ClocksPage.class);
        else if(view.getId()==R.id.ToTimer)
            i = new Intent(this, Timer.class);
        startActivity(i);
    }
}
