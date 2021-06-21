package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfo extends Activity {
    Intent i;
    Dal dal;
    long _id;
    TextView infoname, infopass, infouser;
    Button save, delete, reset;
    Users users = new Users();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        dal = new Dal(this);
        i = getIntent();
        _id = i.getExtras().getInt("_id");
        infoname = findViewById(R.id.InfoName);
        infouser = findViewById(R.id.InfoUser);
        infopass = findViewById(R.id.InfoPassword);

        save = findViewById(R.id.SaveData);
        delete = findViewById(R.id.deleteData);
        reset = findViewById(R.id.ResetData);

        infoname.setText(dal.getName(_id));
        infouser.setText(dal.getUserName(_id));
        infopass.setText(dal.getPassword(_id));

        users.setId(_id);
        users.setName(infoname.getText().toString());
        users.setUsername(infouser.getText().toString());
        users.setPassword(infopass.getText().toString());
    }
    public void onClick(View view) {
        if(view.getId()==R.id.UserToClocks)
            i = new Intent(this, ClocksPage.class);
        else if(view.getId()==R.id.to_Meetings_Display)
            i = new Intent(this, MeetingsList.class);
        i.putExtra("_id",getIntent().getExtras().getInt("_id"));
        startActivity(i);
    }
    public void DeleteUser(View view){
        dal.deleteUser(_id);
        i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
    public void SaveUser(View view){
        users.setName(infoname.getText().toString());
        users.setUsername(infouser.getText().toString());
        users.setPassword(infopass.getText().toString());
        Boolean bool = dal.updateUser(_id, users);
        if(bool) Toast.makeText(UserInfo.this, "Saved", Toast.LENGTH_LONG).show();
        else Toast.makeText(UserInfo.this, "Unsuccessful Save",Toast.LENGTH_LONG).show();
    }
    public void Reset(View view){
        infoname.setText(dal.getName(_id));
        infouser.setText(dal.getUserName(_id));
        infopass.setText(dal.getPassword(_id));

        users.setId(_id);
        users.setName(infoname.getText().toString());
        users.setUsername(infouser.getText().toString());
        users.setPassword(infopass.getText().toString());
    }
}