package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else {
            displayToast("Invalid username or password!");
            username.setText("");
            password.setText("");
        }
    }
}