package com.example.fixedproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;
//for meetings
public class DBHelper extends SQLiteOpenHelper {

    public static final String dbname = "time_management.db";

    public DBHelper(Context context) {
        super(context, "time_management.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table meetings(_id INTEGER primary key, title Text, smin INTEGER, shour INTEGER, syear INTEGER, smonth INTEGER, sday INTEGER, emin INTEGER, ehour INTEGER, eyear INTEGER, emonth INTEGER, eday INTEGER, user_id INTEGER, type_id INTEGER)");
    } // user_id and type_id needs to be forgein key

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists meetings");
    }

    public void insertData(String title, int smin, int shour, int syear, int smonth, int sday, int emin, int ehour, int eyear, int emonth,int eday, long user_id, long type_id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql_insert = "INSERT INTO EXP (title, smin, shour, syear, smonth, sday, emin, ehour, eyear, emonth, eday, user_id, type_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_insert);

        statement.bindString(1, title);
        statement.bindString(2, String.valueOf(smin));
        statement.bindString(3, String.valueOf(shour));
        statement.bindString(4, String.valueOf(syear));
        statement.bindString(5, String.valueOf(smonth));
        statement.bindString(6, String.valueOf(sday));
        statement.bindString(7, String.valueOf(emin));
        statement.bindString(8, String.valueOf(ehour));
        statement.bindString(9, String.valueOf(eyear));
        statement.bindString(10, String.valueOf(emonth));
        statement.bindString(11, String.valueOf(eday));
        statement.bindString(12, String.valueOf(user_id));
        statement.bindString(13, String.valueOf(type_id));

        statement.execute();
    }
    public int GetSDay(int _id)
    {
        return -1;
    }

    public void deleteMeeting(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("Delete From meetings where _id = ?", null);
    }
    public void deleteAllUserMeetings(long user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("Delete From meetings where user_id = ?", null);
    }
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
