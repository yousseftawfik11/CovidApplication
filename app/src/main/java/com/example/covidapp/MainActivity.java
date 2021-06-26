package com.example.covidapp;

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
    }

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

    public void nextActivity(View view) {
        Intent next = new Intent(this,QuizActivity.class);
        startActivity(next);
    }
}