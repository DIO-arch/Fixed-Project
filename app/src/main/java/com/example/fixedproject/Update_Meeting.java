package com.example.fixedproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Update_Meeting extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Intent i;
    Button sdateButton, stimeButton, edatebutton, etimebutton;
    TextView sdateTextView, stimeTextView, edateTextView, etimeTextView;
    Type type = new Type();
    SQLiteHelper sqLiteHelper;
    DBHelper db;
    Meetings meetings = new Meetings();
    long user_id, meeting_id;
    EditText title;
    private static final String TAG = "SelectSchedule";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_meeting);

        i = getIntent();
        sqLiteHelper = new SQLiteHelper(this);
        db = new DBHelper(this);
        //ids
        meeting_id = i.getExtras().getInt("meeting_id");
        user_id = i.getExtras().getInt("_id");
        //starts
        sdateButton = findViewById(R.id.start_date_btnU);
        stimeButton = findViewById(R.id.start_time_btnU);
        sdateTextView = findViewById(R.id.start_date_TVU);
        stimeTextView = findViewById(R.id.start_time_TVU);
        //ends
        edatebutton = findViewById(R.id.end_date_btnU);
        etimebutton = findViewById(R.id.end_time_btnU);
        edateTextView = findViewById(R.id.end_date_TVU);
        etimeTextView = findViewById(R.id.end_time_TVU);
        //title
        title = findViewById(R.id.meeting_titleU);

        //meetings

        //title
        meetings.setTitle(meetings.getTitle());
        //ids
        meetings.setId(meeting_id);
        meetings.setUserid(user_id);
        meetings.setTypeid(db.GetTypeId(meeting_id));
        //starts
        meetings.setSminute(db.GetSMin(meeting_id));
        meetings.setShour(db.GetSHour(meeting_id));

        meetings.setSyear(db.GetSYear(meeting_id));
        meetings.setSmonth(db.GetSMonth(meeting_id));
        meetings.setSday(db.GetSDay(meeting_id));
        //ends
        meetings.setEminute(db.GetEMin(meeting_id));
        meetings.setEhour(db.GetEHour(meeting_id));

        meetings.setEyear(db.GetEYear(meeting_id));
        meetings.setEmonth(db.GetEMonth(meeting_id));
        meetings.setEday(db.GetEDay(meeting_id));

        //starts
        //sdateButton.setText(meetings.get);

        Spinner spinner = findViewById(R.id.spinnerU);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        sdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDatesButtons(view);
            }
        });

        edatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDatesButtons(view);
            }
        });

        stimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTimeButtons(view);
            }
        });

        etimebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { handleTimeButtons(view); }
        });
    }
    public void Reset(View view){
        title.setText(db.GetTitle(meeting_id));
        sdateTextView.setText("");
        stimeTextView.setText("");
        edateTextView.setText("");
        etimeTextView.setText("");

        meetings.setTitle(db.GetTitle(meeting_id));
        //start
        meetings.setShour(db.GetSHour(meeting_id));
        meetings.setSminute(db.GetSMin(meeting_id));
        meetings.setSyear(db.GetSYear(meeting_id));
        meetings.setSmonth(db.GetSMonth(meeting_id));
        meetings.setSday(db.GetSDay(meeting_id));
        //end
        meetings.setEhour(db.GetEHour(meeting_id));
        meetings.setEminute(db.GetEMin(meeting_id));
        meetings.setEyear(db.GetEYear(meeting_id));
        meetings.setEmonth(db.GetEMonth(meeting_id));
        meetings.setEday(db.GetEDay(meeting_id));
        //ids
        meetings.setTypeid(db.GetTypeId(meeting_id));
    }
    public void btnClick(View view) {
        final String[] types = {"leisure", "work", "private", "other"};
        ListAdapter aryListAdapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_1,types);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("title");
        builder.setAdapter(aryListAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int type) {
                Toast.makeText(Update_Meeting.this, types[type],Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.getListView().setBackgroundColor(Color.GRAY);
        dialog.show();
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        long type_id = sqLiteHelper.GetType_id(text);
        meetings.setTypeid(type_id);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    private void handleDatesButtons(View v){
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int YEAR = calendar.get(java.util.Calendar.YEAR);
        int MONTH = calendar.get(java.util.Calendar.MONTH);
        int DATE = calendar.get(java.util.Calendar.DATE);

        if(v.getId() == R.id.start_date_btnU){
            YEAR = db.GetSYear(meeting_id);
            MONTH = db.GetSMonth(meeting_id);
            DATE = db.GetSDay(meeting_id);
        }
        if(v.getId() == R.id.end_date_btnU){
            YEAR = db.GetEYear(meeting_id);
            MONTH = db.GetEMonth(meeting_id);
            DATE = db.GetEDay(meeting_id);
        }
        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {

                        java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                        calendar1.set(java.util.Calendar.YEAR, year);
                        calendar1.set(java.util.Calendar.MONTH, month);
                        calendar1.set(java.util.Calendar.DATE, date);
                        String dateText =
                                DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString();

                        if(v.getId() == R.id.start_date_btnU) { sdateTextView.setText(dateText);
                            meetings.setSyear(year);
                            meetings.setSmonth(month);
                            meetings.setSday(date);
                        } //dateTextView
                        else if(v.getId() == R.id.end_date_btnU) { edateTextView.setText(dateText);
                            meetings.setEyear(year);
                            meetings.setEmonth(month);
                            meetings.setEday(date);
                        }
                        //else throw
                    }
                }, YEAR, MONTH, DATE);

        datePickerDialog.show();
    }

    private void handleTimeButtons(View v) {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        boolean is24HourFormat = DateFormat.is24HourFormat(this);
        int HOUR = calendar.get(java.util.Calendar.HOUR);
        int MINUTE = calendar.get(java.util.Calendar.MINUTE);

        if(v.getId()==R.id.start_time_btnU){
            HOUR = db.GetSHour(meeting_id);
            MINUTE = db.GetSMin(meeting_id);
        }
        if(v.getId()==R.id.end_time_btnU){
            HOUR = db.GetEHour(meeting_id);
            MINUTE = db.GetEMin(meeting_id);
        }
        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        Log.i(TAG, "onTimeSet: " + hour + minute);
                        java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                        calendar1.set(java.util.Calendar.HOUR, hour);
                        calendar1.set(Calendar.MINUTE, minute);
                        String dateText = DateFormat.format("h:mm a", calendar1).toString();

                        if(v.getId()==R.id.start_time_btnU) {stimeTextView.setText(dateText);
                            meetings.setSminute(minute);
                            if (is24HourFormat)
                                hour = hour + 12;
                            meetings.setShour(hour);
                        }
                        else if(v.getId()==R.id.end_time_btnU) {etimeTextView.setText(dateText);
                            meetings.setEminute(minute);
                            if (is24HourFormat)
                                hour = hour + 12;
                            meetings.setEhour(hour);
                        }
                    }
                }, HOUR, MINUTE, is24HourFormat);
        timePickerDialog.show();
    }
    public Boolean CheckNull(View view) {
        if(meetings.getTitle().equals("")) {
            Toast.makeText(Update_Meeting.this, "Title missing", Toast.LENGTH_LONG).show();
            return false; }
        if(!(meetings.getShour()>-1) && !(meetings.getSminute()>-1)){
            Toast.makeText(Update_Meeting.this, "Meeting start time missing", Toast.LENGTH_LONG).show();
            return false; }
        if(!(meetings.getSyear()>-1) && !(meetings.getSmonth()>0) && !(meetings.getSday()>0)){
            Toast.makeText(Update_Meeting.this, "Meeting start date missing", Toast.LENGTH_LONG).show();
            return false; }
        if(!(meetings.getEhour()>-1) && !(meetings.getEminute()>-1)){
            Toast.makeText(Update_Meeting.this, "Meeting end time missing", Toast.LENGTH_LONG).show();
            return false; }
        if(!(meetings.getEyear()>-1) && !(meetings.getEmonth()>0) && !(meetings.getEday()>0)){
            Toast.makeText(Update_Meeting.this, "Meeting end date missing", Toast.LENGTH_LONG).show();
            return false; }
        if(!(meetings.getTypeid()>0)) {
            Toast.makeText(Update_Meeting.this, "Type Fail", Toast.LENGTH_LONG).show();
            return false; }
        if(!(meetings.getUserid()>0)) {
            Toast.makeText(Update_Meeting.this, "User ID Fail", Toast.LENGTH_LONG).show();
            return false; }
        return true;
    }
    public void updateMeeting(View view){
        Toast.makeText(Update_Meeting.this, "Canceled Update", Toast.LENGTH_LONG).show();
        i = new Intent(this, MeetingsList.class);
        i.putExtra("_id",getIntent().getExtras().getInt("_id"));
        startActivity(i);
    }
}