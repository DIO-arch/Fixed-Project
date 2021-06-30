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
    // checkInsert was intended to be used only by insert
    // but inorder to not cause problems I am not changing the name due to time constraints
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
    public Boolean deleteUser(long _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        DBHelper db2 = new DBHelper(this.context);
        //Dal dal = new Dal(this.context);
        if(db2.checkIfUserHasMeetings(_id))
            db2.deleteAllUserMeetings(_id);
        Cursor cursor = db.rawQuery("Delete From users where _id =" +_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return false;
        return checkInsert(getName(_id), getUserName(_id), getPassword(_id));
    }
    public Boolean deleteUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        DBHelper db2 = new DBHelper(this.context);
        Cursor cursor = db.rawQuery("Select * from users where username= '"+username+"'", null);
        if(cursor.getCount() > 0 && cursor.moveToFirst()){
            String sql_delete = "DELETE from users where username = ?";
            SQLiteStatement statement = db.compileStatement(sql_delete);
            //_id = 0, name = 1, username = 2, password = 3
            statement.bindString(cursor.getColumnIndex("username"), username);
            statement.execute();
            if(db2.checkIfUserHasMeetings(this.getId(username))) {
                db2.deleteAllUserMeetings(this.getId(username));
            }
            return true;
        }
        return false;
    }
    // due to merge the save fix was reversed still updates user details
    // but always makes the unsuc toast created 3 versions of updates
    public Boolean updateUser(long _id, Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean b1 = updateName(_id, user.getName());
        Boolean b2 = updateUserName(_id, user.getUsername());
        Boolean b3 = updatePassword(_id, user.getPassword());
        Boolean b4 = false;
        if (b1)
            b4 = true;
        if (b2)
            b4 = true;
        if (b3)
            b4 = true;
        if(checkInsert(user.getName(), user.getUsername(), user.getPassword()))
            b4 = true;
        return b4;
    }
    public Boolean updateName(long _id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update users set name = '"+name+"' where _id =" +_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true;
        return false;
    }
    public Boolean updateUserName(long _id, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update users set username = '"+username+"' where _id =" +_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true;
        return false;
    }
    public Boolean updatePassword(long _id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update users set password = '"+password+"' where _id =" +_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true;
        return false;
    }
    public String[] databasePrint(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users",null);
        return cursor.getColumnNames();
    }
    public String updateName2(long _id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update users set name = '"+name+"' where _id =" +_id, null);
        //cursor = db.rawQuery("Select name From users where _id ="  +_id, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0)
            return cursor.getString(cursor.getColumnIndex("name"));
        return null;
    }
    public String updateUserName2(long _id, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update users set username = '"+username+"' where _id =" +_id, null);
        //cursor = db.rawQuery("Select name From users where _id ="  +_id, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0)
            return cursor.getString(cursor.getColumnIndex("username"));
        return null;
    }
    public String updatePassword2(long _id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update users set password = '"+password+"' where _id =" +_id, null);
        //cursor = db.rawQuery("Select name From users where _id ="  +_id, null);
        if (cursor.moveToFirst() && cursor.getCount() > 0)
            return cursor.getString(cursor.getColumnIndex("password"));
        return null;
    }
    public Boolean updateUser2(long _id, Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        String b1 = updateName2(_id, user.getName());
        String b2 = updateUserName2(_id, user.getUsername());
        String b3 = updatePassword2(_id, user.getPassword());
        Boolean b4 = false;
        if (b1 == user.getName())
            b4 = true;
        if (b2 == user.getUsername())
            b4 = true;
        if (b3 == user.getPassword())
            b4 = true;
        return b4;
    }
    public Boolean updateUser3(long _id, Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        String b1 = updateName2(_id, user.getName());
        String b2 = updateUserName2(_id, user.getUsername());
        String b3 = updatePassword2(_id, user.getPassword());
        if (b1 == user.getName() || b2 == user.getUsername() || b3 == user.getPassword())
            return true;
        return false;
    }
    public Boolean updateName3(long _id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("Update users set name = '"+name+"' where _id =" +_id, null);
        Cursor cursor = db.rawQuery("Select name From users where _id ="  +_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true;
        return false;
    }
    public Boolean updateUserName3(long _id, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.rawQuery("Update users set username = '"+username+"' where _id =" +_id, null);
        Cursor cursor = db.rawQuery("Select name From users where _id ="  +_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true;
        return false;
    }
    public Boolean updatePassword3(long _id, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Update users set password = '"+password+"' where _id =" +_id, null);
        cursor = db.rawQuery("Select name From users where _id ="  +_id, null);
        if (cursor.getCount() > 0 && cursor.moveToFirst()) return true;
        return false;
    }
    public Boolean updateUser4(long _id, Users user) {
        SQLiteDatabase db = this.getWritableDatabase();
        Boolean b1 = updateName3(_id, user.getName());
        Boolean b2 = updateUserName3(_id, user.getUsername());
        Boolean b3 = updatePassword3(_id, user.getPassword());
        Boolean b4 = false;
        if (b1)
            b4 = true;
        if (b2)
            b4 = true;
        if (b3)
            b4 = true;
        return b4;
    }
}
