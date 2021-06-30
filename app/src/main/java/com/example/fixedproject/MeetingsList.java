package com.example.fixedproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class MeetingsList extends AppCompatActivity {
    Intent i;
    ListView meetings_listview;
    ArrayList<Meetings> aryMeetings = new ArrayList<Meetings>();
    long _id;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetings_list);

        meetings_listview = findViewById(R.id.meetings_list);
        db = new DBHelper(this);
        i = getIntent();
        _id = i.getExtras().getInt("_id");
        if(db.checkIfUserHasMeetings(_id)) {
            aryMeetings = db.getAllMeetingsMatchingid(_id); //getAllMeetings();

            Toast.makeText(MeetingsList.this, "" + aryMeetings.size(), Toast.LENGTH_LONG).show();

            MeetingsAdapter ma = new MeetingsAdapter(this, R.layout.meetings, aryMeetings);
            meetings_listview.setAdapter(ma);

            meetings_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(MeetingsList.this);
                    adb.setTitle("Choose a command to apply on " + 0 + " meeting"); //might change 0 to meeting title
                    adb.setMessage("Do you want to delete or update this meeting");

                    int removePos = position; //maybe change pos to final

                    adb.setPositiveButton("Update Meeting", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    db.deleteMeeting(position);
                                }
                            });
                    adb.setNegativeButton("Delete Meeting", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Boolean b = db.deleteMeeting(position);
                            //aryMeetings.notifyAll();
                            if (b)
                                Toast.makeText(MeetingsList.this, "Meeting deleted", Toast.LENGTH_LONG).show();
                            else Toast.makeText(MeetingsList.this, "Failed to delete", Toast.LENGTH_LONG).show();
                        }
                    });
                    adb.show();
                }
            });
        } else {
            Toast.makeText(MeetingsList.this, "This user has no meetings", Toast.LENGTH_LONG).show();
        }
    }
    public void onClick(View view) {
        if(view.getId()==R.id.to_clocks2)
            i = new Intent(this, ClocksPage.class);
        else if(view.getId()==R.id.new_meeting)
            i = new Intent(this, DataSelector.class);
        i.putExtra("_id",getIntent().getExtras().getInt("_id"));
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
    public void DeleteMeetings(View view){
        if(db.checkIfUserHasMeetings(_id))
            if (db.deleteAllUserMeetings(_id)) {
                i = new Intent(this, MeetingsList.class);
                i.putExtra("_id",getIntent().getExtras().getInt("_id"));
                startActivity(i);
            }
        else
            Toast.makeText(MeetingsList.this, "Sorry but the app can't delete meetings that don't exist", Toast.LENGTH_LONG).show();
    }
}