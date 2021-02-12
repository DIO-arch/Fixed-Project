package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText name,User,Pass;
    Button Reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name =  findViewById(R.id.RegName);
        User = findViewById(R.id.RegUser);
        Pass = findViewById(R.id.RegPassword);

        Reg = findViewById(R.id.ToReg);


        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String na, un, pa;

                na = name.getText().toString();
                un = User.getText().toString();
                pa = Pass.getText().toString();


                if (na.equals("")) {
                    Toast.makeText(Register.this, "Name is Blank", Toast.LENGTH_LONG).show();
                } else if (un.equals("")) {
                    Toast.makeText(Register.this, "Username is Blank", Toast.LENGTH_LONG).show();
                } else if (pa.equals("")) {
                    Toast.makeText(Register.this, "Password is Blank", Toast.LENGTH_LONG).show();
                } else {
                    //Authenticaion
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