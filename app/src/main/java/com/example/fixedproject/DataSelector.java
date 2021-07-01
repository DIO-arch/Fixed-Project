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

public class DataSelector extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "SelectSchedule";
    Intent i;
    Button sdateButton, stimeButton, edatebutton, etimebutton;
    TextView sdateTextView, stimeTextView, edateTextView, etimeTextView;
    Type type = new Type();
    SQLiteHelper sqLiteHelper;
    DBHelper db;
    long _id;
    EditText title;
    Meetings meetings = new Meetings();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_selector);

        sqLiteHelper = new SQLiteHelper(this);

        i = getIntent();
        _id = i.getExtras().getInt("_id");
        db = new DBHelper(this);
        meetings.setUserid(_id);
        //s = start
        sdateButton = findViewById(R.id.start_date_btn);
        stimeButton = findViewById(R.id.start_time_btn);
        sdateTextView = findViewById(R.id.start_date_TV);
        stimeTextView = findViewById(R.id.start_time_TV);
        //e = end
        edatebutton = findViewById(R.id.end_date_btn);
        etimebutton = findViewById(R.id.end_time_btn);
        edateTextView = findViewById(R.id.end_date_TV);
        etimeTextView = findViewById(R.id.end_time_TV);
        //title
        title = findViewById(R.id.meeting_title);

        Spinner spinner = findViewById(R.id.spinner);
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

    private void handleDatesButtons(View v){
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        int YEAR = calendar.get(java.util.Calendar.YEAR);
        int MONTH = calendar.get(java.util.Calendar.MONTH);
        int DATE = calendar.get(java.util.Calendar.DATE);

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

                if(v.getId() == R.id.start_date_btn) { sdateTextView.setText(dateText);
                meetings.setSyear(year);
                meetings.setSmonth(month);
                meetings.setSday(date);
                } //dateTextView
                else if(v.getId() == R.id.end_date_btn) { edateTextView.setText(dateText);
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
        int HOUR = calendar.get(java.util.Calendar.HOUR);
        int MINUTE = calendar.get(java.util.Calendar.MINUTE);
        boolean is24HourFormat = DateFormat.is24HourFormat(this);


        //Toast.makeText(this, "" +is24HourFormat, Toast.LENGTH_SHORT).show();

        TimePickerDialog timePickerDialog =
                new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                Log.i(TAG, "onTimeSet: " + hour + minute);
                java.util.Calendar calendar1 = java.util.Calendar.getInstance();
                calendar1.set(java.util.Calendar.HOUR, hour);
                calendar1.set(Calendar.MINUTE, minute);
                String dateText = DateFormat.format("h:mm a", calendar1).toString();

                if(v.getId()==R.id.start_time_btn) {stimeTextView.setText(dateText);
                    meetings.setSminute(minute);
                    if (is24HourFormat)
                        hour = hour + 12;
                    meetings.setShour(hour);
                }
                else if(v.getId()==R.id.end_time_btn) {etimeTextView.setText(dateText);
                    meetings.setEminute(minute);
                    if (is24HourFormat)
                        hour = hour + 12;
                    meetings.setEhour(hour);
                }
            }
        }, HOUR, MINUTE, is24HourFormat);
        timePickerDialog.show();
    }
    public void onClick(View view) {
        if(view.getId()==R.id.Todisplay)
            i = new Intent(this, MeetingsList.class);
        else if (view.getId()==R.id.ToClocksPage2)
            i = new Intent(this, ClocksPage.class);
        i.putExtra("_id",getIntent().getExtras().getInt("_id"));
        startActivity(i);
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
                        Toast.makeText(DataSelector.this, types[type],Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.getListView().setBackgroundColor(Color.GRAY);
        dialog.show();
    }
    public void InsertMeeting(View view) {
        meetings.setTitle(title.getText().toString());
        if (CheckNull(view)) {
            if(db.insertData(meetings.getTitle(),
                    meetings.getSminute(),meetings.getShour(),
                    meetings.getSyear(),meetings.getSmonth(),meetings.getSday(),
                    meetings.getEminute(),meetings.getEhour(),
                    meetings.getEyear(),meetings.getEmonth(),meetings.getEday(),
                    meetings.getUserid(), meetings.getTypeid())) //(int)
                Toast.makeText(DataSelector.this, "MEETING SAVED", Toast.LENGTH_LONG).show();
            else {
                Toast.makeText(DataSelector.this, "FAILED TO SAVE", Toast.LENGTH_LONG).show();
                Toast.makeText(DataSelector.this, "FAILED Checks somethings might be missing", Toast.LENGTH_LONG).show();
            }
        }
    }
    public Boolean CheckNull(View view) {
       if(meetings.getTitle().equals("")) {
           Toast.makeText(DataSelector.this, "Title missing", Toast.LENGTH_LONG).show();
           return false; }
       if(!(meetings.getShour()>-1) && !(meetings.getSminute()>-1)){
           Toast.makeText(DataSelector.this, "Meeting start time missing", Toast.LENGTH_LONG).show();
           return false; }
       if(!(meetings.getSyear()>-1) && !(meetings.getSmonth()>0) && !(meetings.getSday()>0)){
           Toast.makeText(DataSelector.this, "Meeting start date missing", Toast.LENGTH_LONG).show();
           return false; }
       if(!(meetings.getEhour()>-1) && !(meetings.getEminute()>-1)){
           Toast.makeText(DataSelector.this, "Meeting end time missing", Toast.LENGTH_LONG).show();
           return false; }
       if(!(meetings.getEyear()>-1) && !(meetings.getEmonth()>0) && !(meetings.getEday()>0)){
           Toast.makeText(DataSelector.this, "Meeting end date missing", Toast.LENGTH_LONG).show();
           return false; }
        if(!(meetings.getTypeid()>0)) {
            Toast.makeText(DataSelector.this, "Type Fail", Toast.LENGTH_LONG).show();
            return false; }
        if(!(meetings.getUserid()>0)) {
            Toast.makeText(DataSelector.this, "User ID Fail", Toast.LENGTH_LONG).show();
            return false; }
        return true;
    }
    public void Reset(View view){
        title.setText("");
        sdateTextView.setText("");
        stimeTextView.setText("");
        edateTextView.setText("");
        etimeTextView.setText("");

        meetings.setTitle("");
        //start
        meetings.setShour(-1);
        meetings.setSminute(-1);
        meetings.setSyear(-1);
        meetings.setSmonth(-1);
        meetings.setSday(-1);
        //end
        meetings.setEhour(-1);
        meetings.setEminute(-1);
        meetings.setEyear(-1);
        meetings.setEmonth(-1);
        meetings.setEday(-1);
        //ids
        meetings.setTypeid(-1);
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

    public void IdCheck(View view){
        Toast.makeText(DataSelector.this, "user's id: " + _id, Toast.LENGTH_LONG).show();
    }
}