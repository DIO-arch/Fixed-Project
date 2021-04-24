package com.example.fixedproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MeetingsAdapter extends ArrayAdapter<Meetings> {
    private Context ctx;
    private int meetingsResourceId;
    private List<Meetings> data;


    public MeetingsAdapter(@NonNull Context context, int resource, @NonNull List<Meetings> objects) {
        super(context, resource, objects);
        this.ctx=(MeetingsList) context;
        this.data=objects;
        this.meetingsResourceId=resource;
    }
    public int getCount(){
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater li=(LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=li.inflate(this.meetingsResourceId,null);

        Meetings m=this.data.get(position);
        TextView tv1=v.findViewById(R.id.titleId);
        tv1.setText(m.getTitle());
        TextView tv2=v.findViewById(R.id.startTime);
        tv2.setText(m.getSday() + m.getSmonth() + m.getSyear() + '\'' + m.getShour() + m.getSminute());
        TextView tv3=v.findViewById(R.id.startTime);
        tv3.setText(m.getEday() + m.getEmonth() + m.getEyear() + '\'' + m.getEhour() + m.getEminute());
        return v;
    }
}
