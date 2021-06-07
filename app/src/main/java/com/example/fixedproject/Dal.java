package com.example.fixedproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class Dal extends SQLiteAssetHelper { //for users

    public static final String dbname = "time_management.db";
    private Context context = null;

    public Dal(Context context) {
        super(context, "time_management.db", null, 1);
        this.context = context;
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
        Cursor cursor = db.rawQuery("Select * from users where username =" +username, new String[]{username});
        if (cursor.getCount() > 0) return true;
        return false;
    } //can be modified to a password and name as well just add them in the obvious places

    public Boolean checkUps(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = '"+username+"'  and password =" +password, new String[]{username, password});
        if (cursor.getCount() > 0) return true;
        return false;
    }
    public int getId(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select _id From users where username = '"+username+"'", null);
        //return cursor.getInt(cursor.getColumnIndex("_id"));
        if (cursor.getCount() > 0)
        return cursor.getInt(0);
        return -1;
    }
    public String getName(long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select name From users where _id ="  +_id, null);
        return cursor.getString(cursor.getColumnIndex("name"));
    }
    public String getUserName(long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select username From users where _id ="+_id, null);
        return cursor.getString(cursor.getColumnIndex("username"));
    }
    public String getPassword(long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select password From users where _id ="+_id, null);
        return cursor.getString(cursor.getColumnIndex("password"));
    }
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    public void deleteUser(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        DBHelper db2 = new DBHelper(this.context);
        db2.deleteAllUserMeetings(_id);
        db.rawQuery("Delete From users where _id =" +_id, null);
    }
    public Boolean updateUser(long _id, Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean b1 = updateName(_id, user.getName());
        Boolean b2 = updateUserName(_id, user.getUsername());
        Boolean b3 = updatePassword(_id, user.getPassword());
        if (b1 && b2 && b3)
            return true;
        return false;
    }
    public Boolean updateName(long _id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update users set name = '"+name+"' where _id =" +_id, null);
        if (cursor.getCount() > 0) return true;
        return false;
    }
    public Boolean updateUserName(long _id, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update users set username = '"+username+"' where _id =" +_id, null);
        if (cursor.getCount() > 0) return true;
        return false;
    }
    public Boolean updatePassword(long _id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update users set password = '"+password+"' where _id =" +_id, null);
        if (cursor.getCount() > 0) return true;
        return false;
    }
}
