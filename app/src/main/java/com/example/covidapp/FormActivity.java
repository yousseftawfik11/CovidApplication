package com.example.covidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormActivity extends AppCompatActivity {
    private TextView myTitle, myName, myDob, myPhone, myAddress, myEmail, myIC, myUsername, myPassword, myCPassword, myVaccine, myFirstDose, mySecondDose;
    private RadioGroup myGender;
    int listNumber;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

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

        myName = findViewById(R.id.text_name2);
        myGender = findViewById(R.id.radio_gender);
        myDob = findViewById(R.id.text_age2);
        myPhone = findViewById(R.id.text_phone2);
        myAddress = findViewById(R.id.text_address);
        myEmail = findViewById(R.id.text_email);
        myIC = findViewById(R.id.text_ic);

        //New things for Ass3
        myUsername = findViewById(R.id.text_username2);
        myPassword = findViewById(R.id.text_password);
        myCPassword = findViewById(R.id.text_cpassword);
        myVaccine = findViewById(R.id.text_vaccine);
        myFirstDose = findViewById(R.id.text_dose1);
        mySecondDose = findViewById(R.id.text_dose2);
        dbHelper = new DatabaseHelper(this);
    }

    public void confirm(View view) {
        //Checks for a name
        if (TextUtils.isEmpty(myName.getText())){
            myName.setError("Please enter your name");
        }
        else{//Doesn't show error
        }
        //Checks for a age
        if (TextUtils.isEmpty(myDob.getText())){
            myDob.setError("Please enter your date of birth");
        }
        else{//if a dob is entered
            //ageVal(listNumber, myAge);
            dobVal(myDob);
        }
        //Check for a number
        if (TextUtils.isEmpty(myPhone.getText())){
            myPhone.setError("Please enter your phone number");
        }
        else{//Doesn't show error
        }
        //Check for an address
        if (TextUtils.isEmpty(myAddress.getText())){
            myAddress.setError("Please enter your address");
        }
        else{//Doesn't show error
        }
        //Check for an email
        if (TextUtils.isEmpty(myEmail.getText())){
            myEmail.setError("Please enter your email address");
        }
        else{//if an email is entered
            emailVal(myEmail);//Function to make sure that the email is valid
        }
        //Check for an IC number
        if (TextUtils.isEmpty(myIC.getText())){
            myIC.setError("Please enter your IC number");
        }
        else{//Doesn't show error
        }
        //Check for username
        if(TextUtils.isEmpty(myUsername.getText())){
            myUsername.setError("Please enter a username");
        }
        else {//Doesn't show error
        }
        //Checks for password
        if(TextUtils.isEmpty(myPassword.getText())){
            myPassword.setError("Please enter a password");
        }
        else {//Doesn't show error
        }
        //Checks for a confirmation password
        if(TextUtils.isEmpty(myCPassword.getText())){
            myCPassword.setError("Please enter the password another time");
        }
        else {//Doesn't show error
        }
        //checks that all fields are filled
        if(TextUtils.isEmpty(myUsername.getText()) == false && TextUtils.isEmpty(myPassword.getText()) == false &&
                TextUtils.isEmpty(myCPassword.getText()) == false && TextUtils.isEmpty(myName.getText()) == false && TextUtils.isEmpty(myDob.getError()) &&
                TextUtils.isEmpty(myPhone.getText()) == false && TextUtils.isEmpty(myAddress.getText()) == false &&
                TextUtils.isEmpty(myEmail.getError()) && TextUtils.isEmpty(myIC.getText()) == false){

            //New things for Ass3
            String ruser = myUsername.getText().toString();
            String rpw = myPassword.getText().toString();
            String rcpw = myCPassword.getText().toString();
            String rname = myName.getText().toString();
            String rgender;
            if (myGender.getCheckedRadioButtonId() == R.id.rbMale){
                rgender = "Male";
            }
            else {
                rgender = "Female";
            }
            String rdob = myDob.getText().toString();
            String rphone = myPhone.getText().toString();
            String raddress = myAddress.getText().toString();
            String remail = myEmail.getText().toString();
            String ric = myIC.getText().toString();


            if (rpw.equals(rcpw)){
                boolean taken = dbHelper.checkUsername(ruser);
                if(!taken){
                    boolean insertSuccessfully = dbHelper.insert(ruser, rpw, Boolean.FALSE, rname, rgender, rdob, rphone, raddress, remail, ric, 0, 4, "None", "None");//Adding user data to the database
                    if (insertSuccessfully){
                        displayToast("Registered Successfully");
                        myUsername.setText("");
                        myPassword.setText("");
                        myCPassword.setText("");
                        Intent intent = new Intent(this,MainActivity.class);
                        intent.putExtra("username",ruser);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        displayToast("Error");
                    }
                }
                else {
                    myUsername.setError("Username has been taken, please choose another username");
                    myUsername.setText("");
                }
            }
            else {
                myPassword.setError("Passwords don't match");
                myPassword.setText("");
                myCPassword.setText("");
            }
        }
        else {
            //stays on the same page
        }

    }

    private void dobVal(TextView myDob) {//Validates the date of birth that the user types while registering
        String checkDob = myDob.getText().toString().trim();
        String pattern = "^\\d{4}\\/(0[1-9]|1[012])\\/(0[1-9]|[12][0-9]|3[01])$";
        if (checkDob.matches(pattern)){
            //myDob.setError("Invalid email! Please put a valid email");
            String sDate = myDob.getText().toString();
            Date date = new Date();
            DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            try{
                date = format.parse(sDate);
            }
            catch (ParseException e){
                e.printStackTrace();
            }
            //Now we have the dob as a date object
            //extracting year, month and day from date
            Calendar today = Calendar.getInstance();
            Calendar dob = Calendar.getInstance();
            dob.setTime(date);
            if (dob.after(today)){
                myDob.setError("Invalid date of birth! This date hasn't come yet");
            }
        }
        else {
            myDob.setError("Invalid date of birth format! Please follow the format yyyy/MM/dd");
        }
    }

    public void displayToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void emailVal (TextView email){//Ensures that the user enters a valid email
        String checkEmail = email.getText().toString().trim();
        String pattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if (!checkEmail.matches(pattern)){
            email.setError("Invalid email! Please put a valid email");
        }
    }
}