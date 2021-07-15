package com.example.covidapp;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "login.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
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
}
