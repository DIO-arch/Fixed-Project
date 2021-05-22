package com.example.fixedproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Dal extends SQLiteOpenHelper { //for users

    public static final String dbname = "time_management.db";

    public Dal(Context context) {
        super(context, "time_management.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(_id INTEGER primary key, name Text, username Text, password Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists users");
    }

    public Boolean insertData(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", user.getName());
        contentValues.put("username", user.getUsername());
        contentValues.put("password", user.getPassword());
        long result = db.insert("users", null, contentValues);
        if (result == -1) return false;
        return true;
    }

    public Boolean checkUsernames(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) return true;
        return false;
    } //can be modified to a password and name as well just add them in the obvious places

    public Boolean checkUps(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ? and password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) return true;
        return false;
    }
    public String getName(int _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select name From users where _id = ?", null);
        String temp = cursor.getString(cursor.getColumnIndex("name"));
        return temp;
    }
    public String getUserName(int _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select username From users where _id = ?", null);
        String temp = cursor.getString(cursor.getColumnIndex("username"));
        return temp;
    }
    public String getPassword(int _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select password From users where _id = ?", null);
        String temp = cursor.getString(cursor.getColumnIndex("password"));
        return temp;
    }
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    public void deleteUser(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        //DBHelper db2 = new DBHelper(this);
        //db2.deleteAllUserMeetings(_id);
        db.rawQuery("Delete From users where _id = ?", null);
    }
    public void updateUser(long _id, Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
    }
    public void updateName(long _id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("Update name From users where _id = ? Set name = ?", null);
    }
    public void updateUserName(long _id, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
    }
    public void updatePassword(long _id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
    }
}
