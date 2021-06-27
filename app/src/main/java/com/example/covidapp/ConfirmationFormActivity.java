package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConfirmationFormActivity extends AppCompatActivity {

    private TextView nameC,GenderC,myTitle,phoneNumC,addressC,ageC,EmailC,ICnumC,First_date;
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
        phoneNumC= findViewById(R.id.conf_phone);
        addressC= findViewById(R.id.conf_address);
        EmailC= findViewById(R.id.conf_email);
        ICnumC= findViewById(R.id.conf_ICNum);
        ageC= findViewById(R.id.conf_age);
        First_date =findViewById(R.id.firstDose_date);

        String Pname= getIntent().getStringExtra("name");
        String Pnum = getIntent().getStringExtra("phone");
        String PAdress= getIntent().getStringExtra("address");
        String PEmail= getIntent().getStringExtra("email");
        String PICnum= getIntent().getStringExtra("icNumber");
        String P_age= getIntent().getStringExtra("age");
        int genderNum= getIntent().getIntExtra("gender",0);




        nameC.setText(Pname);
        GenderC.setText(genderNum);
        phoneNumC.setText(Pnum);
        addressC.setText(PAdress);
        EmailC.setText(PEmail);
        ICnumC.setText(PICnum);
        ageC.setText(P_age);


        Date currentDate = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DAY_OF_MONTH, 1);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        Date currentDatePlusOne = c.getTime();
        First_date.setText(currentDatePlusOne.toString());
    }


}