package com.example.fixedproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
//for type
public class SQLiteHelper extends SQLiteAssetHelper {
    Context context = null;
    public SQLiteHelper(Context context) {
        super(context, "time_management.db", null, 1);
        this.context = context;
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    public String SelectType(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select type_name From type where _id =" +_id,null);
        return cursor.getString(cursor.getColumnIndex("type_name"));
    }
    public int GetType_id(String type_name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select _id From type where type_name =" +type_name,null);
        if (cursor.getCount() > 0)
            return cursor.getInt(cursor.getColumnIndex("_id"));
        return -1;
    }
}
