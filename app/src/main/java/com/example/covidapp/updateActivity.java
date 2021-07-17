package com.example.covidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updateActivity extends AppCompatActivity {

    EditText username_input, name_input, date_input, phoneNum_input, id_input, role_input;
    String username, name, dob, phoneNum, id, role;
    Button updateBtn, deletebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        username_input = findViewById(R.id.text_username2);
        name_input = findViewById(R.id.text_name2);
        date_input = findViewById(R.id.text_age2);
        phoneNum_input = findViewById(R.id.text_phone2);
        id_input = findViewById(R.id.etID2);
        role_input = findViewById(R.id.etRole2);
        updateBtn = findViewById(R.id.button_update);
        deletebtn = findViewById(R.id.button_delete);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab !=null){
            ab.setTitle(name);
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseHelper mydb= new DatabaseHelper(updateActivity.this);
                name = name_input.getText().toString().trim();
                username = username_input.getText().toString().trim();
                id = id_input.getText().toString().trim();
                dob = date_input.getText().toString().trim();
                phoneNum = phoneNum_input.getText().toString().trim();
                role = role_input.getText().toString().trim();
                mydb.updateData(name,username,id,dob,phoneNum,role);
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
            role_input.setText(role);
            id_input.setText(id);

        }else{
            Toast.makeText(this,"No Data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confrimDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete"+name+ "?");
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