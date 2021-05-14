package com.example.fixedproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
//for meetings
public class DBHelper extends SQLiteAssetHelper {

    public DBHelper(Context context) {
        super(context, "time_management.db", null, 1);
    }

    public void insertData(int smin, int shour, int syear, int smonth, int sday, int emin, int ehour, int eyear, int emonth,int eday, long user_id, long type_id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_insert = "INSERT INTO EXP (, ) values(?,?)";
        SQLiteStatement statement = db.compileStatement(sql_insert);

        statement.bindString(1, );
        statement.bindString(2, );
        statement.execute();
    }
    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
