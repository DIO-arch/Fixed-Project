package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText name,User,Pass;
    Button Reg;
    Dal db;
    Users users = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name =  findViewById(R.id.RegName);
        User = findViewById(R.id.RegUser);
        Pass = findViewById(R.id.RegPassword);
        Reg = findViewById(R.id.ToReg);
        db = new Dal(this);


        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                users.setName(name.getText().toString());
                users.setUsername(User.getText().toString());
                users.setPassword(Pass.getText().toString());

                Intent i = new Intent(getApplicationContext(), Login.class);

                if (users.getName().equals("")) {
                    Toast.makeText(Register.this, "Name is Blank", Toast.LENGTH_LONG).show();
                } else if (users.getUsername().equals("")) {
                    Toast.makeText(Register.this, "Username is Blank", Toast.LENGTH_LONG).show();
                } else if (users.getPassword().equals("")) {
                    Toast.makeText(Register.this, "Password is Blank", Toast.LENGTH_LONG).show();
                } else {
                    //Authenticaion
                    Boolean checkuser = db.checkUsernames(users.getUsername());
                    if(checkuser == true) Toast.makeText(Register.this, "Username already exists", Toast.LENGTH_LONG).show();
                    else {
                        Boolean checkInsert = db.insertData(users.getName(), users.getUsername(), users.getPassword());
                        if(checkInsert == true) {
                            Toast.makeText(Register.this, "Successfully Registered", Toast.LENGTH_LONG).show();
                            i = new Intent(getApplicationContext(), Login.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(Register.this, "Failed to Register", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public void onClick(View view) {
        Intent i = new Intent();
        if(view.getId()==R.id.BypassToLogin)
            i = new Intent(this, Login.class);
        else if (view.getId()==R.id.RegToStart)
            i = new Intent(this, MainActivity.class);
        startActivity(i);




    }
}