package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MeetingsList extends AppCompatActivity {

    ListView listView1;
    ArrayList<Meetings> aryMeetings=new ArrayList<Meetings>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings_list);

        listView1=findViewById(R.id.meetings_list);
        getMeetingsData();

        MeetingsAdapter ma=new MeetingsAdapter(this, R.layout.meetings, aryMeetings);
        listView1.setAdapter(ma);
    }
    public void getMeetingsData(){
        //aryMeetings.add(new Meetings("wedding",35,13,2021,5,7,59,16,2021,12,4));
        //aryMeetings.add(new Meetings("morning breakfast",25,8,2001,12,12,13,13,2222,11,24));
    }

}