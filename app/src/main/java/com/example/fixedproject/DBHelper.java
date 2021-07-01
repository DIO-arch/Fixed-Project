package com.example.fixedproject;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

//for meetings
public class DBHelper extends SQLiteAssetHelper {

    public static final String dbname = "time_management.db";
    Context context = null;

    public DBHelper(Context context) {
        super(context, "time_management.db", null, 1);
        this.context = context;
    }

    public boolean insertData(String title, int smin, int shour, int syear, int smonth, int sday, int emin, int ehour, int eyear, int emonth,int eday, long user_id, long type_id) {
        SQLiteDatabase db = getWritableDatabase();
        //if (checkConflictingMeetings(title, smin, shour, syear, smonth, sday, emin, ehour, eyear, emonth, eday, user_id, type_id)) return false;
        if (checkUpByEverything(title, smin, shour, syear, smonth, sday, emin, ehour, eyear, emonth, eday, user_id, type_id)) return false;
        String sql_insert = "INSERT INTO meetings (smin, shour, syear, smonth, sday, emin, ehour, eyear, emonth, eday, user_id, type_id, title) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = db.compileStatement(sql_insert);
        //starts
        statement.bindString(1, String.valueOf(smin));
        statement.bindString(2, String.valueOf(shour));
        statement.bindString(3, String.valueOf(syear));
        statement.bindString(4, String.valueOf(smonth));
        statement.bindString(5, String.valueOf(sday));
        //ends
        statement.bindString(6, String.valueOf(emin));
        statement.bindString(7, String.valueOf(ehour));
        statement.bindString(8, String.valueOf(eyear));
        statement.bindString(9, String.valueOf(emonth));
        statement.bindString(10, String.valueOf(eday));
        //ids
        statement.bindString(11, String.valueOf(user_id));
        statement.bindString(12, String.valueOf(type_id));
        //title
        statement.bindString(13, title);

        statement.execute();

        return checkUpByEverything(title, smin, shour, syear, smonth, sday, emin, ehour, eyear, emonth, eday, user_id, type_id);
    }

    public long GetID(String title, int smin, int shour, int syear, int smonth, int sday, int emin, int ehour, int eyear, int emonth,int eday, long user_id, long type_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select _id From meetings where title = '"+title+"' and smin = '"+smin+"' and shour = '"+shour+"' and syear = '"+syear+"' and smonth = '"+smonth+"' and sday = '"+sday+"' and emin = '"+emin+"' and ehour = '"+ehour+"' and eyear = '"+eyear+"' and emonth = '"+emonth+"' and eday = '"+eday+"' and user_id = '"+user_id+"' and type_id =" +type_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("_id"));
        return -1;
    }
    public String GetTitle(long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select title From meetings where _id ="  +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getString(cursor.getColumnIndex("title"));
        return null;
    }

    public int GetSHour(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select shour From meetings where _id ="  +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("shour"));
        return -1;
    }
    public int GetSMin(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select smin From meetings where _id ="  +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("smin"));
        return -1;
    }
    public int GetSYear(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select syear From meetings where _id ="  +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("syear"));
        return -1;
    }
    public int GetSMonth(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select smonth From meetings where _id ="  +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("smonth"));
        return -1;
    }
    public int GetSDay(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select sday From meetings where _id ="  +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("sday"));
        return -1;
    }

    public int GetEHour(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select ehour From meetings where _id ="  +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("ehour"));
        return -1;
    }
    public int GetEMin(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select emin From meetings where _id = "  +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("emin"));
        return -1;
    }
    public int GetEYear(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select eyear From meetings where _id = "  +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("eyear"));
        return -1;
    }
    public int GetEMonth(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select emonth From meetings where _id =" +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("emonth"));
        return -1;
    }
    public int GetEDay(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select eday From meetings where _id =" +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("eday"));
        return -1;
    }
    public long GetTypeId(long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select type_id From meetings where _id =" +_id,null);
        if (cursor.getCount() > 0 && cursor.moveToFirst())
            return cursor.getInt(cursor.getColumnIndex("type_id"));
        return -1;
    }
    public String GetType(long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteHelper sql = new SQLiteHelper(this.context);
        long type_id = this.GetTypeId(_id);
        return sql.SelectType(type_id);
    }

    public Boolean deleteMeeting(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Delete From meetings where _id =" +_id, null);
        if(cursor.moveToFirst() && cursor.getCount() > 0) return false;
        return checkByID(_id);
    }
    public Boolean deleteAllUserMeetings(long user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        String s = "Delete From meetings where user_id =" +user_id;
        if(user_id == 1) {
            s = "Delete From meetings";
        }
        Cursor cursor = db.rawQuery(s, null);
        if(cursor.moveToFirst() && cursor.getCount() > 0) return false;
        return true;
    }
    public void deleteAllUserMeetings(String username){
        SQLiteDatabase db = this.getWritableDatabase();

    }
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    public ArrayList<Meetings> getAllMeetings() {
        ArrayList<Meetings> ary = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from meetings", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Meetings m = new Meetings();
            //title
            m.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //start
            m.setSminute(cursor.getInt(cursor.getColumnIndex("smin")));
            m.setShour(cursor.getInt(cursor.getColumnIndex("shour")));
            m.setSyear(cursor.getInt(cursor.getColumnIndex("syear")));
            m.setSmonth(cursor.getInt(cursor.getColumnIndex("smonth")));
            m.setSday(cursor.getInt(cursor.getColumnIndex("sday")));
            //end
            m.setEminute(cursor.getInt(cursor.getColumnIndex("emin")));
            m.setEhour(cursor.getInt(cursor.getColumnIndex("ehour")));
            m.setEyear(cursor.getInt(cursor.getColumnIndex("eyear")));
            m.setEmonth(cursor.getInt(cursor.getColumnIndex("emonth")));
            m.setEday(cursor.getInt(cursor.getColumnIndex("eday")));
            //ids
            m.setTypeid(cursor.getInt(cursor.getColumnIndex("type_id")));
            m.setUserid(cursor.getInt(cursor.getColumnIndex("user_id")));
            m.setId(cursor.getInt(cursor.getColumnIndex("_id")));

            ary.add(m);
            cursor.moveToNext();
        }
        cursor.close();
        return ary;
    }
    public ArrayList<Meetings> getAllMeetingsMatchingid(Long user_id) {
        ArrayList<Meetings> ary = new ArrayList<>();
        String st;
        if(user_id != 1) st = "select * from meetings where user_id=" +user_id;
        else st = "select * from meetings";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
        while (!cursor.isAfterLast()) {
            Meetings m = new Meetings();
            //title
            m.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //start
            m.setSminute(cursor.getInt(cursor.getColumnIndex("smin")));
            m.setShour(cursor.getInt(cursor.getColumnIndex("shour")));
            m.setSyear(cursor.getInt(cursor.getColumnIndex("syear")));
            m.setSmonth(cursor.getInt(cursor.getColumnIndex("smonth")));
            m.setSday(cursor.getInt(cursor.getColumnIndex("sday")));
            //end
            m.setEminute(cursor.getInt(cursor.getColumnIndex("emin")));
            m.setEhour(cursor.getInt(cursor.getColumnIndex("ehour")));
            m.setEyear(cursor.getInt(cursor.getColumnIndex("eyear")));
            m.setEmonth(cursor.getInt(cursor.getColumnIndex("emonth")));
            m.setEday(cursor.getInt(cursor.getColumnIndex("eday")));
            //ids
            m.setTypeid(cursor.getInt(cursor.getColumnIndex("type_id")));
            m.setUserid(cursor.getInt(cursor.getColumnIndex("user_id")));
            m.setId(cursor.getInt(cursor.getColumnIndex("_id")));

            ary.add(m);
            cursor.moveToNext();
        }
            cursor.close();
        return ary; }
        return null;
    }
    public ArrayList<Meetings> getAllMeetingsMatchingUserName(String username, long _id) {
        ArrayList<Meetings> ary = new ArrayList<>();
        String st;
        if(username != "admin") st = "select * from meetings where _id=" +_id;
        else st = "select * from meetings";
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery(st, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()) {
            Meetings m = new Meetings();
            //title
            m.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //start
            m.setSminute(cursor.getInt(cursor.getColumnIndex("smin")));
            m.setShour(cursor.getInt(cursor.getColumnIndex("shour")));
            m.setSyear(cursor.getInt(cursor.getColumnIndex("syear")));
            m.setSmonth(cursor.getInt(cursor.getColumnIndex("smonth")));
            m.setSday(cursor.getInt(cursor.getColumnIndex("sday")));
            //end
            m.setEminute(cursor.getInt(cursor.getColumnIndex("emin")));
            m.setEhour(cursor.getInt(cursor.getColumnIndex("ehour")));
            m.setEyear(cursor.getInt(cursor.getColumnIndex("eyear")));
            m.setEmonth(cursor.getInt(cursor.getColumnIndex("emonth")));
            m.setEday(cursor.getInt(cursor.getColumnIndex("eday")));
            //ids
            m.setTypeid(cursor.getInt(cursor.getColumnIndex("type_id")));
            m.setUserid(cursor.getInt(cursor.getColumnIndex("user_id")));
            m.setId(cursor.getInt(cursor.getColumnIndex("_id")));

            ary.add(m);
        }
        ary.clear();
        return ary;
    }
    public ArrayList<Meetings> getAllMeetingsADMIN(Long user_id) {
        ArrayList<Meetings> ary = new ArrayList<>();
        String st;
        Dal dal = new Dal(this.context);
        if(dal.getUserName(user_id) != "admin") st = "select * from meetings where user_id=" +user_id;
        else st = "select * from meetings";
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(st, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Meetings m = new Meetings();
            //title
            m.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            //start
            m.setSminute(cursor.getInt(cursor.getColumnIndex("smin")));
            m.setShour(cursor.getInt(cursor.getColumnIndex("shour")));
            m.setSyear(cursor.getInt(cursor.getColumnIndex("syear")));
            m.setSmonth(cursor.getInt(cursor.getColumnIndex("smonth")));
            m.setSday(cursor.getInt(cursor.getColumnIndex("sday")));
            //end
            m.setEminute(cursor.getInt(cursor.getColumnIndex("emin")));
            m.setEhour(cursor.getInt(cursor.getColumnIndex("ehour")));
            m.setEyear(cursor.getInt(cursor.getColumnIndex("eyear")));
            m.setEmonth(cursor.getInt(cursor.getColumnIndex("emonth")));
            m.setEday(cursor.getInt(cursor.getColumnIndex("eday")));
            //ids
            m.setTypeid(cursor.getInt(cursor.getColumnIndex("type_id")));
            m.setUserid(cursor.getInt(cursor.getColumnIndex("user_id")));
            m.setId(cursor.getInt(cursor.getColumnIndex("_id")));

            ary.add(m);
            cursor.moveToNext();
        }
        cursor.close();
        return ary;
    }
    public Boolean checkUpByEverything(String title, int smin, int shour, int syear, int smonth, int sday, int emin, int ehour, int eyear, int emonth,int eday, long user_id, long type_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from meetings where title = '"+title+"' and smin = '"+smin+"' and shour = '"+shour+"' and syear = '"+syear+"' and smonth = '"+smonth+"' and sday = '"+sday+"' and emin = '"+emin+"' and ehour = '"+ehour+"' and eyear = '"+eyear+"' and emonth = '"+emonth+"' and eday = '"+eday+"' and user_id = '"+user_id+"' and type_id =" +type_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true;
        return false;
    }
    public boolean checkConflictingMeetings(String title, int smin, int shour, int syear, int smonth, int sday, int emin, int ehour, int eyear, int emonth,int eday, long user_id, long type_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from meetings where user_id = '"+user_id+"' between '"+shour+"' and '"+ehour+"' and between '"+smin+"' and '"+emin+"' and between '"+syear+"' and '"+eyear+"' and between '"+smonth+"' and '"+emonth+"' and between '"+sday+"' and '"+eday+"'",null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true; //title = '"+title+"' and user_id = '"+user_id+"' or
        return false;
    }
    public Boolean checkIfUserHasMeetings(long user_id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * From meetings where user_id ="  +user_id,null);
        if (cursor.moveToFirst() && cursor.getCount() > 0) //changed order for testing
            return true;
        return false;
    }
    public Boolean checkByID(long _id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * From meetings where _id ="  +_id,null);
        if (cursor.moveToFirst() && cursor.getCount() > 0) //changed order for testing
            return true;
        return false;
    }
}
