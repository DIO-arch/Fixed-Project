package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MeetingsList extends AppCompatActivity {
    Intent i = getIntent();
    ListView meetings_listview; //renamed
    ArrayList<Meetings> aryMeetings = new ArrayList<Meetings>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings_list);

        meetings_listview = findViewById(R.id.meetings_list);
        DBHelper db = new DBHelper(this);
        aryMeetings = db.getAllMeetings();

        Toast.makeText(MeetingsList.this,""+aryMeetings.size(),Toast.LENGTH_LONG).show();

        MeetingsAdapter ma = new MeetingsAdapter(this, R.layout.meetings, aryMeetings);
        meetings_listview.setAdapter(ma);
    }
    public void onClick(View view) {
        if(view.getId()==R.id.to_clocks2)
            i = new Intent(this, ClocksPage.class);
        else if(view.getId()==R.id.new_meeting)
            i = new Intent(this, DataSelector.class);
        startActivity(i);
    }
    // title
    // sminute, shour, syear, smonth, sday
    // eminute, ehour, eyear, emonth, eday
    // id, userid, typeid
    public void getMeetingsData(){
        aryMeetings.add(new Meetings("wedding",35,13,2021,5,7,59,16,2021,12,4,36,1,3));
        aryMeetings.add(new Meetings("morning_breakfast",25,8,2001,12,12,13,13,2222,11,24,37,1,2));
    }
}