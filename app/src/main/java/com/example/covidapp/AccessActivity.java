package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);
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
        startActivity(intent);
    }
}