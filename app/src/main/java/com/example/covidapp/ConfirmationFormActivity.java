package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ConfirmationFormActivity extends AppCompatActivity {

    private TextView nameC,GenderC,myTitle;
    int listNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_form);

        myTitle= findViewById(R.id.vaccine_title);

        listNumber = getIntent().getExtras().getInt("QuestionListNumber");
        if (listNumber == 1){
            myTitle.setText("Astrazeneca Confirmation Form");
        }
        else if(listNumber == 2){
            myTitle.setText("Pfizer Confirmation Form");
        }
        else{
            myTitle.setText("Sinopharm Confirmation Form");
        }

        nameC= findViewById(R.id.conf_name);
        GenderC= findViewById(R.id.conf_gender);

        String Pname= getIntent().getStringExtra("name");
        String genderNum= getIntent().getStringExtra("gender");




        nameC.setText(Pname);
        GenderC.setText(genderNum);
    }


}