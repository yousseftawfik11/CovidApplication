package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class FormActivity extends AppCompatActivity {
    private TextView myTitle;
    int listNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        myTitle=(TextView)findViewById(R.id.FormTitle);
        listNumber = getIntent().getExtras().getInt("QuestionListNumber");
        if (listNumber == 1){
            myTitle.setText("Astrazeneca User Form");
        }
        else if(listNumber == 2){
            myTitle.setText("Pfizer User Form");
        }
        else{
            myTitle.setText("Sinopharm User Form");
        }
    }
}