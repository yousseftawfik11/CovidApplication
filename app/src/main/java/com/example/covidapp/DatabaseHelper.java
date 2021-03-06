package com.example.covidapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "login.db";
    private Context context;

    DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "username TEXT, password TEXT, role BOOLEAN, name TEXT, gender TEXT, dob TEXT, phoneNumber TEXT, address TEXT, email TEXT, icNumber TEXT, registeredID INTEGER, vaccine INTEGER, firstDose TEXT, secondDose TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }

    public boolean insert(String username, String password, Boolean role, String name, String gender, String dob, String phoneNumber, String address, String email, String icNumber, Integer registeredID, Integer vaccine, String firstDose, String secondDose){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("role", role);
        contentValues.put("name", name);
        contentValues.put("gender", gender);
        contentValues.put("dob", dob);
        contentValues.put("phoneNumber", phoneNumber);
        contentValues.put("address", address);
        contentValues.put("email", email);
        contentValues.put("icNumber", icNumber);
        contentValues.put("registeredID", registeredID);
        contentValues.put("vaccine", vaccine);
        contentValues.put("firstDose", firstDose);
        contentValues.put("secondDose", secondDose);
        long result = db.insert("user", null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkUsername(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=?",
                new String[] {username});
        //if the result set has more than 0 row i.e. at least one record
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkLogin(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=? AND password=? ",
                new String[] {username, password});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    //creating a method to cursor object
    Cursor readUsers(){
        //sql query to display all data in database
        String query ="SELECT username, name, dob, phoneNumber, role, ID FROM user";
        //creating database object
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db !=null){//if database is not null
            cursor = db.rawQuery(query,null);//execute the query and storing the result in cursor
        }
        return cursor;//will contain all the data from the table
    }

    void updateData(String name, String username, String id, String dob, String phonenum, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(); //to store values inside this object
        cv.put("name", name);
        cv.put("username", username);
        cv.put("ID",id);
        cv.put("dob",dob);
        cv.put("phoneNumber", phonenum);
        cv.put("role",role);

        long result = db.update("user",cv,"id=?", new String[]{id});
        if(result == -1){
            Toast.makeText(context,"Failed to update.",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context,"Succesfully Updated.",Toast.LENGTH_SHORT).show();
    }

    void deleteOneRow(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result= db.delete("user","id=?",new String[]{id});

        if(result == -1){
            Toast.makeText(context,"Failed to Delete.",Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context,"Succesfully Deleted.",Toast.LENGTH_SHORT).show();
    }



    public int getRole(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT role FROM user WHERE username=?",
                new String[] {username});
        int userRole=0 ;
        while(cursor.moveToNext()) {
            userRole = cursor.getInt(0);
        }
        cursor.close();
        return userRole;
    }

    public int getID(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id FROM user WHERE username=?",
                new String[] {username});
        int userID=0 ;
        while(cursor.moveToNext()) {
            userID = cursor.getInt(0);
        }
        cursor.close();
        return userID;
    }

    public void updateVaccine(String id, int vaccineNumber, String firstDose, String secondDose){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("vaccine", vaccineNumber);
        contentValues.put("firstDose", firstDose);
        contentValues.put("secondDose", secondDose);
        long result = db.update("user",contentValues,"id=?", new String[]{id});
    }

    public Cursor userProfileInfo(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery("SELECT * FROM user WHERE username=?",
                    new String[] {username});
        }
        return cursor;
    }

    public int calcAge (String username) {//Function to calculate age of user
        int age = -1; // age if no user
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(//query to calculate age of user for future validation
                "user",
                new String[]{"substr(date('now'),1,4) - substr(dob,1,4) - (strftime('%j',replace(dob,'/','-')) > strftime('%j','now')) AS age"},
                "username=?",
                new String[]{username},
                null,null,null
        );
        if (cursor.moveToFirst()) {
            age = cursor.getInt(cursor.getColumnIndex("age"));
        }
        cursor.close();
        return age;
    }

    public int getVaccineId(String username){//Function to get the id of the vaccine
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT vaccine FROM user WHERE username=?",
                new String[] {username});
        int vaccine_ID=0 ;
        while(cursor.moveToNext()) {
            vaccine_ID = cursor.getInt(0);
        }
        cursor.close();
        return vaccine_ID;
    }
}
