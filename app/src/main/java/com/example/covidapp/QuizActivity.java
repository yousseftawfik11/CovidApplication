package com.example.covidapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class QuizActivity extends AppCompatActivity {
    private Button mTrueButton, mFalseButton, mNextButton, mFormButton, mHomeButton;
    private TextView mQuestionTextView;
    int listNumber, role_id;//a number representing which list to display (sent from intent)
    String user, userID;
    DatabaseHelper db;

    //Array to save the Astrazeneca questions into
    private Questions[] AstraQuestionArray = new Questions[]{
            new Questions(R.string.AstraZeneca1, true),
            new Questions(R.string.AstraZeneca2, true),
            new Questions(R.string.AstraZeneca3, false),
            new Questions(R.string.AstraZeneca4, false),
            new Questions(R.string.AstraZeneca5, true)
    };
    //Array to save the Pfizer questions into
    private Questions[] PfizerQuestionArray = new Questions[]{
            new Questions(R.string.Pfizer1, true),
            new Questions(R.string.Pfizer2, false),
            new Questions(R.string.Pfizer3, false),
            new Questions(R.string.Pfizer4, false),
            new Questions(R.string.Pfizer5, true)
    };
    private Questions[] SinopharmQuesionArray = new Questions[]{
            new Questions(R.string.Sino1, false),
            new Questions(R.string.Sino2, false),
            new Questions(R.string.Sino3, false),
            new Questions(R.string.Sino4, true),
            new Questions(R.string.Sino5, false)
    };

    private int mCurrentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        db = new DatabaseHelper(this);

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

        mTrueButton = findViewById(R.id.true_button);
        mFalseButton = findViewById(R.id.false_button);
        mNextButton = findViewById(R.id.next_button);
        mQuestionTextView = findViewById(R.id.question_text_view);
        mFormButton = findViewById(R.id.form_button);
        mHomeButton = findViewById(R.id.homepage_button);
        mHomeButton.setVisibility(View.INVISIBLE);

        //getting the value that represents which button has been clicked to generate appropriate
        // list of questions
        listNumber = getIntent().getExtras().getInt("QuestionListNumber");
        if (listNumber == 1){
            updateAstraQuestion();
        }
        else if (listNumber == 2){
            updatePfizerQuestions();
        }
        else{
            updateSinoQuestions();
        }

        //Getting username from login page
        user = getIntent().getStringExtra("username");
        role_id = getIntent().getIntExtra("role",0);

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex++;

                if(mCurrentIndex == AstraQuestionArray.length ||
                        mCurrentIndex == PfizerQuestionArray.length ||
                        mCurrentIndex == SinopharmQuesionArray.length){//to ensure that we haven't run out of questions
                    //mNextButton.setText("End");
                    //mNextButton.setEnabled(false);
                    mNextButton.setVisibility(View.INVISIBLE);
                    mQuestionTextView.setText("Congratulations, you finished the quiz!");
                    mFormButton.setVisibility(View.VISIBLE);
                    mTrueButton.setVisibility(View.INVISIBLE);
                    mFalseButton.setVisibility(View.INVISIBLE);
                    mHomeButton.setVisibility(View.VISIBLE);
                }
                else {//if there are still questions
                    //updateQuestion();
                    switch (listNumber){
                        case 1:
                            updateAstraQuestion();
                            break;
                        case 2:
                            updatePfizerQuestions();
                            break;
                        default:
                            updateSinoQuestions();
                    }
                }

            }
        });

        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
    }

    private void updateAstraQuestion() {//loads Astrazeneca questions
        int question = AstraQuestionArray[mCurrentIndex].getmTextResultId();
        mQuestionTextView.setText(question);
        mNextButton.setEnabled(false);
        mFormButton.setVisibility(View.INVISIBLE);
    }

    private void updatePfizerQuestions(){//loads Pfizer questions
        int question = PfizerQuestionArray[mCurrentIndex].getmTextResultId();
        mQuestionTextView.setText(question);
        mNextButton.setEnabled(false);
        mFormButton.setVisibility(View.INVISIBLE);
    }

    private void updateSinoQuestions(){//loads sinopharm questions
        int question = SinopharmQuesionArray[mCurrentIndex].getmTextResultId();
        mQuestionTextView.setText(question);
        mNextButton.setEnabled(false);
        mFormButton.setVisibility(View.INVISIBLE);
    }

    private void checkAnswer(boolean userAnswer){//Checks if the user choose the right or wrong answer
        if (listNumber == 1){
            boolean answer = AstraQuestionArray[mCurrentIndex].ismTrueAnswer();
            if(userAnswer == answer){
                Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                mNextButton.setEnabled(true);
            }
            else{
                Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        }
        else if (listNumber == 2){
            boolean answer = PfizerQuestionArray[mCurrentIndex].ismTrueAnswer();
            if(userAnswer == answer){
                Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                mNextButton.setEnabled(true);
            }
            else{
                Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        }
        else {
            boolean answer = SinopharmQuesionArray[mCurrentIndex].ismTrueAnswer();
            if(userAnswer == answer){
                Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
                mNextButton.setEnabled(true);
            }
            else{
                Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }
        }

    }

    /*public void fillForm(View view) {//Takes user to form activity
        Intent intent = new Intent(this,FormActivity.class);
        intent.putExtra("QuestionListNumber", listNumber);
        startActivity(intent);
    }*/

    /*public void returnHome(View view) {//takes user back to homepage
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }*/

    public void bookVaccine(View view) {
        //Getting today's date to add the 7 days (this is a rule we made to give the first does to user after 7 days of registration)
        DateFormat currentDate = new SimpleDateFormat("yyyy/MM/dd");//format
        Calendar c = Calendar.getInstance();//today's date
        c.add(Calendar.DAY_OF_MONTH, 7);//adding 7 days
        String dose1 = currentDate.format(c.getTime());//Storing result


        if (user == null){
            Intent intent = new Intent(this,LoginPage.class);
            startActivity(intent);
            Toast.makeText(this, "You must be Logged In", Toast.LENGTH_LONG).show();
        }
        else {
            userID = String.valueOf(db.getID(user));
            if (listNumber == 1){
                //Getting second dose date
                DateFormat CD = new SimpleDateFormat("yyyy/MM/dd");
                c.add(Calendar.MONTH,3);
                String dose2 = CD.format(c.getTime());

                db.updateVaccine(userID,1, dose1, dose2);
                Toast.makeText(this, "AstraZeneca Vaccine Booked", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("username", user);
                intent.putExtra("role",role_id);
                startActivity(intent);
                finish();
            }
            else if (listNumber == 2){
                //Getting second dose date
                DateFormat CD = new SimpleDateFormat("yyyy/MM/dd");
                c.add(Calendar.DAY_OF_MONTH,21);
                String dose2 = CD.format(c.getTime());

                db.updateVaccine(userID,2, dose1, dose2);
                Toast.makeText(this, "Pfizer Vaccine Booked", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("username", user);
                intent.putExtra("role",role_id);
                startActivity(intent);
                finish();
            }
            else {
                //Getting second dose date
                DateFormat CD = new SimpleDateFormat("yyyy/MM/dd");
                c.add(Calendar.DAY_OF_MONTH,21);
                String dose2 = CD.format(c.getTime());

                db.updateVaccine(userID,3, dose1, dose2);
                Toast.makeText(this, "Sinopharm Vaccine Booked", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra("username", user);
                intent.putExtra("role",role_id);
                startActivity(intent);
                finish();
            }
        }

    }


    public void returnHome(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("username", user);
        intent.putExtra("role",role_id);
        startActivity(intent);
        finish();
    }
}