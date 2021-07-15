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

public class FormActivity extends AppCompatActivity {
    private TextView myTitle, myName, myAge, myPhone, myAddress, myEmail, myIC, myUsername, myPassword, myCPassword, myVaccine, myFirstDose, mySecondDose;
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

        /*myTitle=(TextView)findViewById(R.id.FormTitle);
        listNumber = getIntent().getExtras().getInt("QuestionListNumber");
        if (listNumber == 1){
            myTitle.setText("Astrazeneca User Form");
        }
        else if(listNumber == 2){
            myTitle.setText("Pfizer User Form");
        }
        else{
            myTitle.setText("Sinopharm User Form");
        }*/
        myName = findViewById(R.id.text_name);
        myGender = findViewById(R.id.radio_gender);
        myAge = findViewById(R.id.text_age);
        myPhone = findViewById(R.id.text_phone);
        myAddress = findViewById(R.id.text_address);
        myEmail = findViewById(R.id.text_email);
        myIC = findViewById(R.id.text_ic);

        //New things for Ass3
        myUsername = findViewById(R.id.text_username);
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
        if (TextUtils.isEmpty(myAge.getText())){
            myAge.setError("Please enter your age");
        }
        else{//if an age is entered
            //ageVal(listNumber, myAge);
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
                TextUtils.isEmpty(myCPassword.getText()) == false && TextUtils.isEmpty(myName.getText()) == false && TextUtils.isEmpty(myAge.getError()) &&
                TextUtils.isEmpty(myPhone.getText()) == false && TextUtils.isEmpty(myAddress.getText()) == false &&
                TextUtils.isEmpty(myEmail.getError()) && TextUtils.isEmpty(myIC.getText()) == false){
            /*
            //Proceeding to next activity
            Intent intent = new Intent(this,ConfirmationFormActivity.class);
            //intent.putExtra("QuestionListNumber", listNumber);

            //storing user values in variables
            String cName = myName.getText().toString();
            String cAge = myAge.getText().toString();
            String cPhone = myPhone.getText().toString();
            String cAddress = myAddress.getText().toString();
            String cEmail = myEmail.getText().toString();
            String cIC = myIC.getText().toString();


            //Sending values to next activity
            intent.putExtra("name", cName);
            //intent.putExtra("gender", myGender.getCheckedRadioButtonId());
            intent.putExtra("age", cAge);
            intent.putExtra("phone", cPhone);
            intent.putExtra("address", cAddress);
            intent.putExtra("email", cEmail);
            intent.putExtra("icNumber", cIC);
            if (myGender.getCheckedRadioButtonId() == R.id.rbMale){
                intent.putExtra("gender", "Male");
            }
            else {
                intent.putExtra("gender", "Female");
            }

            startActivity(intent);*/

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
            String rdob = myAge.getText().toString();
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
                        startActivity(intent);
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

    /*private void ageVal(int vacType, TextView age){//ensures that the user is within the allowed age
        int checkAge = Integer.parseInt(age.getText().toString());//Converting the number to an integer
        if (vacType == 1){//Astrazeneca
            if(checkAge<18){
                age.setError("You must be over 18 years old to take Astrazeneca");
                Toast.makeText(this, R.string.AstraMinAge, Toast.LENGTH_LONG).show();
            }
        }
        else if (vacType == 2){
            if(checkAge<12){
                age.setError("You must be over 12 years old to take Pfizer");
                Toast.makeText(this, R.string.PfizerMinAge, Toast.LENGTH_LONG).show();
            }
        }
        else {
            if(checkAge<18){
                age.setError("You must be over 18 years old to take Sinopharm");
                Toast.makeText(this, R.string.SinoMinAge, Toast.LENGTH_LONG).show();
            }
        }
    }*/
}