package com.example.covidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class ConfirmationFormActivity extends AppCompatActivity {

    private TextView usernameC,nameC,GenderC,myTitle,phoneNumC,addressC,ageC,EmailC,ICnumC,First_date,Second_date;
    int listNumber, role_id;
    String user;
    DatabaseHelper db;
    ArrayList<String> profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_form);

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

        //myTitle= findViewById(R.id.vaccine_title);

        // To determine the header of the form
        listNumber = getIntent().getExtras().getInt("QuestionListNumber");
        /*if (listNumber == 1){
            myTitle.setText("Astrazeneca Confirmation Form");

        }
        else if(listNumber == 2){
            myTitle.setText("Pfizer Confirmation Form");

        }
        else{
            myTitle.setText("Sinopharm Confirmation Form");

        }*/
        user = getIntent().getStringExtra("username");
        role_id =getIntent().getIntExtra("role",0);
        db = new DatabaseHelper(this);
        //profile = new ArrayList<>();
        usernameC = findViewById(R.id.conf_Username);
        nameC= findViewById(R.id.conf_name);
        GenderC= findViewById(R.id.conf_gender);
        phoneNumC= findViewById(R.id.conf_phone);
        addressC= findViewById(R.id.conf_address);
        EmailC= findViewById(R.id.conf_email);
        ICnumC= findViewById(R.id.conf_ICNum);
        ageC= findViewById(R.id.conf_age);
        First_date =findViewById(R.id.firstDose_date);
        Second_date= findViewById(R.id.secondDose_date);
        loadProfile();

        /*String Pname= getIntent().getStringExtra("name");

        String Pnum = getIntent().getStringExtra("phone");
        String PAdress= getIntent().getStringExtra("address");
        String PEmail= getIntent().getStringExtra("email");
        String PICnum= getIntent().getStringExtra("icNumber");
        String P_age= getIntent().getStringExtra("age");
        String genderNum= getIntent().getStringExtra("gender");


        nameC.setText(Pname);
        GenderC.setText(genderNum);
        phoneNumC.setText(Pnum);
        addressC.setText(PAdress);
        EmailC.setText(PEmail);
        ICnumC.setText(PICnum);
        ageC.setText(P_age);*/

        /*//determining the date of the first does by adding 7 days to the day the form is filled
        DateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");//format
        Calendar c = Calendar.getInstance();//today's date
        c.add(Calendar.DAY_OF_MONTH, 7);//adding 7 days
        String result = currentDate.format(c.getTime());//Storing result
        First_date.setText(result);

        //determining the second dose date depending on the selected vaccine in the beginning of the app
        if(listNumber==1){

            DateFormat CD = new SimpleDateFormat("dd/MM/yyy");
            Calendar cal = Calendar.getInstance();
            c.add(Calendar.MONTH,3);
            String results = CD.format(c.getTime());
            Second_date.setText(results);
        }
        else if(listNumber==2){
            DateFormat CD = new SimpleDateFormat("dd/MM/yyy");
            Calendar cal = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH,21);
            String results = CD.format(c.getTime());
            Second_date.setText(results);
        }
        else {
            DateFormat CD = new SimpleDateFormat("dd/MM/yyy");
            Calendar cal = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH,21);
            String results = CD.format(c.getTime());
            Second_date.setText(results);
        }*/

    }

    private void loadProfile() {//Displays the logged in user info
        usernameC.setText(user);
        Cursor cursor = db.userProfileInfo(user);
        while(cursor.moveToNext()) {//extracting info from cursor and displaying it in text view
            String name = cursor.getString(4);
            String dob = cursor.getString(6);
            String gender = cursor.getString(5);
            String phone = cursor.getString(7);
            String address = cursor.getString(8);
            String email = cursor.getString(9);
            String ic = cursor.getString(10);
            String firstDose = cursor.getString(13);
            String secondDose = cursor.getString(14);
            //Displaying info in text view
            nameC.setText(name);
            ageC.setText(dob);
            GenderC.setText(gender);
            phoneNumC.setText(phone);
            addressC.setText(address);
            EmailC.setText(email);
            ICnumC.setText(ic);
            First_date.setText(firstDose);
            Second_date.setText(secondDose);
        }
        /*if(cursor.getCount()==0){
            Toast.makeText(this,"User Profile not found", Toast.LENGTH_SHORT).show();
        }else{
            //while (cursor.moveToNext()){
                //profile.add(cursor.getString(0));
                usernameC.setText(user);
                String name = cursor.getString(4);
                nameC.setText(name);
            //}
        }*/


    }


    public void homepage(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("username",user);
        intent.putExtra("role",role_id);
        startActivity(intent);
        //Toast.makeText(this, R.string.SuccessfulRegistration, Toast.LENGTH_LONG).show();
    }
}