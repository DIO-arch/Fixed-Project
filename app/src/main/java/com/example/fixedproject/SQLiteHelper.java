package com.example.fixedproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
//for type
public class SQLiteHelper extends SQLiteAssetHelper {
    public SQLiteHelper(Context context) {
        super(context, "time_management.db", null, 1);
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
