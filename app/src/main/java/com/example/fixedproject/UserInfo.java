package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class UserInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
    }
    public void onClick(View view) {
        Intent i = new Intent();
        if(view.getId()==R.id.UserToClocks)
            i = new Intent(this, ClocksPage.class);
        startActivity(i);
    }
}