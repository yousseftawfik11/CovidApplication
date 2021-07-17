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
            int role_id= dbHelper.getRole(user);
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("role",role_id);
            intent.putExtra("username", user);
            startActivity(intent);


            int user_id= dbHelper.getID(user);
            //this is where the user ID store you can change the destination of the intent as you wish
            //you could put the user_id as an extra in the first intent and you could redirect it to another activty
           // Intent intent2 = new Intent(this,MainActivity.class);
            //intent2.putExtra("user_id", user_id);
            //startActivity(intent2);
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