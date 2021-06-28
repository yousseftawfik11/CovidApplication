package com.example.covidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class FormActivity extends AppCompatActivity {
    private TextView myTitle, myName, myAge, myPhone, myAddress, myEmail, myIC;
    private RadioGroup myGender;
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
        myName = findViewById(R.id.text_name);
        myGender = findViewById(R.id.radio_gender);
        myAge = findViewById(R.id.text_age);
        myPhone = findViewById(R.id.text_phone);
        myAddress = findViewById(R.id.text_address);
        myEmail = findViewById(R.id.text_email);
        myIC = findViewById(R.id.text_ic);
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
        else{//Doesn't show error
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
        //checks that all fields are filled
        if(TextUtils.isEmpty(myName.getText()) == false && TextUtils.isEmpty(myAge.getText()) == false &&
                TextUtils.isEmpty(myPhone.getText()) == false && TextUtils.isEmpty(myAddress.getText()) == false &&
                TextUtils.isEmpty(myEmail.getError()) && TextUtils.isEmpty(myIC.getText()) == false){
            //Proceeding to next activity
            Intent intent = new Intent(this,ConfirmationFormActivity.class);
            intent.putExtra("QuestionListNumber", listNumber);
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
            else /*if (myGender.getCheckedRadioButtonId()==1)*/{
                intent.putExtra("gender", "Female");
            }

            startActivity(intent);
        }
        else {
            //stays on the same page
        }
    }

    private void emailVal (TextView email){
        String checkEmail = email.getText().toString().trim();
        String pattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        if (!checkEmail.matches(pattern)){
            email.setError("Invalid email! Please put a valid email");
        }
    }
}