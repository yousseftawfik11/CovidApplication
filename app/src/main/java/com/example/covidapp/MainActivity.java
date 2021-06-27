package com.example.covidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to show the icon in the title bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Covid App");
        actionBar.setIcon(R.drawable.mlogo);
        //getSupportActionBar().setDisplayShowTitleEnabled(true);
        //setTitle("My new title");
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
        startActivity(next);
    }

    public void AstraQuiz(View view) {
        Intent next = new Intent(this,QuizActivity.class);
        next.putExtra("QuestionListNumber",1);
        startActivity(next);
    }

    public void PfizerQuiz(View view) {
        Intent next = new Intent(this,QuizActivity.class);
        next.putExtra("QuestionListNumber",2);
        startActivity(next);
    }
}