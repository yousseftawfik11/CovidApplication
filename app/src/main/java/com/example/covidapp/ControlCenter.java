package com.example.covidapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class ControlCenter extends AppCompatActivity {

    DatabaseHelper myDB; //intialazing the database helper class
    ArrayList<String> username, name, dob, phoneNumber, role, id;
    CustomAdapter customAdapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_center);
        recyclerView = findViewById(R.id.users_list);

        myDB = new DatabaseHelper (ControlCenter.this);
        username = new ArrayList<>();
        name = new ArrayList<>();
        dob = new ArrayList<>();
        phoneNumber = new ArrayList<>();
        role = new ArrayList<>();
        id = new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(ControlCenter.this,this, username,name,dob,phoneNumber,role,id);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ControlCenter.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void  storeDataInArrays(){
        Cursor cursor = myDB.readUsers();
        if(cursor.getCount()==0){
            Toast.makeText(this,"No Data.", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                username.add(cursor.getString(0));
                name.add(cursor.getString(1));
                dob.add(cursor.getString(2));
                phoneNumber.add(cursor.getString(3));
                role.add(cursor.getString(4));
                id.add(cursor.getString(5));

            }
        }
    }
}