package com.example.covidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

public class AccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
        //to show the icon in the title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("My Vaccine");
        actionBar.setIcon(R.drawable.logo2);
        //getSupportActionBar().setDisplayShowTitleEnabled(true);
        //setTitle("My new title");

        //to change the color of the title bar (action bar)
        //Define ColorDrawable object and parse color
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#107491"));
        //Set BackgroundDrawable
        actionBar.setBackgroundDrawable(colorDrawable);
    }

    public void myRegister(View view) {
        Intent intent = new Intent(this,FormActivity.class);
        startActivity(intent);
    }

    public void myLogin(View view) {
        Intent intent = new Intent(this,LoginPage.class);
        startActivity(intent);
    }

    public void myGuestHome(View view) {//Takes guest user to homepage but sends an intent value to prevent them from booking vaccine unless they are registered
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("username", "guest");
        startActivity(intent);
    }
}