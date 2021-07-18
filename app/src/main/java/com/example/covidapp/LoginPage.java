package com.example.covidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

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

        dbHelper = new DatabaseHelper(this);
        username = findViewById(R.id.ETusername);
        password = findViewById(R.id.ETpassword);
    }

    public void displayToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void confirmLogin(View view) {
        String user = username.getText().toString();
        String pass = password.getText().toString();

        Boolean loggedIn = dbHelper.checkLogin(user, pass);
        if(loggedIn){
            displayToast("Login successful!");
            int role_id= dbHelper.getRole(user);
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("role",role_id);
            intent.putExtra("username", user);
            startActivity(intent);

        }
        else {
            displayToast("Invalid username or password!");
            username.setText("");
            password.setText("");
        }
    }

    public void loginGoRegister(View view) {
        Intent intent = new Intent(this,FormActivity.class);
        startActivity(intent);
    }
}