package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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