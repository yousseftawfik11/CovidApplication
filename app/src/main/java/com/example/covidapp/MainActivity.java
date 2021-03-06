package com.example.covidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    Button adminbtn, moreinfo, quizAstra, quizPfiz, quizSino, profilebtn, signoutbtn;
    String user;
    int role_id,vaccine_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);

        adminbtn = findViewById(R.id.admin_button);
        quizPfiz = findViewById(R.id.PfizerQuizButton);
        quizAstra = findViewById(R.id.AstraQuizButton);
        quizSino = findViewById(R.id.SinoQuizButton);
        profilebtn = findViewById(R.id.profile_button);
        signoutbtn = findViewById(R.id.signout_button);

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

        role_id = getIntent().getIntExtra("role",0);

        if(role_id == 1){
            adminbtn.setVisibility(View.VISIBLE);
        }
        else if(role_id== 0)
            adminbtn.setVisibility(View.INVISIBLE);

        user = getIntent().getStringExtra("username");

        if (user != null){
            vaccine_id = dbHelper.getVaccineId(user);
            if(vaccine_id == 1 || vaccine_id == 2 || vaccine_id == 3){
                quizSino.setEnabled(false);
                quizSino.setText("Registered");
                quizPfiz.setEnabled(false);
                quizPfiz.setText("Registered");
                quizAstra.setEnabled(false);
                quizAstra.setText("Registered");
            }
            else if (user.equals("guest") == true ) {
                profilebtn.setVisibility(View.INVISIBLE);
                signoutbtn.setVisibility(View.INVISIBLE);
            }
        }

    }
//implicit intents to open vaccine who websites
    public void AstraWeb(View view) {

        Uri uri = Uri.parse("https://www.who.int/news-room/feature-stories/detail/the-oxford-astrazeneca-covid-19-vaccine-what-you-need-to-know");
        Intent Astra = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(Astra);

    }

    public void pfizerWeb(View view) {
        Uri uri = Uri.parse("https://www.who.int/news-room/feature-stories/detail/who-can-take-the-pfizer-biontech-covid-19--vaccine");
        Intent pfizer = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(pfizer);

    }

    public void SinopharmWeb(View view) {
        Uri uri = Uri.parse("https://www.who.int/news-room/feature-stories/detail/the-sinopharm-covid-19-vaccine-what-you-need-to-know");
        Intent Sino = new Intent(Intent.ACTION_VIEW,uri);
        startActivity(Sino);
    }


//explicit intents to open the related quiz for each vaccine
    public void SinoQuiz(View view) {
        Intent next = new Intent(this,QuizActivity.class);
        next.putExtra("QuestionListNumber", 3);
        next.putExtra("username", user);
        next.putExtra("role", role_id);
        startActivity(next);

    }

    public void AstraQuiz(View view) {
        Intent next = new Intent(this,QuizActivity.class);
        next.putExtra("QuestionListNumber",1);
        next.putExtra("username", user);
        next.putExtra("role", role_id);
        startActivity(next);

    }

    public void PfizerQuiz(View view) {
        Intent next = new Intent(this,QuizActivity.class);
        next.putExtra("QuestionListNumber",2);
        next.putExtra("username", user);
        next.putExtra("role", role_id);
        startActivity(next);

    }

    public void toadmin(View view) {
        Intent admin = new Intent(this, ControlCenter.class);
        startActivity(admin);
    }

    public void goProfile(View view) {
        Intent next = new Intent(this, ConfirmationFormActivity.class);
        next.putExtra("username", user);
        next.putExtra("role", role_id);
        startActivity(next);
        //a7a
    }

    public void toSignout(View view) {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}