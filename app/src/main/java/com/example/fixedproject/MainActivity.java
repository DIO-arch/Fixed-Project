package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.ref.WeakReference;

public class MainActivity extends AppCompatActivity {
    Button test, print;
    private static final String Tag = "MainActivity";

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test = findViewById(R.id.async_task_test);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LongRunningTask().execute();
            }
        });
    }
    private class LongRunningTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            Log.d(Tag, "PRE PRE PRE MESSEAGE");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(Tag, "doing: TESTING LET'S SEE IF IT WORKS PLEASE WORK WRITING THIS LONG SO I FIND IT");
            //Toast.makeText(MainActivity.this, "Failed to Login", Toast.LENGTH_LONG).show();
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            Log.d(Tag, "POST EXECURE CLARITY"); 
            super.onPostExecute(unused);
        }
    }
    public void onClick(View view) {
        Intent i = new Intent();
        if(view.getId()==R.id.ToLogin)
            i = new Intent(this, Login.class);
        else if (view.getId()==R.id.ToRegister)
            i = new Intent(this, Register.class);
        startActivity(i);
    }
}