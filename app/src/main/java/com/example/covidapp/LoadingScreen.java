package com.example.covidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.TimerTask;
import java.util.Timer;

public class LoadingScreen extends AppCompatActivity {

    AnimationDrawable loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_screen);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();
                startActivity(new Intent(LoadingScreen.this,MainActivity.class));
            }
        };
        Timer opening = new Timer();
        opening.schedule(task,5000);

        ImageView imgFrame= findViewById(R.id.imageView);
        imgFrame.setBackgroundResource(R.drawable.loading);
        loading=(AnimationDrawable)imgFrame.getBackground();
        loading.start();


        //to show the icon in the title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("My Vaccine");
        actionBar.setIcon(R.drawable.logo2);
    }
}