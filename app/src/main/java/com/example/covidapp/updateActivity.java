package com.example.covidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class updateActivity extends AppCompatActivity {

    EditText  name_input, date_input, phoneNum_input, role_input;
    String username, name, dob, phoneNum, id, role;
    Button updateBtn, deletebtn;
    TextView username_input,id_input;
    RadioGroup rolesRBG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

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

        username_input = findViewById(R.id.text_username2);
        name_input = findViewById(R.id.text_name2);
        date_input = findViewById(R.id.text_age2);
        phoneNum_input = findViewById(R.id.text_phone2);
        id_input = findViewById(R.id.etID2);
        updateBtn = findViewById(R.id.button_update);
        deletebtn = findViewById(R.id.button_delete);
        rolesRBG = findViewById(R.id.rolesGroup);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab !=null){
            ab.setTitle(name);
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (TextUtils.isEmpty(name_input.getText())){
                    name_input.setError("Please enter your name");
                }
                else{//Doesn't show error
                }
                //Check for a number
                if (TextUtils.isEmpty(phoneNum_input.getText())){
                    phoneNum_input.setError("Please enter your phone number");
                }
                else {//Doesn't show error
                }

                if(TextUtils.isEmpty(date_input.getText())){
                    date_input.setError("Please enter a date");
                }
                else{//Doesn't show error
                }
                //validates the roles for admin creation


                //checks that all fields are filled
                if(TextUtils.isEmpty(name_input.getText()) == false &&
                        TextUtils.isEmpty(phoneNum_input.getText()) == false &&
                         TextUtils.isEmpty(date_input.getText()) == false){
                    DatabaseHelper mydb= new DatabaseHelper(updateActivity.this);
                    name = name_input.getText().toString().trim();
                    username = username_input.getText().toString().trim();
                    id = id_input.getText().toString().trim();
                    dob = date_input.getText().toString().trim();
                    phoneNum = phoneNum_input.getText().toString().trim();

                    if(rolesRBG.getCheckedRadioButtonId()==R.id.adminRB){
                        //role = role_input.getText().toString().trim();
                        role="1";
                    }else if(rolesRBG.getCheckedRadioButtonId()==R.id.memberRB){
                        role="0";
                    }

                    mydb.updateData(name,username,id,dob,phoneNum,role);
                    finish();
                }


            }
        });
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confrimDialog();
            }
        });


    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("username") &&
                getIntent().hasExtra("name") &&
                getIntent().hasExtra("dob") &&
                getIntent().hasExtra("phonenum") &&
                getIntent().hasExtra("role") &&
                getIntent().hasExtra("id")
        ){
            //Getting data from intent
            username = getIntent().getStringExtra("username");
            name = getIntent().getStringExtra("name");
            dob = getIntent().getStringExtra("dob");
            phoneNum = getIntent().getStringExtra("phonenum");
            role = getIntent().getStringExtra("role");
            id = getIntent().getStringExtra("id");

            //setting intent data
            username_input.setText(username);
            name_input.setText(name);
            date_input.setText(dob);
            phoneNum_input.setText(phoneNum);
            if(role.equals("0")){
                ((RadioButton)rolesRBG.getChildAt(1)).setChecked(true);
            }
            else if (role.equals("1")){
                ((RadioButton) rolesRBG.getChildAt(0)).setChecked(true);
            }

            id_input.setText(id);

        }else{
            Toast.makeText(this,"No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confrimDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete "+name+ "?");
        builder.setMessage("Are you sure you want to delete this user?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseHelper mydb = new DatabaseHelper(updateActivity.this);
                mydb.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }


}

