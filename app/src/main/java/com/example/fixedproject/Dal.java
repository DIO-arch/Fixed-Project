package com.example.fixedproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

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
    public boolean insertData(String name, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (checkUsernames(username)) return false; //just in case
        String sql_insert = "INSERT INTO users (name, username, password) values(?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_insert);

        statement.bindString(1, name);
        statement.bindString(2, username);
        statement.bindString(3, password);

        statement.execute();

        return checkInsert(name, username, password);
    }
    public boolean checkInsert(String name, String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where name = '"+name+"' and username = '"+username+"'  and password ='"+password+"'", null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true;
        return false;
    }

    public Boolean checkUsernames(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = '"+username+"'", null);
        //new String[]{username}
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true;
        return false;
    } //can be modified to a password and name as well just add them in the obvious places

    public Boolean checkUps(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = '"+username+"'  and password ='"+password+"'", null);
        //new String[]{username, password}
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true;
        return false;
    }
    public int getId(String username){
        SQLiteDatabase db = this.getWritableDatabase(); //below
        Cursor cursor = db.rawQuery("Select _id From users where username = '"+username+"'", null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return  cursor.getInt(cursor.getColumnIndex("_id")); //cursor.getColumnIndex("_id"))
        return -1; //cursor.getInt(0);
    }

    public boolean fakeGetId(String username) {
        SQLiteDatabase db = this.getWritableDatabase(); //below
        Cursor cursor = db.rawQuery("Select _id From users where username = '"+username+"'", null);
        return cursor.moveToFirst();
    }


    public String getName(long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select name From users where _id ="  +_id, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0) //cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex("name"));
        return null;
    }
    public String getUserName(long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select username From users where _id ="+_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex("username"));
        return null;
    }
    public String getPassword(long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select password From users where _id ="+_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex("password"));
        return null;
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
        if (b1 || b2 || b3)
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
    public String[] databasePrint(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users",null);
        return cursor.getColumnNames();
    }
}
