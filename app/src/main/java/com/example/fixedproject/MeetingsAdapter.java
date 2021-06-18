package com.example.fixedproject;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MeetingsAdapter extends ArrayAdapter<Meetings> {
    private Context ctx;
    private int meetingsResourceId;
    private ArrayList<Meetings> data;


    public MeetingsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Meetings> objects) {
        super(context, resource, objects);
        this.ctx = context;
        this.data = objects;
        this.meetingsResourceId = resource;
    }
    public int getCount(){
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater li=(LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = convertView;
        if (v == null) {
            v = li.inflate(R.layout.meetings, parent, false);
        }
        SQLiteHelper sqLiteHelper = new SQLiteHelper(this.ctx);
        Meetings m = this.data.get(position);
        SQLiteOpenHelper sql = new SQLiteHelper(this.ctx);

        TextView title_tv = v.findViewById(R.id.titleId);
        TextView start_tv = v.findViewById(R.id.startTime);
        TextView end_tv = v.findViewById(R.id.endTime);
        TextView type_tv = v.findViewById(R.id.type_TV);

        title_tv.setText(m.getTitle());
        start_tv.setText("" + m.getSday() + " " + m.getSmonth() + " " + m.getSyear() + '\'' + " " + m.getShour() + " " + m.getSminute());
        end_tv.setText("" + m.getEday() + " " + m.getEmonth() + " " + m.getEyear() + '\'' + " " + m.getEhour() + " " + m.getEminute());
        type_tv.setText(sqLiteHelper.SelectType(m.getTypeid()));

        return v;
    }
}
