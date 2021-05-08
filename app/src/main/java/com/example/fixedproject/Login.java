package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText user,pass;
    Button login;
    Dal db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.edName);
        pass = findViewById(R.id.edPassword);

        login = findViewById(R.id.Login);
        db = new Dal(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usr,pas;

                usr = user.getText().toString();
                pas = pass.getText().toString();

                if (usr.equals("")){
                    Toast.makeText(Login.this,"Username is Blank",Toast.LENGTH_LONG).show(); }
                else if (pas.equals("")) {
                    Toast.makeText(Login.this, "Password is Blank",Toast.LENGTH_LONG).show(); }
                else{
                    //Authenticaion
                    Boolean checkUp = db.checkUps(usr, pas);
                    if(checkUp == true) {
                        Toast.makeText(Login.this, "Successful Login", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(getApplicationContext(), ClocksPage.class);
                        startActivity(i);
                    } else Toast.makeText(Login.this, "Failed to Login", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void onClick(View view) {
        Intent i = new Intent();
        if(view.getId()==R.id.NeedReg)
            i = new Intent(this, Register.class);
        else if (view.getId()==R.id.LoginToStart)
            i = new Intent(this, MainActivity.class);
        else if(view.getId()==R.id.BypassToClocks)
            i = new Intent(this, ClocksPage.class);
        startActivity(i);
    }
}